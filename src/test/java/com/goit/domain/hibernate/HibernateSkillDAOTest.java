package com.goit.domain.hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.domain.dao.SkillDAO;
import com.goit.model.Skill;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class HibernateSkillDAOTest{

    @Autowired
    private SkillDAO skillDAO;

    @Autowired
    private PlatformTransactionManager txManager;

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

}