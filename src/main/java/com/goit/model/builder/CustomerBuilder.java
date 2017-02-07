package com.goit.model.builder;

import com.goit.model.Customer;


public final class CustomerBuilder {
    private Integer id;
    private String name;
    private Double account;
    private Integer projectId;

    private CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {

        return new CustomerBuilder();
    }

    public CustomerBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withAccount(Double account) {
        this.account = account;
        return this;
    }

    public CustomerBuilder withProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setAccount(account);
        customer.setProjectId(projectId);
        return customer;
    }
}
