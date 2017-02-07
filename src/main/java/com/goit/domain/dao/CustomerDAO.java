package com.goit.domain.dao;

import com.goit.model.Customer;

import java.util.List;


public interface CustomerDAO {

    Customer find(Integer id);

    List<Customer> findAll();

    void delete(Integer customerId);

    void save(Customer customer);
}
