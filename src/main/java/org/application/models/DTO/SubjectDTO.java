package org.application.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectDTO {
    private long subjectId;
    private String name;
    private String description;
    private boolean closed;
    private boolean isAvailable;
}