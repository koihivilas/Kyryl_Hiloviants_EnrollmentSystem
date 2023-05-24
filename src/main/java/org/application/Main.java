package org.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.application.dao.implementations.*;
import org.application.entities.*;

import java.util.List;

public class Main {

    public static <T> void viewGroup(List<T> list) {
        for (T e : list) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EnrollmentSystem");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        System.out.println("\n\n--------------------------------------------------");
        System.out.println("Role DAO");
        System.out.println("--------------------------------------------------");
        Role testRole = new Role();
        testRole.setCode("TEST");
        testRole.setName("Tester");

        System.out.println("\nCreate role");
        testRole = new RoleDAOImpl(em).create(testRole);

        System.out.println("\nView all roles");
        viewGroup(new RoleDAOImpl(em).getAll());

        testRole.setName("Mega tester");
        System.out.println("\nUpdate role");
        new RoleDAOImpl(em).update(testRole);

        System.out.println("\nView all roles");
        viewGroup(new RoleDAOImpl(em).getAll());

        System.out.println("\nFind role by code 'STUDENT'");
        System.out.println(new RoleDAOImpl(em).findByCode("STUDENT"));


        System.out.println("\n\n--------------------------------------------------");
        System.out.println("Status DAO");
        System.out.println("--------------------------------------------------");
        UserStatus testStatus = new UserStatus();
        testStatus.setCode("TEST");
        testStatus.setName("Being under test");

        System.out.println("\nCreate status");
        testStatus = new UserStatusDAOImpl(em).create(testStatus);

        System.out.println("\nView all statuses");
        viewGroup(new UserStatusDAOImpl(em).getAll());

        testStatus.setCode("ARREST");
        testStatus.setName("Being under arrest!");
        System.out.println("\nUpdate status");
        new UserStatusDAOImpl(em).update(testStatus);

        System.out.println("\nView all statuses");
        viewGroup(new UserStatusDAOImpl(em).getAll());

        System.out.println("\nFind user status by code 'ENR'");
        System.out.println(new UserStatusDAOImpl(em).findByCode("ENR"));


        System.out.println("\n\n--------------------------------------------------");
        System.out.println("Subject DAO");
        System.out.println("--------------------------------------------------");
        Subject testSubject = new Subject();
        testSubject.setName("Astrology");
        testSubject.setDescription("Study on how position of stars affects human fate");
        testSubject.setClosed(false);

        System.out.println("\nCreate subject");
        testSubject = new SubjectDAOImpl(em).create(testSubject);

        System.out.println("\nView all subjects");
        viewGroup(new SubjectDAOImpl(em).getAll());

        testSubject.setName("Programming");
        testSubject.setDescription("Hello world!");
        testSubject.setClosed(true);
        System.out.println("\nUpdate subject");
        new SubjectDAOImpl(em).update(testSubject);

        System.out.println("\nView all subjects");
        viewGroup(new SubjectDAOImpl(em).getAll());

        System.out.println("\nFind subject by name 'Math'");
        System.out.println(new SubjectDAOImpl(em).findByName("Math"));

        System.out.println("\nView all opened subjects (find by status)");
        viewGroup(new SubjectDAOImpl(em).findByStatus(false));


        System.out.println("\n\n--------------------------------------------------");
        System.out.println("Specialization DAO");
        System.out.println("--------------------------------------------------");
        Specialization testSpecialization = new Specialization();
        testSpecialization.setCode(111);
        testSpecialization.setName("Anime expertise");
        testSpecialization.setDescription("Brand new field of study for truly passionate anime fans");
        testSpecialization.setLimit(10);

        System.out.println("\nCreate specialization");
        testSpecialization = new SpecializationDAOImpl(em).create(testSpecialization);

        System.out.println("\nView all specializations");
        viewGroup(new SpecializationDAOImpl(em).getAll());

        testSpecialization.setCode(333);
        testSpecialization.setName("Game development");
        testSpecialization.setDescription("Obviously something about developing games");
        testSpecialization.setLimit(20);
        System.out.println("\nUpdate specialization");
        new SpecializationDAOImpl(em).update(testSpecialization);

        System.out.println("\nView all specializations");
        viewGroup(new SpecializationDAOImpl(em).getAll());

        System.out.println("\nFind specialization by code '122'");
        Specialization spec = new SpecializationDAOImpl(em).findByCode(122);
        System.out.println(spec);

        System.out.println("\nFind specialization by name 'Economics'");
        System.out.println(new SpecializationDAOImpl(em).findByName("Economics"));


        System.out.println("\n\n--------------------------------------------------");
        System.out.println("SpecializationSubject DAO");
        System.out.println("--------------------------------------------------");
        List <SpecializationSubject> specializationSubjects = new SpecializationSubjectDAOImpl(em).getBySpecializationId(spec.getSpecializationId());

        System.out.println("Specialization '" + spec.getName() + "' requires you to pass test from these subjects (with according weights):");
        for (var ss : specializationSubjects) {
            System.out.println(new SubjectDAOImpl(em).getById(ss.getSubjectId()).getName() + " - " + ss.getSubjectWeight());
        }
        var specSub1 = new SpecializationSubject(testSpecialization.getSpecializationId(), testSubject.getSubjectId(), 0.5);
        var specSub2 = new SpecializationSubject(testSpecialization.getSpecializationId(), new SubjectDAOImpl(em).findByName("Math").getSubjectId(), 0.4);
        var specSub3 = new SpecializationSubject(testSpecialization.getSpecializationId(), new SubjectDAOImpl(em).findByName("Art").getSubjectId(), 0.1);

        System.out.println("\nView all specializationSubjects for specialization 'Game development'");
        viewGroup(new SpecializationSubjectDAOImpl(em).getBySpecializationId(testSpecialization.getSpecializationId()));

        System.out.println("\nCreate specializationSubjects for 'Game development'");
        specSub1 = new SpecializationSubjectDAOImpl(em).create(specSub1);
        specSub2 = new SpecializationSubjectDAOImpl(em).create(specSub2);
        specSub3 = new SpecializationSubjectDAOImpl(em).create(specSub3);

        System.out.println("\nView all specializationSubjects for specialization 'Game development'");
        var specSubs = new SpecializationSubjectDAOImpl(em).getBySpecializationId(testSpecialization.getSpecializationId());
        viewGroup(specSubs);
        System.out.println("Specialization '" + testSpecialization.getName() + "' requires you to pass test from these subjects (with according weights):");
        for (var ss : specSubs) {
            System.out.println(new SubjectDAOImpl(em).getById(ss.getSubjectId()).getName() + " - " + ss.getSubjectWeight());
        }

        System.out.println("\n\n--------------------------------------------------");
        System.out.println("User DAO");
        System.out.println("--------------------------------------------------");
        User testUser = new User();
        testUser.setUsername("testOverlord");
        testUser.setPassword("testiki1223");
        testUser.setFirstName("Tester");
        testUser.setLastName("Testerko");
        testUser.setEmail("tester@gmail.com");
        testUser.setPhone("380500505050");
        testUser.setRole(testRole);
        testUser.setUserStatus(testStatus);
        testUser.setSpecialization(testSpecialization); // Should I create separate constructor without id???

        System.out.println("\nCreate user");
        testUser = new UserDAOImpl(em).create(testUser);

        System.out.println("\nView all users");
        viewGroup(new UserDAOImpl(em).getAll());

        testUser.setUsername("grandTESTER");
        testUser.setPassword("testuniaka11");
        System.out.println("\nUpdate user");
        new UserDAOImpl(em).update(testUser);

        System.out.println("\nView all users");
        viewGroup(new UserDAOImpl(em).getAll());

        System.out.println("\nFind user by username 'stud'");
        System.out.println(new UserDAOImpl(em).findByUsername("stud"));

        System.out.println("\nFind user by email 'beverly46@yahoo.com'");
        System.out.println(new UserDAOImpl(em).findByEmail("beverly46@yahoo.com"));

        System.out.println("\nFind user by phone '380963432789'");
        var usert = new UserDAOImpl(em).findByPhone("380963432789");
        System.out.println(usert);

        System.out.println("\nView all users with role 'STUDENT'");
        viewGroup(new UserDAOImpl(em).findByRole(new RoleDAOImpl(em).findByCode("STUDENT")));

        System.out.println("\nView all users with status 'REJ'");
        viewGroup(new UserDAOImpl(em).findByStatus(new UserStatusDAOImpl(em).findByCode("REJ")));

        System.out.println("\nView all users with specialization 'None'");
        viewGroup(new UserDAOImpl(em).findBySpecialization(new SpecializationDAOImpl(em).findByName("None")));


        System.out.println("\n\n--------------------------------------------------");
        System.out.println("UserSubject DAO");
        System.out.println("--------------------------------------------------");
        List <UserSubject> userSubjects = new UserSubjectDAOImpl(em).getByUserId(usert.getUserId());

        System.out.println("User '" + usert.getUsername() + "' did tests from these subjects (with according scores):");
        for (var us : userSubjects) {
            System.out.println(new SubjectDAOImpl(em).getById(us.getSubjectId()).getName() + " - " + us.getScore());
        }
        var userSub1 = new UserSubject(testUser.getUserId(), testSubject.getSubjectId(), 89);
        var userSub2 = new UserSubject(testUser.getUserId(), new SubjectDAOImpl(em).findByName("Math").getSubjectId(), 90);
        var userSub3 = new UserSubject(testUser.getUserId(), new SubjectDAOImpl(em).findByName("Art").getSubjectId(), 75);

        System.out.println("\nView all userSubjects for user 'Tester'");
        viewGroup(new UserSubjectDAOImpl(em).getByUserId(testUser.getUserId()));

        System.out.println("\nCreate userSubjects for 'Tester'");
        userSub1 = new UserSubjectDAOImpl(em).create(userSub1);
        userSub2 = new UserSubjectDAOImpl(em).create(userSub2);
        userSub3 = new UserSubjectDAOImpl(em).create(userSub3);

        System.out.println("\nView all userSubjects for user 'Tester'");
        var userSubs = new UserSubjectDAOImpl(em).getByUserId(testUser.getUserId());
        viewGroup(userSubs);
        System.out.println("User '" + testUser.getUsername() + "' did tests from these subjects (with according scores):");
        for (var us : userSubs) {
            System.out.println(new SubjectDAOImpl(em).getById(us.getSubjectId()).getName() + " - " + us.getScore());
        }

        System.out.println("\n\n\n--------------------------------------------------");
        System.out.println("DELETION TEST");
        System.out.println("--------------------------------------------------");

        System.out.println("\nDelete all userSubjects for user 'grandTESTER'");
        new UserSubjectDAOImpl(em).delete(new UserSubjectId(userSub1.getUserId(), userSub1.getSubjectId()));
        new UserSubjectDAOImpl(em).delete(new UserSubjectId(userSub2.getUserId(), userSub2.getSubjectId()));
        new UserSubjectDAOImpl(em).delete(new UserSubjectId(userSub3.getUserId(), userSub3.getSubjectId()));

        System.out.println("\nView all userSubjects for user 'grandTESTER'");
        viewGroup(new UserSubjectDAOImpl(em).getByUserId(new UserDAOImpl(em).findByUsername("grandTESTER").getUserId()));

        System.out.println("\nDelete user");
        new UserDAOImpl(em).delete(testUser.getUserId());

        System.out.println("\nView all users");
        viewGroup(new UserDAOImpl(em).getAll());

        System.out.println("\nDelete all specializationSubjects for specialization 'Game development'");
        new SpecializationSubjectDAOImpl(em).delete(new SpecializationSubjectId(specSub1.getSpecializationId(), specSub1.getSubjectId()));
        new SpecializationSubjectDAOImpl(em).delete(new SpecializationSubjectId(specSub2.getSpecializationId(), specSub2.getSubjectId()));
        new SpecializationSubjectDAOImpl(em).delete(new SpecializationSubjectId(specSub3.getSpecializationId(), specSub3.getSubjectId()));

        System.out.println("\nView all specializationSubjects for specialization 'Game development'");
        viewGroup(new SpecializationSubjectDAOImpl(em).getBySpecializationId(testSpecialization.getSpecializationId()));

        System.out.println("\nDelete specialization");
        new SpecializationDAOImpl(em).delete(testSpecialization.getSpecializationId());

        System.out.println("\nView all specializations");
        viewGroup(new SpecializationDAOImpl(em).getAll());

        System.out.println("\nDelete subject");
        new SubjectDAOImpl(em).delete(testSubject.getSubjectId());

        System.out.println("\nView all subjects");
        viewGroup(new SubjectDAOImpl(em).getAll());

        System.out.println("\nDelete status");
        new UserStatusDAOImpl(em).delete(testStatus.getUserStatusId());

        System.out.println("\nView all statuses");
        viewGroup(new UserStatusDAOImpl(em).getAll());

        System.out.println("\nDelete role");
        new RoleDAOImpl(em).delete(testRole.getRoleId());

        System.out.println("\nView all roles");
        viewGroup(new RoleDAOImpl(em).getAll());

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
