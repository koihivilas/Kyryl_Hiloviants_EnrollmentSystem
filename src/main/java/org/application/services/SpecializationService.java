package org.application.services;

import org.application.connection.ConnectionPool;
import org.application.dao.implementations.SpecializationDAOImpl;
import org.application.dao.interfaces.SpecializationDAO;
import org.application.entities.Specialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class SpecializationService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<Specialization> getAllSpecializations() {
        try (Connection conn = ConnectionPool.getConnection()){
            SpecializationDAO specializationDAO = new SpecializationDAOImpl(conn);
            try {
                return specializationDAO.getAll();
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
        } catch (Exception e){
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }
}
