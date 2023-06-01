package org.application.dao.implementations;

import org.application.dao.interfaces.SubjectDAO;
import org.application.dao.repository.SubjectRepository;
import org.application.models.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectDAOImpl implements SubjectDAO {
    private final SubjectRepository subjectRepository;
    public SubjectDAOImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject getById(long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name);
    }

    @Override
    public List<Subject> findByStatus(boolean closed) {
        return subjectRepository.findByClosed(closed);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void delete(long id) {
        subjectRepository.deleteById(id);
    }
}
