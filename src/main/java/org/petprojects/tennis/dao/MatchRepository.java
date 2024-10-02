package org.petprojects.tennis.dao;

import org.petprojects.tennis.entity.Match;

import java.util.List;

public class MatchRepository extends BaseRepository<Integer, Match> {
    public MatchRepository() {
        super(Match.class);
    }
    public List<Match> findAllByPlayerName(String name) {
        return executeTransaction(entityManager -> entityManager
                .createQuery("from Match m " +
                        " join m.firstPlayer fp" +
                        " join m.secondPlayer sp" +
                        " where fp.name = :name or sp.name = :name", Match.class)
                .setParameter("name", name)
                .getResultList());
    }

    public List<Match> findByPlayerNameWithPagination(String name, int offset, int limit) {
        return executeTransaction(entityManager ->
                entityManager
                .createQuery("from Match m " +
                        " join m.firstPlayer fp" +
                        " join m.secondPlayer sp" +
                        " where fp.name = :name or sp.name = :name", Match.class)
                .setParameter("name", name)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList());
    }
    public List<Match> findAllWithPagination(int offset, int limit) {
        return executeTransaction(entityManager ->
                entityManager
                        .createQuery("from Match m", Match.class)
                        .setFirstResult(offset)
                        .setMaxResults(limit)
                        .getResultList());
    }
}
