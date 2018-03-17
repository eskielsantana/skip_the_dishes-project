package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.entity.Customer;
import com.vanhack.ezequiel.entity.CustomerToken;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class CustomerTokenManagerImpl implements CustomerTokenManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void registerTokenToCustomer(String token, Integer customerId){

        invalidateOldTokens(customerId);
        CustomerToken customerToken = new CustomerToken();
        customerToken.setToken(token);
        customerToken.setCustomer(entityManager.find(Customer.class, customerId));
        customerToken.setValidityDate(DateTime.now().plusDays(3).toDate());
        customerToken.setIsActive(true);
        entityManager.persist(customerToken);
    }

    @Override
    public Boolean checkIfTokenIsValid(String token){
        List<Integer> customerTokens = entityManager.createNamedQuery("CustomerToken.checkIfTokenIsValid", Integer.class)
                .setParameter("token", token).getResultList();
        return (!customerTokens.isEmpty());
    }

    private void invalidateOldTokens(Integer customerId){
        entityManager.createNamedQuery("CustomerToken.invalidateOldCustomerToken")
                .setParameter("customerId", customerId).executeUpdate();
    }

}