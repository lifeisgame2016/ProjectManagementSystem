package com.goit.model.builder;

import com.goit.model.Company;

/**
 * Created by Den on 04-Feb-17.
 */
public final class CompanyBuilder {
    private Integer id;
    private String name;
    private String address;
    private Integer projectId;

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

    public CompanyBuilder withProjectId(Integer projectId) {
        this.projectId = projectId;
        return this;
    }

    public Company build() {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setAddress(address);
        company.setProjectId(projectId);
        return company;
    }
}
