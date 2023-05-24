package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.UserSubjectDAO;
import org.application.entities.UserSubject;
import org.application.entities.UserSubjectId;

import java.util.List;

public class UserSubjectDAOImpl implements UserSubjectDAO {
    private final EntityManager em;
    public UserSubjectDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public UserSubject get(UserSubjectId id) {
        return em.find(UserSubject.class, id);
    }

    @Override
    public List<UserSubject> getByUserId(long userId) {
        return em.createQuery("SELECT us FROM UserSubject us WHERE us.userId = :userId", UserSubject.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<UserSubject> getAll() {
        return em.createQuery("SELECT us FROM UserSubject us", UserSubject.class)
                .getResultList();
    }

    @Override
    public UserSubject create(UserSubject userSubject) {
        em.persist(userSubject);
        return userSubject;
    }

    @Override
    public void update(UserSubject userSubject) {
        em.merge(userSubject);
    }

    @Override
    public void delete(UserSubjectId id) {
        em.remove(em.find(UserSubject.class, id));
    }
}
