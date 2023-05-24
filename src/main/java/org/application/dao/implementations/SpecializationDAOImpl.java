package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.SpecializationDAO;
import org.application.entities.Specialization;

import java.util.List;

public class SpecializationDAOImpl implements SpecializationDAO {
    private final EntityManager em;
    public SpecializationDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Specialization getById(long id) {
        return em.find(Specialization.class, id);
    }

    @Override
    public List<Specialization> getAll() {
        return em.createQuery("SELECT s FROM Specialization s", Specialization.class).getResultList();
    }

    @Override
    public Specialization findByCode(int code) {
        return em.createQuery("SELECT s FROM Specialization s WHERE s.code = :code", Specialization.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public Specialization findByName(String name) {
        return em.createQuery("SELECT s FROM Specialization s WHERE s.name = :name", Specialization.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Specialization create(Specialization specialization) {
        em.persist(specialization);
        return specialization;
    }

    @Override
    public void update(Specialization specialization) {
        em.merge(specialization);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(Specialization.class, id));
    }
}
