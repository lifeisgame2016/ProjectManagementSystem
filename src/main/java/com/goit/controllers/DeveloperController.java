package com.goit.controllers;

import com.goit.domain.dao.DeveloperDAO;
import com.goit.model.Developer;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Den on 21-Dec-16.
 */
@Controller
@RequestMapping("/developers")
public class DeveloperController {

    private PlatformTransactionManager txManager;
    private DeveloperDAO developerDAO;

   public List<Developer> getAllDeveloper(){
        TransactionStatus status =
                txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Developer> result = developerDAO.findAll();
            txManager.commit(status);
            return result;
        } catch (Exception e){
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(value = "/{id}", produces = "text/plain")
    public String getDeveloperById(@PathVariable Integer id){
        return developerDAO.load(id).getName();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewDeveloper(Developer developer){
       developerDAO.addDeveloper(developer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteDeveloperById(int id){
        developerDAO.deleteDeveloper(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDeveloper(Developer developer){
        developerDAO.updateDeveloper(developer);
    }

    public void setTxManager(PlatformTransactionManager txManager) {
        this.txManager = txManager;
    }

    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }
}
