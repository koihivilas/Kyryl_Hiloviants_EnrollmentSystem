package org.application.dao.implementations;

import org.application.dao.interfaces.UserStatusDAO;
import org.application.entities.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserStatusDAOImpl implements UserStatusDAO {
    private static final String SQL_SELECT_USER_STATUS_BY_ID = "SELECT * FROM user_statuses WHERE user_status_id = ?";
    private static final String SQL_SELECT_ALL_USER_STATUSES = "SELECT * FROM user_statuses";
    private static final String SQL_SELECT_USER_STATUS_BY_CODE = "SELECT * FROM user_statuses WHERE code = ?";
    private static final String SQL_ADD_USER_STATUS = "INSERT INTO user_statuses (`code`, `name`) VALUES (?,?)";
    private static final String SQL_UPDATE_USER_STATUS = "UPDATE user_statuses SET `code` = ?, `name` = ? WHERE user_status_id = ?";
    private static final String SQL_DELETE_USER_STATUS = "DELETE FROM user_statuses WHERE user_status_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(UserStatusDAOImpl.class);
    public UserStatusDAOImpl(Connection conn) {
        this.conn = conn;
    }

    private UserStatus getUserStatus(ResultSet rs) {
        try {
            long id = rs.getLong("user_status_id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            return new UserStatus(id, code, name);
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserStatus getById(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_USER_STATUS_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getUserStatus(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<UserStatus> getAll() {
        try(PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL_USER_STATUSES)) {
            List<UserStatus> userStatuses = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    UserStatus status = getUserStatus(rs);
                    userStatuses.add(status);
                }
                return userStatuses;
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserStatus findByCode(String code) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_USER_STATUS_BY_CODE)) {
            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getUserStatus(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserStatus create(UserStatus status) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_ADD_USER_STATUS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, status.getCode());
            pstmt.setString(2, status.getName());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    status.setUserStatusId(rs.getLong(1));
                    return status;
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(UserStatus status) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_USER_STATUS)){
            pstmt.setString(1, status.getCode());
            pstmt.setString(2, status.getName());
            pstmt.setLong(3, status.getUserStatusId());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_USER_STATUS)){
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
