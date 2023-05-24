package org.application;

import org.application.connection.ConnectionPool;
import org.application.dao.implementations.*;
import org.application.dao.interfaces.DAO;
import org.application.entities.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static <T> void viewAll(DAO<T> impl) {
        List<T> elements = impl.getAll();
        for (T e : elements) {
            System.out.println(e);
        }
    }

    public static <T> void viewGroup(List<T> list) {
        for (T e : list) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionPool.getConnection()) {
            System.out.println("\n\n--------------------------------------------------");
            System.out.println("Role DAO");
            System.out.println("--------------------------------------------------");
            RoleDAOImpl roleDAO = new RoleDAOImpl(conn);
            Role testRole = new Role("TEST", "Tester");

            System.out.println("\nCreate role");
            testRole = roleDAO.create(testRole);

            System.out.println("\nView all roles");
            viewAll(roleDAO);

            testRole.setName("Mega tester");
            System.out.println("\nUpdate role");
            roleDAO.update(testRole);

            System.out.println("\nView all roles");
            viewAll(roleDAO);


            System.out.println("\n\n--------------------------------------------------");
            System.out.println("Status DAO");
            System.out.println("--------------------------------------------------");
            UserStatusDAOImpl userStatusDAO = new UserStatusDAOImpl(conn);
            UserStatus testStatus = new UserStatus("TEST", "Being under test");

            System.out.println("\nCreate status");
            testStatus = userStatusDAO.create(testStatus);

            System.out.println("\nView all statuses");
            viewAll(userStatusDAO);

            testStatus.setCode("ARREST");
            testStatus.setName("Being under arrest!");
            System.out.println("\nUpdate status");
            userStatusDAO.update(testStatus);

            System.out.println("\nView all statuses");
            viewAll(userStatusDAO);


            System.out.println("\n\n--------------------------------------------------");
            System.out.println("Subject DAO");
            System.out.println("--------------------------------------------------");
            SubjectDAOImpl subjectDAO = new SubjectDAOImpl(conn);
            Subject testSubject = new Subject("Astrology", "Study on how position of stars affects human fate", false);

            System.out.println("\nCreate subject");
            testSubject = subjectDAO.create(testSubject);

            System.out.println("\nView all subjects");
            viewAll(subjectDAO);

            testSubject.setName("Programming");
            testSubject.setDescription("Hello world!");
            testSubject.setClosed(true);
            System.out.println("\nUpdate subject");
            subjectDAO.update(testSubject);

            System.out.println("\nView all subjects");
            viewAll(subjectDAO);

            System.out.println("\nFind subject by name 'Math'");
            System.out.println(subjectDAO.findByName("Math"));

            System.out.println("\nView all opened subjects (find by status)");
            viewGroup(subjectDAO.findByStatus(false));


            System.out.println("\n\n--------------------------------------------------");
            System.out.println("Specialization DAO");
            System.out.println("--------------------------------------------------");
            SpecializationDAOImpl specializationDAO = new SpecializationDAOImpl(conn);
            Specialization testSpecialization = new Specialization(111, "Anime expertise",
                    "Brand new field of study for truly passionate anime fans", 10);

            System.out.println("\nCreate specialization");
            testSpecialization = specializationDAO.create(testSpecialization);

            System.out.println("\nView all specializations");
            viewAll(specializationDAO);

            testSpecialization.setCode(333);
            testSpecialization.setName("Game development");
            testSpecialization.setDescription("Obviously something about developing games");
            testSpecialization.setLimit(20);
            System.out.println("\nUpdate specialization");
            specializationDAO.update(testSpecialization);

            System.out.println("\nView all specializations");
            viewAll(specializationDAO);

            System.out.println("\nFind specialization by code '122'");
            System.out.println(specializationDAO.findByCode(122));

            System.out.println("\nFind specialization by name 'Economics'");
            System.out.println(specializationDAO.findByName("Economics"));


            System.out.println("\n\n--------------------------------------------------");
            System.out.println("SubjectWeightUnit DAO");
            System.out.println("--------------------------------------------------");
            SubjectWeightUnitDAOImpl subjectWeightUnitDAO = new SubjectWeightUnitDAOImpl(conn);
            SubjectWeightUnit testSubjectWeight1 = new SubjectWeightUnit(testSubject, 0.5);
            SubjectWeightUnit testSubjectWeight2 = new SubjectWeightUnit(subjectDAO.findByName("Math"), 0.25);
            SubjectWeightUnit testSubjectWeight3 = new SubjectWeightUnit(subjectDAO.findByName("Art"), 0.25);

            System.out.println("\nView all subjectWeightUnits for specialization 'Game development'");
            viewGroup(subjectWeightUnitDAO.getBySpecialization(specializationDAO.findByName("Game development")));

            System.out.println("\nCreate subjectWeightUnits for 'Game development'");
            testSubjectWeight1 = subjectWeightUnitDAO.create(testSpecialization, testSubjectWeight1);
            testSubjectWeight2 = subjectWeightUnitDAO.create(testSpecialization, testSubjectWeight2);
            testSubjectWeight3 = subjectWeightUnitDAO.create(testSpecialization, testSubjectWeight3);

            System.out.println("\nView all subjectWeightUnits for specialization 'Game development'");
            viewGroup(subjectWeightUnitDAO.getBySpecialization(specializationDAO.findByName("Game development")));

            testSubjectWeight1.setSubjectWeight(0.45);
            testSubjectWeight2.setSubjectWeight(0.3);
            System.out.println("\nUpdate subjectWeightUnit for 'Game development'");
            subjectWeightUnitDAO.update(testSpecialization, testSubjectWeight1);
            subjectWeightUnitDAO.update(testSpecialization, testSubjectWeight2);

            System.out.println("\nView all subjectWeightUnits for specialization 'Game development'");
            viewGroup(subjectWeightUnitDAO.getBySpecialization(specializationDAO.findByName("Game development")));


            System.out.println("\n\n--------------------------------------------------");
            System.out.println("User DAO");
            System.out.println("--------------------------------------------------");
            UserDAOImpl userDAO = new UserDAOImpl(conn);
            User testUser = new User("testOverlord", "testiki1223", "Tester",
                    "Testerko", "tester@gmail.com", "380500505050",
                    testRole, testStatus, testSpecialization);

            System.out.println("\nCreate user");
            testUser = userDAO.create(testUser);

            System.out.println("\nView all users");
            viewAll(userDAO);

            testUser.setUsername("grandTESTER");
            testUser.setPassword("testuniaka11");
            System.out.println("\nUpdate user");
            userDAO.update(testUser);

            System.out.println("\nView all users");
            viewAll(userDAO);

            System.out.println("\nFind user by username 'stud'");
            System.out.println(userDAO.findByUsername("stud"));

            System.out.println("\nFind user by email 'beverly46@yahoo.com'");
            System.out.println(userDAO.findByEmail("beverly46@yahoo.com"));

            System.out.println("\nFind user by phone '380963432789'");
            System.out.println(userDAO.findByPhone("380963432789"));

            System.out.println("\nView all users with role 'STUDENT'");
            viewGroup(userDAO.findByRole(roleDAO.getById(2)));

            System.out.println("\nView all users with status 'REG'");
            viewGroup(userDAO.findByStatus(userStatusDAO.getById(2)));

            System.out.println("\nView all users with specialization 'None'");
            viewGroup(userDAO.findBySpecialization(specializationDAO.findByName("None")));


            System.out.println("\n\n--------------------------------------------------");
            System.out.println("SubjectScoreUnit DAO");
            System.out.println("--------------------------------------------------");
            SubjectScoreUnitDAOImpl subjectScoreUnitDAO = new SubjectScoreUnitDAOImpl(conn);
            SubjectScoreUnit testSubjectScore1 = new SubjectScoreUnit(testSubject, 80);
            SubjectScoreUnit testSubjectScore2 = new SubjectScoreUnit(subjectDAO.findByName("Math"), 90);
            SubjectScoreUnit testSubjectScore3 = new SubjectScoreUnit(subjectDAO.findByName("Art"), 60);

            System.out.println("\nView all subjectScoreUnits for user 'grandTESTER'");
            viewGroup(subjectScoreUnitDAO.getByUser(userDAO.findByUsername("grandTESTER")));

            System.out.println("\nCreate subjectScoreUnits for 'grandTESTER'");
            testSubjectScore1 = subjectScoreUnitDAO.create(testUser, testSubjectScore1);
            testSubjectScore2 = subjectScoreUnitDAO.create(testUser, testSubjectScore2);
            testSubjectScore3 = subjectScoreUnitDAO.create(testUser, testSubjectScore3);

            System.out.println("\nView all subjectScoreUnits for user 'grandTESTER'");
            viewGroup(subjectScoreUnitDAO.getByUser(userDAO.findByUsername("grandTESTER")));

            testSubjectScore2.setScore(100);
            System.out.println("\nUpdate subjectScoreUnit for 'grandTESTER'");
            subjectScoreUnitDAO.update(testUser, testSubjectScore2);

            System.out.println("\nView all subjectScoreUnits for user 'grandTESTER'");
            viewGroup(subjectScoreUnitDAO.getByUser(userDAO.findByUsername("grandTESTER")));


            System.out.println("\n\n\n--------------------------------------------------");
            System.out.println("DELETION TEST");
            System.out.println("--------------------------------------------------");

            System.out.println("\nDelete all subjectScoreUnits for user 'grandTESTER'");
            subjectScoreUnitDAO.delete(testUser, testSubjectScore1.getSubject());
            subjectScoreUnitDAO.delete(testUser, testSubjectScore2.getSubject());
            subjectScoreUnitDAO.delete(testUser, testSubjectScore3.getSubject());

            System.out.println("\nView all subjectScoreUnits for user 'grandTESTER'");
            viewGroup(subjectScoreUnitDAO.getByUser(userDAO.findByUsername("grandTESTER")));

            System.out.println("\nDelete user");
            userDAO.delete(testUser.getUserId());

            System.out.println("\nView all users");
            viewAll(userDAO);

            System.out.println("\nDelete all subjectWeightUnits for specialization 'Game development'");
            subjectWeightUnitDAO.delete(testSpecialization, testSubjectWeight1.getSubject());
            subjectWeightUnitDAO.delete(testSpecialization, testSubjectWeight2.getSubject());
            subjectWeightUnitDAO.delete(testSpecialization, testSubjectWeight3.getSubject());

            System.out.println("\nView all subjectWeightUnits for specialization 'Game development'");
            viewGroup(subjectWeightUnitDAO.getBySpecialization(specializationDAO.findByName("Game development")));

            System.out.println("\nDelete specialization");
            specializationDAO.delete(testSpecialization.getSpecializationId());

            System.out.println("\nView all specializations");
            viewAll(specializationDAO);

            System.out.println("\nDelete subject");
            subjectDAO.delete(testSubject.getSubjectId());

            System.out.println("\nView all subjects");
            viewAll(subjectDAO);

            System.out.println("\nDelete status");
            userStatusDAO.delete(testStatus.getUserStatusId());

            System.out.println("\nView all statuses");
            viewAll(userStatusDAO);

            System.out.println("\nDelete role");
            roleDAO.delete(testRole.getRoleId());

            System.out.println("\nView all roles");
            viewAll(roleDAO);
        } finally {
            ConnectionPool.closePool();
        }
    }
}
