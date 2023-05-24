package org.application.dao.implementations;

import org.application.dao.interfaces.SpecializationDAO;
import org.application.entities.Specialization;
import org.application.entities.SubjectWeightUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecializationDAOImpl implements SpecializationDAO {
    private static final String SQL_SELECT_SPECIALIZATION_BY_ID = "SELECT * FROM specializations WHERE specialization_id = ?";
    private static final String SQL_SELECT_ALL_SPECIALIZATIONS = "SELECT * FROM specializations";
    private static final String SQL_SELECT_SPECIALIZATION_BY_CODE = "SELECT * FROM specializations WHERE `code` = ?";
    private static final String SQL_SELECT_SPECIALIZATION_BY_NAME = "SELECT * FROM specializations WHERE `name` = ?";
    private static final String SQL_ADD_SPECIALIZATION = "INSERT INTO specializations (`code`, `name`, `description`, `limit`) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_SPECIALIZATION = "UPDATE specializations SET `code` = ?, `name` = ?, `description` = ?," +
            "`limit` = ? WHERE specialization_id = ?";
    private static final String SQL_DELETE_SPECIALIZATION = "DELETE FROM specializations WHERE specialization_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(SpecializationDAOImpl.class);
    public SpecializationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    private Specialization getSpecialization(ResultSet rs) {
        try {
            long id = rs.getLong("specialization_id");
            int code = rs.getInt("code");
            String name = rs.getString("name");
            String description = rs.getString("description");
            int limit = rs.getInt("limit");
            Specialization specialization = new Specialization(id, code, name, description, limit);
            List<SubjectWeightUnit> subjectWeightUnits = new SubjectWeightUnitDAOImpl(conn).getBySpecialization(specialization);
            specialization.setSubjectWeightUnits(subjectWeightUnits);
            return specialization;
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization getById(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SPECIALIZATION_BY_ID)) {
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getSpecialization(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Specialization> getAll() {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL_SPECIALIZATIONS)) {
            List<Specialization> specializations = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Specialization specialization = getSpecialization(rs);
                    specializations.add(specialization);
                }
                return specializations;
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization findByCode(int code) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SPECIALIZATION_BY_CODE)) {
            pstmt.setInt(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getSpecialization(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization findByName(String name) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_SPECIALIZATION_BY_NAME)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getSpecialization(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization create(Specialization specialization) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_ADD_SPECIALIZATION, PreparedStatement.RETURN_GENERATED_KEYS)) {
            createUpdate(pstmt, specialization);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    specialization.setSpecializationId(rs.getLong(1));
                    return specialization;
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Specialization specialization) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_SPECIALIZATION)){
            createUpdate(pstmt, specialization);
            pstmt.setLong(5, specialization.getSpecializationId());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    private void createUpdate(PreparedStatement pstmt, Specialization specialization) throws SQLException {
        pstmt.setInt(1, specialization.getCode());
        pstmt.setString(2, specialization.getName());
        pstmt.setString(3, specialization.getDescription());
        pstmt.setInt(4, specialization.getLimit());
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_SPECIALIZATION)){
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
