package com.goit.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @SequenceGenerator(name = "project_id_seq", sequenceName = "project_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
    @Column(name = "id_project")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "dat_beg")
    private LocalDate datBeg;

    @Column(name = "dat_end")
    private LocalDate datEnd;

    @Column(name = "cost")
    private Integer cost;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
