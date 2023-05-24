package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.RoleDAO;
import org.application.entities.Role;

import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private final EntityManager em;
    public RoleDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role getById(long id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> getAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role findByCode(String code) {
        return em.createQuery("SELECT r FROM Role r WHERE r.code = :code", Role.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public Role create(Role role) {
        em.persist(role);
        return role;
    }

    @Override
    public void update(Role role) {
        em.merge(role);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(Role.class, id));
    }
}
