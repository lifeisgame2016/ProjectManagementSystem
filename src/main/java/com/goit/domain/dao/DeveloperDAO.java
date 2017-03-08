package com.goit.domain.dao;

import com.goit.model.Developer;

import java.util.List;

public interface DeveloperDAO {

    Developer find(Integer id);

    List<Developer> findAll();

    void delete(Integer developerId);

    void save(Developer developer);

    List<Developer> findByName(String name);
}
