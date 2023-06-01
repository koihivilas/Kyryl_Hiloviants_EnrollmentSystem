package org.application.dao.interfaces;

import org.application.models.UserSubject;
import org.application.models.UserSubjectId;

import java.util.List;

public interface UserSubjectDAO {
    UserSubject get(UserSubjectId id);
    List<UserSubject> getByUserId(long userId);
    List<UserSubject> getAll();
    UserSubject save(UserSubject userSubject);
    void delete(UserSubjectId id);
}
