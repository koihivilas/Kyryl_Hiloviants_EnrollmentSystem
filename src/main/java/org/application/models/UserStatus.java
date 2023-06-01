package org.application.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_statuses")
@AllArgsConstructor
@NoArgsConstructor
public class UserStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_status_id")
  private long userStatusId;
  private String code;
  private String name;
}
