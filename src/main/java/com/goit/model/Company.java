package com.goit.model;

/*
* Simple POJO
* */

import javax.persistence.*;

@Entity
@Table(name = "companies")
public class Company {


    @Id
    @SequenceGenerator(name = "companies_id_seq", sequenceName = "companies_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_id_seq")
    @Column(name = "id_company")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany
    @Column(name = "id_project")
    private Integer projectId;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
