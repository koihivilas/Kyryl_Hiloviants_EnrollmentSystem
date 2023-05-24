package org.application.dao.implementations;

import org.application.dao.interfaces.UserDAO;
import org.application.entities.Role;
import org.application.entities.Specialization;
import org.application.entities.User;
import org.application.entities.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_SELECT_USER_BY_PHONE = "SELECT * FROM users WHERE phone = ?";
    private static final String SQL_SELECT_USERS_BY_ROLE = "SELECT * FROM users WHERE role_id = ?";
    private static final String SQL_SELECT_USERS_BY_STATUS = "SELECT * FROM users WHERE user_status_id = ?";
    private static final String SQL_SELECT_USERS_BY_SPECIALIZATION = "SELECT * FROM users WHERE specialization_id = ?";
    private static final String SQL_ADD_USER = "INSERT INTO users (username, `password`, first_name, last_name," +
            "email, phone, role_id, user_status_id, specialization_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET username = ?, `password` = ?, " +
            "first_name = ?, last_name = ?, email = ?, phone = ?, role_id = ?, user_status_id = ?, " +
            "specialization_id = ? WHERE user_id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";

    private final Connection conn;
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
    public UserDAOImpl(Connection conn){
        this.conn = conn;
    }

    private User getUser(ResultSet rs) {
        try {
            long id = rs.getLong("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            Role role = new RoleDAOImpl(conn).getById(rs.getLong("role_id"));
            UserStatus status = new UserStatusDAOImpl(conn).getById(rs.getLong("user_status_id"));
            Specialization specialization = new SpecializationDAOImpl(conn).getById(rs.getLong("specialization_id"));
            return new User(id, username, password, firstName, lastName, email, phone, role, status, specialization);
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getById(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_USER_BY_ID)){
            pstmt.setLong(1, id);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getUser(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL_USERS)){
            List<User> users = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    User user = getUser(rs);
                    users.add(user);
                }
                return users;
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    private User findByUniqueString(String sql, String uniqueString) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, uniqueString);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getUser(rs);
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return findByUniqueString(SQL_SELECT_USER_BY_USERNAME, username);
    }

    @Override
    public User findByEmail(String email) {
        return findByUniqueString(SQL_SELECT_USER_BY_EMAIL, email);
    }

    @Override
    public User findByPhone(String phone) {
        return findByUniqueString(SQL_SELECT_USER_BY_PHONE, phone);
    }

    private List<User> findByGroup(String sql, long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    User user = getUser(rs);
                    users.add(user);
                }
                return users;
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findByRole(Role role) {
        return findByGroup(SQL_SELECT_USERS_BY_ROLE, role.getRoleId());
    }

    @Override
    public List<User> findByStatus(UserStatus status) {
        return findByGroup(SQL_SELECT_USERS_BY_STATUS, status.getUserStatusId());
    }

    @Override
    public List<User> findBySpecialization(Specialization specialization) {
        return findByGroup(SQL_SELECT_USERS_BY_SPECIALIZATION, specialization.getSpecializationId());
    }

    private void createUpdate(PreparedStatement pstmt, User user) throws SQLException {
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3, user.getFirstName());
        pstmt.setString(4, user.getLastName());
        pstmt.setString(5, user.getEmail());
        pstmt.setString(6, user.getPhone());
        pstmt.setLong(7, user.getRole().getRoleId());
        pstmt.setLong(8, user.getUserStatus().getUserStatusId());
        pstmt.setLong(9, user.getSpecialization().getSpecializationId());
    }

    @Override
    public User create(User user) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS)){
            createUpdate(pstmt, user);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setUserId(rs.getLong(1));
                }
            }
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_USER)){
            createUpdate(pstmt, user);
            pstmt.setLong(10, user.getUserId());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE_USER)){
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
