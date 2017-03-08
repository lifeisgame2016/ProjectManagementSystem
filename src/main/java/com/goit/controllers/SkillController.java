package com.goit.controllers;

import com.goit.model.Skill;
import com.goit.service.SkillService;
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
@RequestMapping("/skills")
@Transactional
public class SkillController {

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private SkillService skillService;

    @RequestMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @SuppressWarnings("all")
    public Skill getSkillById(@PathVariable Integer id) {
        try{
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            Skill skill = skillService.get(id);
            txManager.commit(status);
            return skill;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<Skill>> getAllSkill(){
        TransactionStatus status =
                txManager.getTransaction(new DefaultTransactionDefinition
                        (TransactionDefinition.PROPAGATION_REQUIRED));
        try {
            List<Skill> result = skillService.all();
            txManager.commit(status);
            return new ResponseEntity<List<Skill>>(result, HttpStatus.OK);
        } catch (Exception e) {
            txManager.rollback(status);
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    @SuppressWarnings("all")
    public ResponseEntity saveSkill(@RequestBody Skill skill) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(
                            new DefaultTransactionDefinition(
                                    TransactionDefinition.PROPAGATION_REQUIRED));
            skillService.save(skill);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    //method delete
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSkillById(@PathVariable int id) {
        try {
            TransactionStatus status =
                    txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
            skillService.delete(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
