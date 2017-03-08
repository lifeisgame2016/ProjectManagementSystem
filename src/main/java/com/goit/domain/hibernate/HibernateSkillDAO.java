package com.goit.domain.hibernate;

import com.goit.domain.dao.SkillDAO;
import com.goit.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Den on 04-Mar-17.
 */
public class HibernateSkillDAO implements SkillDAO {

    private SessionFactory sessionFactory;

    @Override
    public Skill find(Integer id) {
        try(Session session = sessionFactory.openSession()){
            session.joinTransaction();
            return session.find(Skill.class, id);
        }
    }

    @Override
    public List<Skill> findAll() {
        return null;
    }

    @Override
    public void delete(Integer skillId) {

    }

    @Override
    public void save(Skill skill) {
        Session session = sessionFactory.openSession();
        session.joinTransaction();
        session.persist(skill);
        session.close();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
