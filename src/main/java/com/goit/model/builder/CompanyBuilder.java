package com.goit.model.builder;

import com.goit.model.Company;
import com.goit.model.Project;

import java.util.List;


public final class CompanyBuilder {
    private Integer id;
    private String name;
    private String address;
    private List<Project> projects;

    private CompanyBuilder() {
    }

    public static CompanyBuilder aCompany() {
        return new CompanyBuilder();
    }

    public CompanyBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public CompanyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CompanyBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public CompanyBuilder withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Company build() {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setAddress(address);
        company.setProjects(projects);
        return company;
    }
}
