package org.application.dao.interfaces;

import org.application.entities.Subject;
import org.application.entities.SubjectScoreUnit;
import org.application.entities.User;

import java.util.List;

public interface SubjectScoreUnitDAO extends DAO<SubjectScoreUnit>{
    List<SubjectScoreUnit> getByUser(User user);
    SubjectScoreUnit create(User user, SubjectScoreUnit subjectScoreUnit);
    void update(User user, SubjectScoreUnit subjectScoreUnit);
    void delete(User user, Subject subject);
}
