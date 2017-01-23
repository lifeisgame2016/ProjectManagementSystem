package com.goit.model;

/**
 * Created by Den on 15-Jan-17.
 */
public class Skill {

    private int id_skills;
    private String name;
    private int id_developer;

    public int getId_skills() {
        return id_skills;
    }

    public void setId_skills(int id_skills) {
        this.id_skills = id_skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_developer() {
        return id_developer;
    }

    public void setId_developer(int id_developer) {
        this.id_developer = id_developer;
    }
}
