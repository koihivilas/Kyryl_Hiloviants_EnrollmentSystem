package org.application.dao.implementations;

import org.application.dao.interfaces.SubjectScoreUnitDAO;
import org.application.entities.Subject;
import org.application.entities.SubjectScoreUnit;
import org.application.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectScoreUnitDAOImpl implements SubjectScoreUnitDAO {
    private static final String SQL_SELECT_SUBJECT_SCORE_BY_USER_ID = "SELECT * FROM user_subject WHERE user_id = ?";
    private static final String SQL_INSERT_SUBJECT_SCORE = "INSERT INTO user_subject (user_id, subject_id, score) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_SUBJECT_SCORE = "UPDATE user_subject SET score = ? WHERE user_id = ? AND subject_id = ?";
    private static final String SQL_DELETE_SUBJECT_SCORES_BY_USER_ID = "DELETE FROM user_subject WHERE user_id = ?";
    private static final String SQL_DELETE_SUBJECT_SCORE = "DELETE FROM user_subject WHERE user_id = ? AND subject_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(SubjectScoreUnitDAOImpl.class);
    public SubjectScoreUnitDAOImpl(Connection conn) {
        this.conn = conn;
    }

    private SubjectScoreUnit getSubjectScoreUnit(ResultSet rs) {
        try {
            long subjectId = rs.getLong("subject_id");
            Subject subject = new SubjectDAOImpl(conn).getById(subjectId);
            int score = rs.getInt("score");

            return new SubjectScoreUnit(subject, score);
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public SubjectScoreUnit getById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SubjectScoreUnit> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<SubjectScoreUnit> getByUser(User user) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SUBJECT_SCORE_BY_USER_ID)) {
            pstmt.setLong(1, user.getUserId());
            try(ResultSet rs = pstmt.executeQuery()) {
                List<SubjectScoreUnit> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(getSubjectScoreUnit(rs));
                }
                return scores;
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public SubjectScoreUnit create(SubjectScoreUnit subjectScoreUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubjectScoreUnit create(User user, SubjectScoreUnit subjectScoreUnit) {
        try(PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_SUBJECT_SCORE)) {
            pstmt.setLong(1, user.getUserId());
            pstmt.setLong(2, subjectScoreUnit.getSubject().getSubjectId());
            pstmt.setInt(3, subjectScoreUnit.getScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return subjectScoreUnit;
    }

    @Override
    public void update(SubjectScoreUnit subjectScoreUnit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(User user, SubjectScoreUnit subjectScoreUnit) {
        try(PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_SUBJECT_SCORE)) {
            pstmt.setInt(1, subjectScoreUnit.getScore());
            pstmt.setLong(2, user.getUserId());
            pstmt.setLong(3, subjectScoreUnit.getSubject().getSubjectId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    // delete all subject scores of specific user
    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_SUBJECT_SCORES_BY_USER_ID)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(User user, Subject subject) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_SUBJECT_SCORE)) {
            pstmt.setLong(1, user.getUserId());
            pstmt.setLong(2, subject.getSubjectId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
