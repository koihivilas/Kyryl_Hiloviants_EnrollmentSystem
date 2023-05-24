package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.SubjectDAO;
import org.application.entities.Subject;

import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    private final EntityManager em;
    public SubjectDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Subject getById(long id) {
        return em.find(Subject.class, id);
    }

    @Override
    public List<Subject> getAll() {
        return em.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    @Override
    public Subject findByName(String name) {
        return em.createQuery("SELECT s FROM Subject s WHERE s.name = :name", Subject.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Subject> findByStatus(boolean closed) {
        return em.createQuery("SELECT s FROM Subject s WHERE s.closed = :closed", Subject.class)
                .setParameter("closed", closed)
                .getResultList();
    }

    @Override
    public Subject create(Subject subject) {
        em.persist(subject);
        return subject;
    }

    @Override
    public void update(Subject subject) {
        em.merge(subject);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(Subject.class, id));
    }
}
