package org.petprojects.tennis.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.petprojects.tennis.entity.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {
    private final Class<E> entityClass;
    private final Session session;


    @Override
    public E create(E entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(session.find(entityClass, id));
    }

    @Override
    public void update(E entity) {
        session.merge(entity);
    }

    @Override
    public void delete(E entity) {
        session.remove(entity);
    }

    @Override
    public List<E> findAll() {
        CriteriaQuery<E> query = session.getCriteriaBuilder().createQuery(entityClass);
        query.select(query.from(entityClass));
        return session.createQuery(query).getResultList();
    }
}
