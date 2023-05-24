package org.application.services;

import org.application.connection.ConnectionPool;
import org.application.dao.implementations.RoleDAOImpl;
import org.application.dao.interfaces.RoleDAO;
import org.application.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    public Role getRoleByCode(String code) {
        try (Connection conn = ConnectionPool.getConnection()) {
            RoleDAO roleDAO = new RoleDAOImpl(conn);
            try {
                return roleDAO.findByCode(code);
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }
}
