package com.goit.service;


import com.goit.model.Customer;

import java.util.List;

public interface CustomerService {

    void delete(int id);

    void save (Customer customer);

    Customer get(int id);

    List<Customer> all();
}
