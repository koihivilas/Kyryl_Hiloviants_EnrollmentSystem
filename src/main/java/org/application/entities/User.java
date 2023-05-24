package org.application.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long userId;
  private String username;
  private String password;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  private String email;
  private String phone;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;
  @ManyToOne
  @JoinColumn(name = "user_status_id")
  private UserStatus userStatus;
  @ManyToOne
  @JoinColumn(name = "specialization_id")
  private Specialization specialization;
  @OneToMany
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private List<UserSubject> userSubjects;

//  @Override
//  public String toString() {
//    return "User{userId: " + userId + ", username: '" + username +
//            "', password: '" + password + "', firstName: '" + firstName +
//            "', lastName: '" + lastName + "', email: '" + email +
//            "', phone: '" + phone + "', role: " + role +
//            ", userStatus: " + userStatus + ", specialization: " + specialization + "}";
//  }
//
//  public User(String username, String password,
//              String firstName, String lastName, String email,
//              String phone, Role role, UserStatus userStatus,
//              Specialization specialization) {
//    this.username = username;
//    this.password = password;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.email = email;
//    this.phone = phone;
//    this.role = role;
//    this.userStatus = userStatus;
//    this.specialization = specialization;
//  }
}
