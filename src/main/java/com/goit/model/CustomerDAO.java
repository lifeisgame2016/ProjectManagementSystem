package com.goit.model;

import java.util.List;


public interface CustomerDAO {

    Customer load(int id);

    List<Customer> findAll();

    void addCustomer(Customer customer);

    void deleteCustomer(int id_customer);

    void updateCustomer(Customer customer);
}
