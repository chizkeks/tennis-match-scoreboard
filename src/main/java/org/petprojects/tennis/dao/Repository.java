package org.petprojects.tennis.dao;

import org.petprojects.tennis.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface Repository <K extends Serializable, E extends BaseEntity<K>> {
    E create(E entity);
    E findById(K id);
    void update(E entity);
    void delete(E entity);
    List<E> findAll();
}
