package org.application.dao.interfaces;

import java.util.List;

public interface DAO <T> {
    T getById(long id);
    List<T> getAll();

    // There were two separate methods create() and update()
    T save(T t);
    void delete(long id);
}
