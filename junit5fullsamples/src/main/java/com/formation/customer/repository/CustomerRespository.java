package com.formation.customer.repository;

import java.util.List;

import com.formation.customer.model.Customer;

public interface CustomerRespository {

    List<Customer> findAll();

    Customer findOne(Long id);

    boolean isDataFromMemory();
}