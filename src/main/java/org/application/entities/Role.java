package org.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private long roleId;
  private String code;
  private String name;

//  @Override
//  public String toString() {
//    return "Role{roleId: " + this.roleId + ", code: '" + this.code + "', name: '" + this.name + "'}";
//  }

//  public Role(String code, String name) {
//    this.code = code;
//    this.name = name;
//  }

}
