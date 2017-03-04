package com.goit.controllers;

import com.goit.model.Project;
import com.goit.service.ProjectService;
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
@RequestMapping("/projects")
@Transactional
public class ProjectController {

    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @SuppressWarnings("all")
    public Project getProjectById(@PathVariable Integer id){
        try{
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            Project project = projectService.get(id);
            txManager.commit(status);
            return project;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Project>> getAllProject() {
        TransactionStatus status =
                txManager.getTransaction(new DefaultTransactionDefinition
                        (TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Project> result = projectService.all();
            txManager.commit(status);
            return new ResponseEntity<List<Project>>(result, HttpStatus.OK);
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @SuppressWarnings("all")
    public ResponseEntity saveProject(@RequestBody Project project) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(
                            new DefaultTransactionDefinition(
                                    TransactionDefinition.PROPAGATION_REQUIRED));
            projectService.save(project);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteProjectById(@PathVariable int id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            projectService.delete(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
