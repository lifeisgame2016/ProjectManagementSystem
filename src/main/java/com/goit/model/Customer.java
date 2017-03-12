package com.goit.model;


import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @Column(name = "id_customer")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "account")
    private Double account;

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

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}