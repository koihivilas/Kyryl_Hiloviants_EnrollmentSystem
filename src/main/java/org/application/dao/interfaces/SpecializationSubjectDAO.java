package org.application.dao.interfaces;

import org.application.entities.SpecializationSubject;
import org.application.entities.SpecializationSubjectId;

import java.util.List;

public interface SpecializationSubjectDAO {
    SpecializationSubject get(SpecializationSubjectId id);
    List<SpecializationSubject> getBySpecializationId(long specializationId);
    List<SpecializationSubject> getAll();
    SpecializationSubject create(SpecializationSubject specializationSubject);
    void update(SpecializationSubject specializationSubject);
    void delete(SpecializationSubjectId id);
}
