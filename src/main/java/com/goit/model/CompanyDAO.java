package com.goit.model;

import java.util.List;


public interface CompanyDAO {

    Company load(int id);

    List<Company> findAll();

    void addCompany(Company company);

    void deleteCompany(int id_company);

    void updateCompany(Company company);
}
