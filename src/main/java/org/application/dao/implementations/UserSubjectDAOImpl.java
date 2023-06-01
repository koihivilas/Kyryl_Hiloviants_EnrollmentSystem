package org.application.dao.implementations;

import org.application.dao.interfaces.UserSubjectDAO;
import org.application.dao.repository.UserSubjectRepository;
import org.application.models.UserSubject;
import org.application.models.UserSubjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserSubjectDAOImpl implements UserSubjectDAO {
    private final UserSubjectRepository userSubjectRepository;
    public UserSubjectDAOImpl(UserSubjectRepository userSubjectRepository) {
        this.userSubjectRepository = userSubjectRepository;
    }

    @Override
    public UserSubject get(UserSubjectId id) {
        return userSubjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserSubject> getByUserId(long userId) {
        return userSubjectRepository.findByUserId(userId);
    }

    @Override
    public List<UserSubject> getAll() {
        return userSubjectRepository.findAll();
    }

    @Override
    public UserSubject save(UserSubject userSubject) {
        return userSubjectRepository.save(userSubject);
    }

    @Override
    public void delete(UserSubjectId id) {
        userSubjectRepository.deleteById(id);
    }
}
