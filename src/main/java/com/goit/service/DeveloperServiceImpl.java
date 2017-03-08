package com.goit.service;

import com.goit.domain.dao.DeveloperDAO;
import com.goit.domain.dao.SkillDAO;
import com.goit.model.Developer;

import java.util.List;



public class DeveloperServiceImpl implements DeveloperService {

    private DeveloperDAO developerDAO;
    private SkillDAO customSkillDAO;

    @Override
    public Developer get(int id) {
        return developerDAO.find(id);
    }

    @Override
    public List<Developer> all() {
        return null;
    }

    @Override
    public void delete(int developerId) {
        customSkillDAO.findAll()
        .stream()
        .filter(s -> s.getDeveloperId().equals(developerId))
        .forEach(s -> customSkillDAO.delete(s.getId()));
        //delete developer
        developerDAO.delete(developerId);
    }

    @Override
    public void save(Developer developer) {
        developerDAO.save(developer);
    }

    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    public void setSkillDAO(SkillDAO skillDAO) {
        this.customSkillDAO = skillDAO;
    }
}
