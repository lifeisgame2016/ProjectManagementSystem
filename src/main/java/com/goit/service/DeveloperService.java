package com.goit.service;

import com.goit.model.Developer;

import java.util.List;



public interface DeveloperService {

    void delete(int id);

    void save(Developer developer);

    Developer get(int id);

    List<Developer> all();
}
