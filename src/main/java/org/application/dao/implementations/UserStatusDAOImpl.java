package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.UserStatusDAO;
import org.application.entities.UserStatus;

import java.util.List;

public class UserStatusDAOImpl implements UserStatusDAO {
    private final EntityManager em;
    public UserStatusDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserStatus getById(long id) {
        return em.find(UserStatus.class, id);
    }

    @Override
    public List<UserStatus> getAll() {
        return em.createQuery("SELECT us FROM UserStatus us", UserStatus.class).getResultList();
    }

    @Override
    public UserStatus findByCode(String code) {
        return em.createQuery("SELECT us FROM UserStatus us WHERE us.code = :code", UserStatus.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public UserStatus create(UserStatus status) {
        em.persist(status);
        return status;
    }

    @Override
    public void update(UserStatus status) {
        em.merge(status);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(UserStatus.class, id));
    }
}
