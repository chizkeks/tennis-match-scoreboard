package org.petprojects.tennis.dao;

import org.hibernate.Session;
import org.petprojects.tennis.entity.Match;

public class MatchRepository extends BaseRepository<Integer, Match> {
    public MatchRepository(Session session) {
        super(Match.class, session);
    }
}
