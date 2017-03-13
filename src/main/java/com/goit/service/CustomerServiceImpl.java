package com.goit.service;


import com.goit.domain.dao.CustomerDAO;
import com.goit.model.Customer;

import java.util.List;


public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;
    private ProjectServiceImpl projectService;

    @Override
    public void delete(int customerId) {
        projectService.all()
                .stream()
                .filter(s -> s.getCustomer().equals(customerId))
                .forEach(s -> projectService.delete(s.getId()));
        customerDAO.delete(customerId);
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public Customer get(int id) {
        return customerDAO.find(id);
    }

    @Override
    public List<Customer> all() {
        return customerDAO.findAll();
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}
