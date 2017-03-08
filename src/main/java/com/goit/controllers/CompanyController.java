package com.goit.controllers;

import com.goit.model.Company;
import com.goit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/companies")
@Transactional
public class CompanyController {
    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Company>> getAllCompanies(){
        TransactionStatus status =
                txManager.getTransaction(new DefaultTransactionDefinition
                        (TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Company> result = companyService.all();
            txManager.commit(status);
            return new ResponseEntity<List<Company>>(result, HttpStatus.OK);
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Company getCompanyById(@PathVariable Integer id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            Company company = companyService.get(id);
            txManager.commit(status);
            return company;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //method post - for creation, method put - for updating
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @SuppressWarnings("all")
    public ResponseEntity saveCompany(@RequestBody Company company) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(
                            new DefaultTransactionDefinition(
                                    TransactionDefinition.PROPAGATION_REQUIRED));
            companyService.save(company);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //method delete
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCompanyById(@PathVariable int id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            companyService.delete(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
