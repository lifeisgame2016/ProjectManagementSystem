package com.goit.domain.hibernate;

import com.goit.domain.dao.CustomerDAO;
import com.goit.model.Customer;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Den on 12-Mar-17.
 */
public class HibernateCustomerDAO implements CustomerDAO {
    private SessionFactory sessionFactory;

    @Override
    public Customer find(Integer id) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public void delete(Integer customerId) {

    }

    @Override
    public void save(Customer customer) {

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
