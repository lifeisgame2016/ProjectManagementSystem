package com.goit.service;


import com.goit.model.Company;

import java.util.List;

public interface CompanyService {

    void delete(int companyId);

    void save(Company company);

    Company get(int id);

    List<Company> all();
}
