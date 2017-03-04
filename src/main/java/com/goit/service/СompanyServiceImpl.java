package com.goit.service;

import com.goit.domain.dao.CompanyDAO;
import com.goit.model.Company;

import java.util.List;


public class СompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO;
    private ProjectServiceImpl projectService;

    @Override
    public void delete(int companyId) {
        projectService.all()
                .stream()
                .filter(s -> s.getCompanyId().equals(companyId))
                .forEach(s -> projectService.delete(companyId));
        companyDAO.delete(companyId);
    }

    @Override
    public void save(Company company) {
        companyDAO.save(company);
    }

    @Override
    public Company get(int id) {
        return companyDAO.find(id);
    }

    @Override
    public List<Company> all() {
        return companyDAO.findAll();
    }
}
