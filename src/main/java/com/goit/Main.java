package com.goit;

import com.goit.controllers.DeveloperController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main  {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private DeveloperController developerController;

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/application-context.xml");
        Main main = context.getBean(Main.class);
        main.start();
    }

    private void start() {
        // do something
    }

    public void setDeveloperController(DeveloperController developerController) {
        this.developerController = developerController;
    }




}
