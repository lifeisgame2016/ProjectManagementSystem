package com.goit.domain.dao;

import com.goit.model.Company;

import java.util.List;


public interface CompanyDAO {

    Company find(Integer id);

    List<Company> findAll();

    void delete(Integer companyId);

    void save(Company company);

    @Deprecated
        // add company to db
    void addCompany(Company company); //TODO implement save(Company company)

    @Deprecated
        // use CompanyDao#save instead
    void updateCompany(Company company); //TODO implement save(Company company)
}
