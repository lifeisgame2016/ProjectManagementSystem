package com.goit.domain.hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.domain.dao.DeveloperDAO;
import com.goit.domain.dao.SkillDAO;
import com.goit.model.Skill;
import com.goit.model.builder.SkillBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import javax.validation.*;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class HibernateSkillDAOTest{

    @Autowired
    private SkillDAO skillDAO;

    @Autowired
    private DeveloperDAO developerDAO;

    @Autowired
    private PlatformTransactionManager txManager;

    private static Validator validator;

    @BeforeClass
//    @Valid
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void find() throws JsonProcessingException {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = txManager.getTransaction(definition);

        Skill skill = skillDAO.find(4);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(skill);
        Assert.notNull(skill);

        txManager.commit(transactionStatus);
    }

    @Test
    public void validatorTest(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = txManager.getTransaction(definition);

        Skill skill = skillDAO.find(4);

        Set<ConstraintViolation<Skill>> validate = validator.validate(skill);

        org.junit.Assert.assertTrue(validate.isEmpty());
        txManager.rollback(transactionStatus);
    }

    @Test
    public void validatorTestERR() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = txManager.getTransaction(definition);

        try {
            Skill skill = SkillBuilder.aSkill()
                    .withId(777)
                    .withName("Y")
                    .withDeveloper(developerDAO.find(2))
                    .build();

            skillDAO.save(skill);
            Set<ConstraintViolation<Skill>> validate = validator.validate(skill);

            org.junit.Assert.assertFalse(validate.isEmpty());
        } finally {
            txManager.rollback(transactionStatus);
        }
    }

}