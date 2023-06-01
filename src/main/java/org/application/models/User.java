package org.application.models;

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
}
