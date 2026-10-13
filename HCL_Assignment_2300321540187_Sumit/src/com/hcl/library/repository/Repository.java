package com.hcl.library.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public interface Repository<T, ID> {

    void save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    boolean deleteById(ID id);

    int removeIf(Predicate<T> condition);
}