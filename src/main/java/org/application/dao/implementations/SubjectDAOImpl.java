package org.application.dao.implementations;

import org.application.dao.interfaces.SubjectDAO;
import org.application.entities.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    private static final String SQL_SELECT_SUBJECT_BY_ID = "SELECT * FROM subjects WHERE subject_id = ?";
    private static final String SQL_SELECT_ALL_SUBJECTS = "SELECT * FROM subjects";
    private static final String SQL_SELECT_SUBJECT_BY_NAME = "SELECT * FROM subjects WHERE `name` = ?";
    private static final String SQL_SELECT_SUBJECT_BY_STATUS = "SELECT * FROM subjects WHERE closed = ?";
    private static final String SQL_ADD_SUBJECT = "INSERT INTO subjects (`name`, `description`, closed) VALUES (?,?,?)";
    private static final String SQL_UPDATE_SUBJECT = "UPDATE subjects SET `name` = ?, `description` = ?, closed = ? WHERE subject_id = ?";
    private static final String SQL_DELETE_SUBJECT = "DELETE FROM subjects WHERE subject_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(SubjectDAOImpl.class);
    public SubjectDAOImpl(Connection conn) {
        this.conn = conn;
    }

    private Subject getSubject(ResultSet rs){
        try {
            long id = rs.getLong("subject_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            boolean closed = rs.getBoolean("closed");
            return new Subject(id, name, description, closed);
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Subject getById(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SUBJECT_BY_ID)){
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getSubject(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subject> getAll() {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL_SUBJECTS)){
            List<Subject> subjects = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = getSubject(rs);
                    subjects.add(subject);
                }
                return subjects;
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Subject findByName(String name) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SUBJECT_BY_NAME)){
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getSubject(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Subject> findByStatus(boolean closed) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SUBJECT_BY_STATUS)){
            pstmt.setBoolean(1, closed);
            List<Subject> subjects = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Subject subject = getSubject(rs);
                    subjects.add(subject);
                }
                return subjects;
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Subject create(Subject subject) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_ADD_SUBJECT, PreparedStatement.RETURN_GENERATED_KEYS)){
            pstmt.setString(1, subject.getName());
            pstmt.setString(2, subject.getDescription());
            pstmt.setBoolean(3, subject.getClosed());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    subject.setSubjectId(rs.getLong(1));
                    return subject;
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Subject subject) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_SUBJECT)){
            pstmt.setString(1, subject.getName());
            pstmt.setString(2, subject.getDescription());
            pstmt.setBoolean(3, subject.getClosed());
            pstmt.setLong(4, subject.getSubjectId());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_SUBJECT)){
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
