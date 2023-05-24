package org.application.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "specializations")
@AllArgsConstructor
@NoArgsConstructor
public class Specialization {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "specialization_id")
  private long specializationId;
  private int code;
  private String name;
  private String description;
  @Column(name = "`limit`")
  private int limit;
  @OneToMany
  @JoinColumn(name = "specialization_id", referencedColumnName = "specialization_id")
  private List<SpecializationSubject> specializationSubjects;

//  @Override
//  public String toString() {
//    return "Specialization{specializationId: " + this.specializationId + ", code: '"
//            + this.code + "', name: '" + this.name + "', description: '"
//            + this.description + "', limit: " + this.limit + "}";
//  }
//
//  public Specialization(int code, String name, String description, int limit) {
//    this.code = code;
//    this.name = name;
//    this.description = description;
//    this.limit = limit;
//  }
}
