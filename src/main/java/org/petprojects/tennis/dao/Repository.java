package org.petprojects.tennis.dao;

import jakarta.persistence.Entity;
import org.petprojects.tennis.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository <K extends Serializable, E extends BaseEntity<K>> {
    E create(E entity);
    Optional<E> findById(K id);
    void update(E entity);
    void delete(E entity);
    List<E> findAll();
}
