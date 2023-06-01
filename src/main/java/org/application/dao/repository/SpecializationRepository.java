package org.application.dao.repository;

import org.application.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Specialization findByCode(int code);
    Specialization findByName(String name);
}
