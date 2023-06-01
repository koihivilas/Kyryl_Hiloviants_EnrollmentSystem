package org.application.dao.interfaces;

import org.application.models.Specialization;

public interface SpecializationDAO extends DAO<Specialization>{
    Specialization findByCode(int code);
    Specialization findByName(String name);
}
