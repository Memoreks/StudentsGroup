package org.example.studentsgroup.services;

import java.util.Collection;

public interface CRUDService<T> {
    T getById(Integer id);
    Collection<T> getAll();
    T create(T item);
}
