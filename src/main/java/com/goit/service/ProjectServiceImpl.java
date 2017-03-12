package com.goit.service;

import com.goit.domain.dao.ProjectDAO;
import com.goit.domain.dao.SkillDAO;
import com.goit.model.Project;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    private ProjectDAO projectDAO;
    private DeveloperServiceImpl developerService; //DeveloperDAO
    private SkillDAO skillDAO;

    @Override
    public void delete(int projectId) {
        /*List<Developer> developersInProject =
                developerService.findAll()
                .stream()
                .filter(s -> s.getProjectId().equals(projectId))
                .collect(Collectors.toList());*/

       developerService.all()
               .stream()
               .filter(s -> s.getProject().getId().equals(projectId))
               .forEach(s -> developerService.delete(s.getId()));
       projectDAO.delete(projectId);
    }

    @Override
    public void save(Project project) {
        projectDAO.save(project);
    }

    @Override
    public Project get(int id) {
        return projectDAO.find(id);
    }

    @Override
    public List<Project> all() {
        return projectDAO.findAll();
    }

    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }
}
