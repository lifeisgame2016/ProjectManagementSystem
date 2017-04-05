package com.goit.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer implements Serializable {

    @Id
    @SequenceGenerator(name = "developer_id_seq", sequenceName = "developer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developer_id_seq")
    @Column(name = "id_developer")
    private Integer id;

    @Column(name = "name")
    @Size(min = 3)
    private String name;

    @Column(name = "age")

    @Max(value = 200)
    @Min(value = 11)
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    @JsonBackReference
    @Cascade(CascadeType.REMOVE)
    @OneToMany(mappedBy = "developer")
    private List<Skill> skills;


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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", project=" + project +
                ", skills=" + skills +
                '}';
    }
}
