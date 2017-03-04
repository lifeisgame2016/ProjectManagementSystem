package com.goit.model;

import java.time.LocalDate;


public class Project {

    private Integer id;
    private String name;
    private LocalDate datBeg;
    private LocalDate datEnd;
    private Integer cost;
    private Integer companyId;
    private Integer customerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDatBeg() {
        return datBeg;
    }

    public void setDatBeg(LocalDate datBeg) {
        this.datBeg = datBeg;
    }

    public LocalDate getDatEnd() {
        return datEnd;
    }

    public void setDatEnd(LocalDate datEnd) {
        this.datEnd = datEnd;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
