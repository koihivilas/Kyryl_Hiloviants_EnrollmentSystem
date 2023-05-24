package org.application.entities;


import java.util.List;

public class User {
  private long userId;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private Role role;
  private UserStatus userStatus;
  private Specialization specialization;
  private List<SubjectScoreUnit> subjectScoreUnits;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }


  public UserStatus getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(UserStatus userStatus) {
    this.userStatus = userStatus;
  }


  public Specialization getSpecialization() {
    return specialization;
  }

  public void setSpecialization(Specialization specialization) {
    this.specialization = specialization;
  }


  public List<SubjectScoreUnit> getSubjectScoreUnits() {
    return subjectScoreUnits;
  }

  public void setSubjectScoreUnits(List<SubjectScoreUnit> subjectScoreUnits) {
    this.subjectScoreUnits = subjectScoreUnits;
  }

  @Override
  public String toString() {
    return "User{userId: " + userId + ", username: '" + username +
            "', password: '" + password + "', firstName: '" + firstName +
            "', lastName: '" + lastName + "', email: '" + email +
            "', phone: '" + phone + "', role: " + role +
            ", userStatus: " + userStatus + ", specialization: " + specialization + "}";
  }


  public User(long userId, String username, String password, String firstName,
              String lastName, String email, String phone, Role role,
              UserStatus userStatus, Specialization specialization) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.userStatus = userStatus;
    this.specialization = specialization;
  }

  public User(String username, String password,
              String firstName, String lastName, String email,
              String phone, Role role, UserStatus userStatus,
              Specialization specialization) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.userStatus = userStatus;
    this.specialization = specialization;
  }

  public User() {
  }
}
