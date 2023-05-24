package org.application.dao.interfaces;

import java.util.List;

public interface DAO <T> {
    T getById(long id);
    List<T> getAll();
    T create(T t);
    void update(T t);
    void delete(long id);
}
