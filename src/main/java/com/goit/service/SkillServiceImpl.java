package com.goit.service;

import com.goit.domain.dao.SkillDAO;
import com.goit.model.Skill;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private SkillDAO skillDAO;

    @Override
    public void delete(int id) {
       skillDAO.delete(id);
    }

    @Override
    public void save(Skill skill) {
        skillDAO.save(skill);
    }

    @Override
    public Skill get(int id) {
        return skillDAO.find(id);
    }

    @Override
    public List<Skill> all() {
        return skillDAO.findAll();
    }

    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }
}
