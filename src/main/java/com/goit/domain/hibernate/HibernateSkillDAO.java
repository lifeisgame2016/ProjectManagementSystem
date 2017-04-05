package com.goit.domain.hibernate;

import com.goit.domain.dao.SkillDAO;
import com.goit.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.validation.Valid;
import java.util.List;

public class HibernateSkillDAO implements SkillDAO {

    private SessionFactory sessionFactory;


    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;


    @Override
    @Transactional
    public Skill find(Integer id) {
//        return entityManager.find(Skill.class, id);
        try (Session session = sessionFactory.openSession()) {
            session.joinTransaction();
            Skill skill = session.find(Skill.class, id);
//            skill.getDeveloper();
            return skill;
        }
        //return sessionFactory.getCurrentSession().find(Skill.class, id);
    }

    @Override
    @Transactional
    @Valid
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
        session.save(skill);
        session.close();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
