package org.application.services;

import org.application.connection.ConnectionPool;
import org.application.dao.implementations.UserStatusDAOImpl;
import org.application.dao.interfaces.UserStatusDAO;
import org.application.entities.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class UserStatusService {
    private static final Logger logger = LoggerFactory.getLogger(UserStatusService.class);
    public UserStatus getStatusByCode(String code) {
        try (Connection conn = ConnectionPool.getConnection()) {
            UserStatusDAO userStatusDAO = new UserStatusDAOImpl(conn);
            try {
                return userStatusDAO.findByCode(code);
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }
}
