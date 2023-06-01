package org.application.services;

import jakarta.transaction.Transactional;
import org.application.dao.interfaces.RoleDAO;
import org.application.dao.interfaces.UserDAO;
import org.application.dao.interfaces.UserStatusDAO;
import org.application.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final UserStatusDAO userStatusDAO;

    public UserService(UserDAO userDAO, RoleDAO roleDAO, UserStatusDAO userStatusDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.userStatusDAO = userStatusDAO;
    }

    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    public Page<User> getAllUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return userDAO.getAll(pageable);
    }

    public User loginUser(String username, String password) {
        User user = userDAO.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public User createNewUser(User user) {
        user.setRole(roleDAO.findByCode("STUDENT"));
        user.setUserStatus(userStatusDAO.findByCode("REG"));

        if (userDAO.findByUsername(user.getUsername()) != null
                || userDAO.findByEmail(user.getEmail()) != null
                || userDAO.findByPhone(user.getPhone()) != null) {
            return null;
        }

        return userDAO.save(user);
    }

    public void changeRole(long id, String roleCode) {
        User user = userDAO.getById(id);

        if (user != null && !user.getRole().getCode().equals("ADMIN")) {
            user.setRole(roleDAO.findByCode(roleCode));
            userDAO.save(user);
        }
    }

    public void changeStatus(long id, String statusCode) {
        User user = userDAO.getById(id);

        if (user != null && !user.getUserStatus().getCode().equals("ADMIN")) {
            user.setUserStatus(userStatusDAO.findByCode(statusCode));
            userDAO.save(user);
        }
    }

    public void deleteUser(long id) {
        userDAO.delete(id);
    }
}
