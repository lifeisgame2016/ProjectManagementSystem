package com.goit.model.builder;

import com.goit.model.Project;

import java.time.LocalDate;



public final class ProjectBuilder {
    private Integer id;
    private String name;
    private LocalDate datBeg;
    private LocalDate datEnd;
    private Integer cost;
    private Integer companyId;
    private Integer customerId;

    private ProjectBuilder() {
    }

    public static ProjectBuilder aProject() {
        return new ProjectBuilder();
    }

    public ProjectBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder withDatBeg(LocalDate datBeg) {
        this.datBeg = datBeg;
        return this;
    }

    public ProjectBuilder withDatEnd(LocalDate datEnd) {
        this.datEnd = datEnd;
        return this;
    }

    public ProjectBuilder withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public ProjectBuilder withCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    public ProjectBuilder withCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public Project build() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDatBeg(datBeg);
        project.setDatEnd(datEnd);
        project.setCost(cost);
        project.setCompanyId(companyId);
        project.setCustomerId(customerId);
        return project;
    }
}
