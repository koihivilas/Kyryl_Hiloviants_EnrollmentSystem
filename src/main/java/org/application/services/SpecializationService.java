package org.application.services;

import jakarta.transaction.Transactional;
import org.application.dao.interfaces.SpecializationDAO;
import org.application.models.Specialization;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SpecializationService {
    private final SpecializationDAO specializationDAO;
    public SpecializationService(SpecializationDAO specializationDAO) {
        this.specializationDAO = specializationDAO;
    }

    public Specialization getSpecializationByCode(int code) {
        return specializationDAO.findByCode(code);
    }

    public List<Specialization> getAllSpecializations() {
        return specializationDAO.getAll();
    }

    public List<Specialization> getAllOpenedSpecializations() {
        var specializations = specializationDAO.getAll();
        specializations.remove(0);
        return specializations;
    }
}
