package com.goit.controllers;

import com.goit.model.Developer;
import com.goit.model.DeveloperDAO;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by Den on 21-Dec-16.
 */
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
    public Developer getDeveloperById(int id){

        return developerDAO.load(id);
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
