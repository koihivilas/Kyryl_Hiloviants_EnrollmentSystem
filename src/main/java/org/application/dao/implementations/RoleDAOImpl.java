package org.application.dao.implementations;

import org.application.dao.interfaces.RoleDAO;
import org.application.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM roles WHERE role_id = ?";
    private static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM roles";
    private static final String SQL_SELECT_ROLE_BY_CODE = "SELECT * FROM roles WHERE code = ?";
    private static final String SQL_ADD_ROLE = "INSERT INTO roles (`code`, `name`) VALUES (?,?)";
    private static final String SQL_UPDATE_ROLE = "UPDATE roles SET `code` = ?, `name` = ? WHERE role_id = ?";
    private static final String SQL_DELETE_ROLE = "DELETE FROM roles WHERE role_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

    public RoleDAOImpl(Connection conn) {
        this.conn = conn;
    }


    private Role getRole(ResultSet rs) {
        try {
            long id = rs.getLong("role_id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            return new Role(id, code, name);
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Role getById(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ROLE_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getRole(rs);
                }
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Role> getAll() {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL_ROLES)) {
            List<Role> roles = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Role role = getRole(rs);
                    roles.add(role);
                }
                return roles;
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Role findByCode(String code) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ROLE_BY_CODE)) {
            pstmt.setString(1, code);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getRole(rs);
                }
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Role create(Role role) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_ADD_ROLE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, role.getCode());
            pstmt.setString(2, role.getName());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    role.setRoleId(rs.getLong(1));
                    return role;
                }
            }
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Role role) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_ROLE)) {
            pstmt.setString(1, role.getCode());
            pstmt.setString(2, role.getName());
            pstmt.setLong(3, role.getRoleId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_ROLE)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            logger.error("Error: " + ex.getMessage());
        }
    }
}
