package com.goit.model;


import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @SequenceGenerator(name = "skill_id_seq", sequenceName = "skill_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_id_seq")
    @Column(name = "id_skills")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_developer")
    private Developer developerId;


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

    public Developer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Developer developerId) {
        this.developerId = developerId;
    }
}
