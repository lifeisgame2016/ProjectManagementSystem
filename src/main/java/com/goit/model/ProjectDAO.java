package com.goit.model;

import java.util.List;


public interface ProjectDAO {

    Project load(int id);

    List<Project> findAll();

    void addProject(Project project);

    void deleteProject(int id_project);

    void updateProject(Project project);
}
