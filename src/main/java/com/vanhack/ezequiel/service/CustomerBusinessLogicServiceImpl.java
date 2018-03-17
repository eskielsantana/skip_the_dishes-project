package com.vanhack.ezequiel.service;

import com.vanhack.ezequiel.dto.CustomerDto;
import com.vanhack.ezequiel.manager.CustomerManager;
import com.vanhack.ezequiel.manager.CustomerTokenManager;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class CustomerBusinessLogicServiceImpl implements CustomerBusinessLogicService {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String EMPTY_STRING = "";


    @Autowired
    CustomerManager customerManager;

    @Autowired
    CustomerTokenManager customerTokenManager;

    @Override
    public void registerNewCustomer(CustomerDto customerDto) throws Exception {

        if(customerManager.checkDuplicatedEmail(customerDto.getEmail())){
            EncryptedPasswordVO encryptedPasswordVO = encriptPassword(customerDto.getPassword(), EMPTY_STRING);
            customerDto.setPassword(encryptedPasswordVO.getEncryptedPassword());
            customerManager.registerNewCustomer(customerDto, encryptedPasswordVO.privateKey);
        }else{
            throw new Exception("This email is already being used");
        }
    }

    @Override
    public String loginCustomer(String email, String password) throws Exception {
        CustomerDto customerDto = customerManager.retrieveCustomerByEmail(email);
        EncryptedPasswordVO encryptedPasswordVO = encriptPassword(password, customerDto.getPrivateKey());
        if(customerDto.getPassword().equals(encryptedPasswordVO.getEncryptedPassword())){
            String token = randomAlphaNumeric(64);
            customerTokenManager.registerTokenToCustomer(token, customerDto.getId());
            return token;
        }else{
            throw new Exception("Incorrect password");
        }
    }

    @Override
    public Boolean validateToken(String token){
        return customerTokenManager.checkIfTokenIsValid(token);
    }

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private EncryptedPasswordVO encriptPassword(String password, String privateKey) throws NoSuchAlgorithmException, InvalidKeyException {

        if(EMPTY_STRING.equals(privateKey)){
            privateKey = randomAlphaNumeric(64);
        }

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(privateKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        String encryptedPassword = Base64.encodeBase64String(sha256_HMAC.doFinal(password.getBytes()));

        return new EncryptedPasswordVO(encryptedPassword, privateKey);
    }

    private final class EncryptedPasswordVO {
        private String encryptedPassword;
        private String privateKey;

        public EncryptedPasswordVO(String encryptedPassword, String privateKey) {
            this.encryptedPassword = encryptedPassword;
            this.privateKey = privateKey;
        }

        public String getEncryptedPassword() {
            return encryptedPassword;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }

}