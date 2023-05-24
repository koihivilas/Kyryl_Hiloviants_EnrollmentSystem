package org.application.services;

import org.application.connection.ConnectionPool;
import org.application.dao.implementations.SubjectDAOImpl;
import org.application.dao.interfaces.SubjectDAO;
import org.application.entities.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class SubjectService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<Subject> getAllSubjects() {
        try (Connection conn = ConnectionPool.getConnection()){
            SubjectDAO subjectDAO = new SubjectDAOImpl(conn);
            try {
                return subjectDAO.getAll();
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
        } catch (Exception e){
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }
    public List<Subject> getAllOpenedSubjects() {
        try (Connection conn = ConnectionPool.getConnection()){
            SubjectDAO subjectDAO = new SubjectDAOImpl(conn);
            try {
                return subjectDAO.findByStatus(false);
            } catch (Exception e) {
                logger.error("Error: " + e.getMessage());
            }
        } catch (Exception e){
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }
}
