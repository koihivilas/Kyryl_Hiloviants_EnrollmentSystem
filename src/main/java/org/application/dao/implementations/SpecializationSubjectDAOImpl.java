package org.application.dao.implementations;

import org.application.dao.interfaces.SpecializationSubjectDAO;
import org.application.dao.repository.SpecializationSubjectRepository;
import org.application.models.SpecializationSubject;
import org.application.models.SpecializationSubjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecializationSubjectDAOImpl implements SpecializationSubjectDAO {
    private final SpecializationSubjectRepository specializationSubjectRepository;
    public SpecializationSubjectDAOImpl(SpecializationSubjectRepository specializationSubjectRepository) {
        this.specializationSubjectRepository = specializationSubjectRepository;
    }

    @Override
    public SpecializationSubject get(SpecializationSubjectId id) {
        return specializationSubjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<SpecializationSubject> getBySpecializationId(long specializationId) {
        return specializationSubjectRepository.findBySpecializationId(specializationId);
    }

    @Override
    public List<SpecializationSubject> getAll() {
        return specializationSubjectRepository.findAll();
    }

    @Override
    public SpecializationSubject save(SpecializationSubject specializationSubject) {
        return specializationSubjectRepository.save(specializationSubject);
    }

    @Override
    public void delete(SpecializationSubjectId id) {
        specializationSubjectRepository.deleteById(id);
    }
}
