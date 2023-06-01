package org.application.dao.repository;

import org.application.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
    List<Subject> findByClosed(boolean closed);
}
