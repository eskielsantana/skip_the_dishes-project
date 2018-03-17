package com.vanhack.ezequiel.manager;

import com.vanhack.ezequiel.dto.CustomerDto;

public interface CustomerManager {

    Boolean checkDuplicatedEmail(String username);

    void registerNewCustomer(CustomerDto customerDto, String privateKey);

    CustomerDto retrieveCustomerByEmail(String email);

}