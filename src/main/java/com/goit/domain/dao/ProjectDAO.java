package com.goit.domain.dao;

import com.goit.model.Project;

import java.util.List;

public interface ProjectDAO {

    Project find(Integer id);

    List<Project> findAll();

    void delete(Integer projectId);

    void save(Project project);

}
