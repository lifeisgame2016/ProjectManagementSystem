package com.goit.service;

import com.goit.model.Project;

import java.util.List;


public interface ProjectService {

    void delete(int id);

    void save(Project project);

    Project get(int id);

    List<Project> all();
}
