package com.goit.domain.hibernate;

import com.goit.domain.dao.ProjectDAO;
import com.goit.model.Project;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Den on 12-Mar-17.
 */
public class HibernateProjectDAO implements ProjectDAO {
    private SessionFactory sessionFactory;

    @Override
    public Project find(Integer id) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public void delete(Integer projectId) {

    }

    @Override
    public void save(Project project) {

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
