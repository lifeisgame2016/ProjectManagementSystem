package com.goit.service;


import com.goit.model.Skill;

import java.util.List;

public interface SkillService {

    void delete(int id);

    void save(Skill skill);

    Skill get(int id);

    List<Skill> all();

}
