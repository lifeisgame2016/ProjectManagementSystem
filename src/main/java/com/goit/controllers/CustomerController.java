package com.goit.controllers;

import com.goit.model.Customer;
import com.goit.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
@Transactional
public class CustomerController {

    @Autowired
    private HibernateTransactionManager txManager;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomer(){
        TransactionStatus status =
                txManager.getTransaction(new DefaultTransactionDefinition
                        (TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Customer> result = customerService.all();
            txManager.commit(status);
            return new ResponseEntity<List<Customer>>(result, HttpStatus.OK);
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Customer getCustomerById(@PathVariable Integer id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            Customer customer = customerService.get(id);
            txManager.commit(status);
            return customer;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //method post - for creation, method put - for updating
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @SuppressWarnings("all")
    public ResponseEntity saveCustomer(@RequestBody Customer customer) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(
                            new DefaultTransactionDefinition(
                                    TransactionDefinition.PROPAGATION_REQUIRED));
            customerService.save(customer);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //method delete
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomerById(@PathVariable int id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            customerService.delete(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
