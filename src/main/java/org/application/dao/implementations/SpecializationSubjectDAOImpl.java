package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.SpecializationSubjectDAO;
import org.application.entities.SpecializationSubject;
import org.application.entities.SpecializationSubjectId;

import java.util.List;

public class SpecializationSubjectDAOImpl implements SpecializationSubjectDAO {
    private final EntityManager em;
    public SpecializationSubjectDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public SpecializationSubject get(SpecializationSubjectId id) {
        return em.find(SpecializationSubject.class, id);
    }

    @Override
    public List<SpecializationSubject> getBySpecializationId(long specializationId) {
        return em.createQuery("SELECT ss FROM SpecializationSubject ss WHERE ss.specializationId = :specializationId", SpecializationSubject.class)
                .setParameter("specializationId", specializationId)
                .getResultList();
    }

    @Override
    public List<SpecializationSubject> getAll() {
        return em.createQuery("SELECT ss FROM SpecializationSubject ss", SpecializationSubject.class)
                .getResultList();
    }

    @Override
    public SpecializationSubject create(SpecializationSubject specializationSubject) {
        em.persist(specializationSubject);
        return specializationSubject;
    }

    @Override
    public void update(SpecializationSubject specializationSubject) {
        em.merge(specializationSubject);
    }

    @Override
    public void delete(SpecializationSubjectId id) {
        em.remove(em.find(SpecializationSubject.class, id));
    }
}
