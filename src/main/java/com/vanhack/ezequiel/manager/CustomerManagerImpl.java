package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.CustomerDto;
import com.vanhack.ezequiel.dto.OrderDto;
import com.vanhack.ezequiel.entity.Customer;
import com.vanhack.ezequiel.entity.CustomerToken;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class CustomerManagerImpl implements CustomerManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean checkDuplicatedEmail(String email) {

        List<Integer> result = entityManager.createNamedQuery("Customer.checkDuplicatedEmail", Integer.class)
                .setParameter("email", email).getResultList();
        return (result.isEmpty());
    }

    @Override
    public void registerNewCustomer(CustomerDto customerDto, String privateKey){
        Customer customer = new Customer();
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setDate(new Date());
        customer.setPassword(customerDto.getPassword());
        customer.setPrivateKey(privateKey);
        customer.setIsActive(true);
        entityManager.persist(customer);
    }

    @Override
    public CustomerDto retrieveCustomerByEmail(String email){
        List<CustomerDto> result = entityManager.createNamedQuery("Customer.retrieveCustomerByEmail", CustomerDto.class)
                .setParameter("email", email).getResultList();
        return (!result.isEmpty() ? result.get(0) : null);
    }

}