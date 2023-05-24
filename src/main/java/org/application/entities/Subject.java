package org.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "subject_id")
  private long subjectId;
  private String name;
  private String description;
  private boolean closed;

//  @Override
//  public String toString() {
//    return "Subject{subjectId: " + this.subjectId + ", name: '" + this.name + "', description: '"
//            + this.description + "', closed: " + this.closed + "}";
//  }

//  public Subject(String name, String description, boolean closed) {
//    this.name = name;
//    this.description = description;
//    this.closed = closed;
//  }

}
