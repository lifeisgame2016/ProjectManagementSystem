package com.goit.domain.hibernate;

import com.goit.domain.dao.CompanyDAO;
import com.goit.model.Company;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Den on 12-Mar-17.
 */
public class HibernateCompanyDAO implements CompanyDAO {
    private SessionFactory sessionFactory;

    @Override
    public Company find(Integer id) {
        return null;
    }

    @Override
    public List<Company> findAll() {
        return null;
    }

    @Override
    public void delete(Integer companyId) {

    }

    @Override
    public void save(Company company) {

    }

    @Override
    public void addCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
