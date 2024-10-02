package org.petprojects.tennis.dao;

import org.petprojects.tennis.entity.Player;


public class PlayerRepository extends BaseRepository<Integer, Player> {
    public PlayerRepository() {
        super(Player.class);
    }
    public Player findByName(String name) {
        return executeTransaction(entityManager ->
                entityManager
                .createQuery("from Player where name = :name", Player.class)
                .setParameter("name", name)
                .getSingleResult());
    }
}
