package com.goit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goit.domain.dao.DeveloperDAO;
import com.goit.model.Developer;
import com.goit.model.builder.DeveloperBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class DeveloperControllerTest {

    @Autowired
    private DeveloperController developerController;
    @Autowired
    private DeveloperDAO developerDAO;

    @Autowired
    private PlatformTransactionManager txManager;
    private MockMvc mockMvc;

    @Before
    public void start(){
        mockMvc = MockMvcBuilders.standaloneSetup(developerController).build();
    }

    @Test
    public void deleteDeveloperById() throws Exception {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transaction = txManager.getTransaction(definition);

        mockMvc.perform(delete("/developers/1"))
                .andExpect(status().isOk());
        txManager.rollback(transaction);
    }

    @Test
    public void saveDeveloper() throws Exception {
        Developer developer = DeveloperBuilder.aDeveloper()
                .withName("Josh")
                .withAge(26)
                .withAddress("Lviv")
                .withSalary(7000)
                .withProjectId(3)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        byte[] developerBinary = mapper.writeValueAsString(developer).getBytes();

        TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED));
        mockMvc.perform(post("/developers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(developerBinary))
                .andExpect(status().isOk());

        txManager.rollback(transaction);
    }
}