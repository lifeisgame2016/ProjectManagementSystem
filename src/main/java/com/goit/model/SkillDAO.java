package com.goit.model;

import java.util.List;


public interface SkillDAO {

    Skill load(int id);

    List<Skill> findAll();

    void addSkill(Skill skill);

    void deleteSkill(int id_skill);

    void updateSkill(Skill skill);
}
