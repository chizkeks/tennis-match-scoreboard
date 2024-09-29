package org.petprojects.tennis.dao;

import org.hibernate.Session;
import org.petprojects.tennis.entity.Match;

import java.util.List;

public class MatchRepository extends BaseRepository<Integer, Match> {
    public MatchRepository(Session session) {
        super(Match.class, session);
    }

    public List<Match> findByPlayerNameWithPagination(String name, int offset, int limit) {
        if(name==null || name.isEmpty()) {
            return entityManager
                    .createQuery("from Match m", Match.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        }
        return entityManager
                .createQuery("from Match m " +
                        " join m.firstPlayer fp" +
                        " join m.secondPlayer sp" +
                        " where fp.name = :name or sp.name = :name", Match.class)
                .setParameter("name", name)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
