package org.petprojects.tennis.dao;

import jakarta.persistence.EntityManager;
import org.petprojects.tennis.entity.Player;


public class PlayerRepository extends BaseRepository<Integer, Player> {
    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }
    public Player findByName(String name) {
        return entityManager
                .createQuery("from Player where name = :name", Player.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
