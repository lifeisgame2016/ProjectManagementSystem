package com.goit.domain.hibernate;

import com.goit.domain.dao.DeveloperDAO;
import com.goit.model.Developer;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Den on 12-Mar-17.
 */
public class HibernateDeveloperDAO implements DeveloperDAO {
    private SessionFactory sessionFactory;

    @Override
    public Developer find(Integer id) {
        return null;
    }

    @Override
    public List<Developer> findAll() {
        return null;
    }

    @Override
    public void delete(Integer developerId) {

    }

    @Override
    public void save(Developer developer) {

    }

    @Override
    public List<Developer> findByName(String name) {
        return null;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
