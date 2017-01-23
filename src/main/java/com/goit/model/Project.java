package com.goit.model;

import java.time.LocalDate;


public class Project {

    private int id_project;
    private String name;
    private LocalDate dat_beg;
    private LocalDate dat_end;
    private int cost;
    private int id_company;
    private int id_customer;

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDat_beg() {
        return dat_beg;
    }

    public void setDat_beg(LocalDate dat_beg) {
        this.dat_beg = dat_beg;
    }

    public LocalDate getDat_end() {
        return dat_end;
    }

    public void setDat_end(LocalDate dat_end) {
        this.dat_end = dat_end;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

}
