package com.goit.model;

/**
 * Created by Den on 14-Dec-16.
 */
public class Developer {

    private int id_developer;
    private String name;
    private int age;
    private String address;
    private int salary;
    private int id_project;

    public Developer(){}

    public Developer(int id_developer, String name, int age, String address, int salary, int id_project) {
        this.id_developer = id_developer;
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
        this.id_project = id_project;
    }

    public int getId_developer() {
        return id_developer;
    }

    public void setId_developer(int id_developer) {
        this.id_developer = id_developer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id_developer=" + id_developer +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", id_project=" + id_project +
                '}';
    }
}
