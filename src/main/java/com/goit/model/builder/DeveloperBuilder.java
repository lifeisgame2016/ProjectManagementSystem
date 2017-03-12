package com.goit.model.builder;

import com.goit.model.Developer;
import com.goit.model.Project;
import com.goit.model.Skill;

import java.util.List;


public final class DeveloperBuilder {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private Integer salary;
    private Project project;
    private List<Skill> skills;

    private DeveloperBuilder() {
    }

    public static DeveloperBuilder aDeveloper() {
        return new DeveloperBuilder();
    }

    public DeveloperBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public DeveloperBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public DeveloperBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public DeveloperBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public DeveloperBuilder withSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public DeveloperBuilder withProject(Project project) {
        this.project = project;
        return this;
    }

    public DeveloperBuilder withSkills(List<Skill> skills){
        this.skills = skills;
        return this;
    }

    public Developer build() {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setName(name);
        developer.setAge(age);
        developer.setAddress(address);
        developer.setSalary(salary);
        developer.setProject(project);
        developer.setSkills(skills);
        return developer;
    }
}
