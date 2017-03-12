package com.goit.domain.hibernate;

import com.goit.domain.dao.SkillDAO;
import com.goit.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class HibernateSkillDAO implements SkillDAO {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Skill find(Integer id) {
        try(Session session = sessionFactory.openSession()){
            session.joinTransaction();
            return session.find(Skill.class, id);
        }
        //return sessionFactory.getCurrentSession().find(Skill.class, id);
    }

    @Override
    @Transactional
    public List<Skill> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select s from SKILLS s").list();

    }

    @Override
    @Transactional
    public void delete(Integer skillId) {
        sessionFactory.getCurrentSession().delete(skillId);
    }

    @Override
    @Transactional
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
