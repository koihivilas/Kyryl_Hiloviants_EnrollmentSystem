package org.application.dao.interfaces;

import org.application.entities.Specialization;
import org.application.entities.Subject;
import org.application.entities.SubjectWeightUnit;

import java.util.List;

public interface SubjectWeightUnitDAO extends DAO<SubjectWeightUnit>{
    List<SubjectWeightUnit> getBySpecialization(Specialization specialization);
    SubjectWeightUnit create(Specialization specialization, SubjectWeightUnit subjectWeightUnit);
    void update(Specialization specialization, SubjectWeightUnit subjectWeightUnit);
    void delete(Specialization specialization, Subject subject);
}
