package org.application.dao.interfaces;

import org.application.models.SpecializationSubject;
import org.application.models.SpecializationSubjectId;

import java.util.List;

public interface SpecializationSubjectDAO {
    SpecializationSubject get(SpecializationSubjectId id);
    List<SpecializationSubject> getBySpecializationId(long specializationId);
    List<SpecializationSubject> getAll();
    SpecializationSubject save(SpecializationSubject specializationSubject);
    void delete(SpecializationSubjectId id);
}
