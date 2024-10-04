package org.petprojects.tennis.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.petprojects.tennis.entity.BaseEntity;
import org.petprojects.tennis.util.HibernateUtil;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
public class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E> {
    final Class<E> entityClass;


    @Override
    public E create(E entity) throws PersistenceException {
        return executeTransaction(entityManager -> {
            entityManager.persist(entity);
            return entity;
        });
    }

    @Override
    public E findById(K id) throws PersistenceException {
        return executeTransaction(entityManager -> entityManager.find(entityClass, id));
    }

    @Override
    public void update(E entity) throws PersistenceException {
        executeTransaction(entityManager -> entityManager.merge(entity));
    }

    @Override
    public void delete(E entity) throws PersistenceException {
        executeTransaction(entityManager -> {
            entityManager.remove(entity);
            return null;
        } );
    }

    @Override
    public List<E> findAll() throws PersistenceException {
        return executeTransaction(entityManager -> {
            CriteriaQuery<E> query = entityManager.getCriteriaBuilder().createQuery(entityClass);
            query.select(query.from(entityClass));
            return entityManager.createQuery(query).getResultList();
        });
    }

    @Override
    public <R> R executeTransaction(Function<EntityManager, R> operation) throws PersistenceException {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().getCurrentSession()){
            transaction = session.beginTransaction();

            R result = operation.apply(session);
            log.error("Test tr {}", session);
            transaction.commit();

            return result;
        } catch (PersistenceException e) {
            if(transaction != null && transaction.isActive()){
                log.error("Error during transaction {}",e.getMessage());
                transaction.rollback();
            }
            throw e;
        }
    }
}
