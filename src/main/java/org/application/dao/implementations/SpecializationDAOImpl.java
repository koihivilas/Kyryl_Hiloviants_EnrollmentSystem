package org.application.dao.implementations;

import org.application.dao.interfaces.SpecializationDAO;
import org.application.dao.repository.SpecializationRepository;
import org.application.models.Specialization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpecializationDAOImpl implements SpecializationDAO {
    private final SpecializationRepository specializationRepository;
    public SpecializationDAOImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public Specialization getById(long id) {
        return specializationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization findByCode(int code) {
        return specializationRepository.findByCode(code);
    }

    @Override
    public Specialization findByName(String name) {
        return specializationRepository.findByName(name);
    }

    @Override
    public Specialization save(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    public void delete(long id) {
        specializationRepository.deleteById(id);
    }
}
