package org.application.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_subject")
@IdClass(UserSubjectId.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserSubject {
    @Id
    @Column(name = "user_id")
    private long userId;
    @Id
    @Column(name = "subject_id")
    private long subjectId;
    private int score;
}
