package org.application.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationSubjectId implements Serializable {
    private long specializationId;
    private long subjectId;
}
