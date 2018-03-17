package com.vanhack.ezequiel.manager;

public interface CustomerTokenManager {

    void registerTokenToCustomer(String  token, Integer customerId);

    Boolean checkIfTokenIsValid(String token);

}