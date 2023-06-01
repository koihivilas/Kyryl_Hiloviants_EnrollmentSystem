package org.application.dao.interfaces;

import org.application.models.Subject;

import java.util.List;

public interface SubjectDAO extends DAO<Subject> {
    Subject findByName(String name);
    List<Subject> findByStatus(boolean closed);
}
