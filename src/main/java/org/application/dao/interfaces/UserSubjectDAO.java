package org.application.dao.interfaces;

import org.application.entities.UserSubject;
import org.application.entities.UserSubjectId;

import java.util.List;

public interface UserSubjectDAO {
    UserSubject get(UserSubjectId id);
    List<UserSubject> getByUserId(long userId);
    List<UserSubject> getAll();
    UserSubject create(UserSubject userSubject);
    void update(UserSubject userSubject);
    void delete(UserSubjectId id);
}
