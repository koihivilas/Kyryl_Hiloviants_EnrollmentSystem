package org.application.services;

import jakarta.transaction.Transactional;
import org.application.dao.interfaces.SubjectDAO;
import org.application.dao.interfaces.UserSubjectDAO;
import org.application.models.DTO.SubjectDTO;
import org.application.models.Subject;
import org.application.models.UserSubject;
import org.application.models.UserSubjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SubjectService {
    private final SubjectDAO subjectDAO;
    private final UserSubjectDAO userSubjectDAO;

    public SubjectService(SubjectDAO subjectDAO, UserSubjectDAO userSubjectDAO) {
        this.subjectDAO = subjectDAO;
        this.userSubjectDAO = userSubjectDAO;
    }

    public Subject getSubjectById(long id) {
        return subjectDAO.getById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.getAll();
    }

    public List<SubjectDTO> getSubjectsByUser(long userId) {
        var subjects = getAllSubjects();
        List<SubjectDTO> subjectDTOs = new java.util.ArrayList<>();
        for (var s : subjects) {
            var subjectDTO = new SubjectDTO(s.getSubjectId(), s.getName(), s.getDescription(), s.isClosed(), isAvailable(userId, s.getSubjectId()));
            subjectDTOs.add(subjectDTO);
        }

        return subjectDTOs;
    }

    public List<Subject> getAllOpenedSubjects() {
        return subjectDAO.findByStatus(false);
    }

    public void changeScore(long userId, long subjectId, int score) {
        UserSubject us = userSubjectDAO.get(new UserSubjectId(userId, subjectId));

        if (us == null) {
            us = new UserSubject(userId, subjectId, score);
            userSubjectDAO.save(us);
            return;
        }

        us.setScore(score);
        userSubjectDAO.save(us);
    }

    public boolean isAvailable(long userId, long subjectId) {
        var userSubject = userSubjectDAO.get(new UserSubjectId(userId, subjectId));
        if (userSubject == null) {
            return true;
        }

        return userSubject.getScore() != -1;
    }
}
