package org.application.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "specialization_subject")
@IdClass(SpecializationSubjectId.class)
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationSubject {
    @Id
    @Column(name = "specialization_id")
    private long specializationId;
    @Id
    @Column(name = "subject_id")
    private long subjectId;
    @Column(name = "subject_weight")
    private double subjectWeight;
}
