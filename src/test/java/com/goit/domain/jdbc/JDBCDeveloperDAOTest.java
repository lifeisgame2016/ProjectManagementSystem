package com.goit.domain.jdbc;

import com.goit.domain.dao.DeveloperDAO;
import com.goit.model.builder.DeveloperBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class JDBCDeveloperDAOTest {

    @Autowired
    private DeveloperDAO developerDAO;
    @Autowired
    private DataSourceTransactionManager txManager;

    @Test
    public void findAllTest() throws Exception {
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());
        assertFalse(developerDAO.findAll().isEmpty());
        txManager.commit(transaction);
    }

    @Test
    public void saveTest(){
        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition());

        int expected = developerDAO.findAll().size() + 1;

        developerDAO.save(DeveloperBuilder.aDeveloper()
        .withProjectId(333)
        .withName("Jon")
        .withSalary(50000)
        .withAddress("Kiev")
        .withProjectId(3)
        .withAge(34)
        .build());

        int actual = developerDAO.findAll().size();

        Assert.assertEquals(expected, actual);

        txManager.rollback(transaction);
    }

}