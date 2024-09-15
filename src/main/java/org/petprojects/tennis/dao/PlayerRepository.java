package org.petprojects.tennis.dao;

import org.hibernate.Session;
import org.petprojects.tennis.entity.Player;


public class PlayerRepository extends BaseRepository<Integer, Player> {

    public PlayerRepository(Session session) {
        super(Player.class, session);
    }
}
