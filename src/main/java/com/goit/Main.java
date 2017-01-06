package com.goit;

import com.goit.controllers.DeveloperController;
import com.goit.model.Developer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    private DeveloperController developerController;

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Main main = context.getBean(Main.class);
        main.start();





        /*DeveloperDAO JDBCDeveloperDAO = new JDBCDeveloperDAO();

        System.out.println("All developers");
        JDBCDeveloperDAO.findAll().forEach(System.out::println);

        System.out.println("Developer with id 2");
        System.out.println(JDBCDeveloperDAO.load(2));*/


    }

    private void start() {
        developerController.getAllDeveloper().forEach(System.out::println);

        developerController.addNewDeveloper(new Developer(8,"Max",23,"Kiev",8000,2));
        developerController.getAllDeveloper().forEach(System.out::println);


    }

    public void setDeveloperController(DeveloperController developerController) {
        this.developerController = developerController;
    }




}
