package org.petprojects.tennis.dao;

import jakarta.persistence.EntityManager;
import org.petprojects.tennis.entity.Player;

import java.util.Optional;


public class PlayerRepository extends BaseRepository<Integer, Player> {
    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }
    public Optional<Player> findByName(String name) {
        return Optional.ofNullable(entityManager
                .createQuery("from Player where name = :name", Player.class)
                .setParameter("name", name)
                .getSingleResult());
    }
}
