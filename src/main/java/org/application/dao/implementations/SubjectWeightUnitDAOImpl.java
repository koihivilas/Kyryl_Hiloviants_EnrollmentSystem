package org.application.dao.implementations;

import org.application.dao.interfaces.SubjectWeightUnitDAO;
import org.application.entities.Specialization;
import org.application.entities.Subject;
import org.application.entities.SubjectWeightUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectWeightUnitDAOImpl implements SubjectWeightUnitDAO {
    private static final String SQL_SELECT_SUBJECT_WEIGHT_BY_SPECIALIZATION_ID = "SELECT * FROM specialization_subject WHERE specialization_id = ?";
//    private static final String SQL_SELECT_ALL_SUBJECT_WEIGHTS = "SELECT * FROM specialization_subject";
    private static final String SQL_INSERT_SUBJECT_WEIGHT = "INSERT INTO specialization_subject (specialization_id, subject_id, subject_weight) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_SUBJECT_WEIGHT = "UPDATE specialization_subject SET subject_weight = ? WHERE specialization_id = ? AND subject_id = ?";
    private static final String SQL_DELETE_SUBJECT_WEIGHTS_BY_SPECIALIZATION_ID = "DELETE FROM specialization_subject WHERE specialization_id = ?";
    private static final String SQL_DELETE_SUBJECT_WEIGHT = "DELETE FROM specialization_subject WHERE specialization_id = ? AND subject_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(SubjectWeightUnitDAOImpl.class);
    public SubjectWeightUnitDAOImpl(Connection conn) {
        this.conn = conn;
    }

    private SubjectWeightUnit getSubjectWeightUnit(ResultSet rs) {
        try {
            long subjectId = rs.getLong("subject_id");
            Subject subject = new SubjectDAOImpl(conn).getById(subjectId);
            double subjectWeight = rs.getDouble("subject_weight");

            return new SubjectWeightUnit(subject, subjectWeight);
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public SubjectWeightUnit getById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SubjectWeightUnit> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SubjectWeightUnit> getBySpecialization(Specialization specialization) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SUBJECT_WEIGHT_BY_SPECIALIZATION_ID)) {
            pstmt.setLong(1, specialization.getSpecializationId());
            try(ResultSet rs = pstmt.executeQuery()) {
                List<SubjectWeightUnit> subjectWeights = new ArrayList<>();
                while (rs.next()) {
                    subjectWeights.add(getSubjectWeightUnit(rs));
                }
                return subjectWeights;
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public SubjectWeightUnit create(SubjectWeightUnit subjectWeight) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectWeightUnit create(Specialization specialization, SubjectWeightUnit subjectWeightUnit) {
        try(PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_SUBJECT_WEIGHT)) {
            pstmt.setLong(1, specialization.getSpecializationId());
            pstmt.setLong(2, subjectWeightUnit.getSubject().getSubjectId());
            pstmt.setDouble(3, subjectWeightUnit.getSubjectWeight());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return subjectWeightUnit;
    }

    @Override
    public void update(SubjectWeightUnit subjectWeightUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Specialization specialization, SubjectWeightUnit subjectWeightUnit) {
        try(PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_SUBJECT_WEIGHT)) {
            pstmt.setDouble(1, subjectWeightUnit.getSubjectWeight());
            pstmt.setLong(2, specialization.getSpecializationId());
            pstmt.setLong(3, subjectWeightUnit.getSubject().getSubjectId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    // delete all subject weights of specific specialization
    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_SUBJECT_WEIGHTS_BY_SPECIALIZATION_ID)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(Specialization specialization, Subject subject) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_SUBJECT_WEIGHT)) {
            pstmt.setLong(1, specialization.getSpecializationId());
            pstmt.setLong(2, subject.getSubjectId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
