package org.application.dao.implementations;

import org.application.dao.interfaces.UserStatusDAO;
import org.application.dao.repository.UserStatusRepository;
import org.application.models.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserStatusDAOImpl implements UserStatusDAO {
    private final UserStatusRepository userStatusRepository;
    public UserStatusDAOImpl(UserStatusRepository userStatusRepository) {
        this.userStatusRepository = userStatusRepository;
    }

    @Override
    public UserStatus getById(long id) {
        return userStatusRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserStatus> getAll() {
        return userStatusRepository.findAll();
    }

    @Override
    public UserStatus findByCode(String code) {
        return userStatusRepository.findByCode(code);
    }

    @Override
    public UserStatus save(UserStatus status) {
        return userStatusRepository.save(status);
    }

    @Override
    public void delete(long id) {
        userStatusRepository.deleteById(id);
    }
}
