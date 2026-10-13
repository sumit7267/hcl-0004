package com.hcl.library.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;


public class InMemoryRepository<T, ID> implements Repository<T, ID> {

    private final Map<ID, T> storage = new HashMap<>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) {
        storage.put(idExtractor.apply(entity), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean deleteById(ID id) {
        return storage.remove(id) != null;
    }

    @Override
    public int removeIf(Predicate<T> condition) {
        int sizeBefore = storage.size();
        storage.values().removeIf(condition);
        return sizeBefore - storage.size();
    }
}