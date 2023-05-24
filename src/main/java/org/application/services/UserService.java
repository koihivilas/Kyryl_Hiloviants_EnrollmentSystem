package org.application.services;

import org.application.connection.ConnectionPool;
import org.application.dao.implementations.RoleDAOImpl;
import org.application.dao.implementations.UserDAOImpl;
import org.application.dao.implementations.UserStatusDAOImpl;
import org.application.dao.interfaces.RoleDAO;
import org.application.dao.interfaces.UserDAO;
import org.application.dao.interfaces.UserStatusDAO;
import org.application.entities.Role;
import org.application.entities.User;
import org.application.entities.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User createNewUser(User user) {
        try (Connection conn = ConnectionPool.getConnection()) {
            UserDAO userDAO = new UserDAOImpl(conn);
            RoleDAO roleDAO = new RoleDAOImpl(conn);
            UserStatusDAO userStatusDAO = new UserStatusDAOImpl(conn);
            try {
                conn.setAutoCommit(false);
                Role role = roleDAO.findByCode("STUDENT");
                UserStatus userStatus = userStatusDAO.findByCode("REG");
                user.setRole(role);
                user.setUserStatus(userStatus);
                if (userDAO.create(user) != null) {
                    conn.commit();
                    return user;
                }
            } catch (Exception e) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    logger.error("Error: " + e.getMessage());
                }
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }

        return null;
    }

    public User loginUser(String username, String password) {
        try (Connection conn = ConnectionPool.getConnection()) {
            UserDAO userDAO = new UserDAOImpl(conn);
            try {
                User user = userDAO.findByUsername(username);
                if (user.getPassword().equals(password)) {
                    return user;
                }
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }

        return null;
    }
}
