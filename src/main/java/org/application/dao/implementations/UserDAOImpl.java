package org.application.dao.implementations;

import jakarta.persistence.EntityManager;
import org.application.dao.interfaces.UserDAO;
import org.application.entities.Role;
import org.application.entities.Specialization;
import org.application.entities.User;
import org.application.entities.UserStatus;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private final EntityManager em;
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User getById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findByPhone(String phone) {
        return em.createQuery("SELECT u FROM User u WHERE u.phone = :phone", User.class)
                .setParameter("phone", phone)
                .getSingleResult();
    }

    @Override
    public List<User> findByRole(Role role) { // Should I use Id as a parameter instead? (here and in the next 2 methods)
        return em.createQuery("SELECT u FROM User u WHERE u.role = :role", User.class)
                .setParameter("role", role)
                .getResultList();
    }

    @Override
    public List<User> findByStatus(UserStatus status) {
        return em.createQuery("SELECT u FROM User u WHERE u.userStatus = :status", User.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    public List<User> findBySpecialization(Specialization specialization) {
        return em.createQuery("SELECT u FROM User u WHERE u.specialization = :specialization", User.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(User.class, id));
    }
}
