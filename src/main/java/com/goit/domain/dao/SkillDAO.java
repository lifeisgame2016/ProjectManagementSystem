package com.goit.domain.dao;

import com.goit.model.Skill;

import java.util.List;

public interface SkillDAO {

    Skill find(Integer id);

    List<Skill> findAll();

    void delete(Integer skillId);

    void save(Skill skill);
}
