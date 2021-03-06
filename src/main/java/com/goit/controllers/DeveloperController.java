package com.goit.controllers;

import com.goit.model.Developer;
import com.goit.service.DeveloperService;
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
@RequestMapping("/developers")
@Transactional
public class DeveloperController {

    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private DeveloperService developerService;

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Developer>> getAllDeveloper(){
        TransactionStatus status =
                txManager.getTransaction(new DefaultTransactionDefinition
                        (TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Developer> result = developerService.all();
            txManager.commit(status);
            return new ResponseEntity<List<Developer>>(result, HttpStatus.OK);
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Developer getDeveloperById(@PathVariable Integer id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            Developer developer = developerService.get(id);
            txManager.commit(status);
            return developer;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //method post - for creation, method put - for updating
    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @SuppressWarnings("all")
    public ResponseEntity saveDeveloper(@RequestBody Developer developer) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(
                            new DefaultTransactionDefinition(
                                    TransactionDefinition.PROPAGATION_REQUIRED));
            developerService.save(developer);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //method delete
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDeveloperById(@PathVariable int id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            developerService.delete(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


}