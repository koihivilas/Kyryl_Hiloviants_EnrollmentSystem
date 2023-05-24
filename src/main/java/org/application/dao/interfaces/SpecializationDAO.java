package org.application.dao.interfaces;

import org.application.entities.Specialization;
import org.application.entities.SubjectWeightUnit;

import java.util.List;

public interface SpecializationDAO extends DAO<Specialization>{
    Specialization findByCode(int code);
    Specialization findByName(String name);
}
