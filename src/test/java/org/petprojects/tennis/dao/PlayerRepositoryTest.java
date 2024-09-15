package org.petprojects.tennis.dao;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.petprojects.tennis.entity.Player;

public class PlayerRepositoryTest {
    @Test
    public void assertPlayerCreation() {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        PlayerRepository repository = new PlayerRepository(session);
        session.beginTransaction();

        Player player = Player.builder()
                .name("Novak Jokovic")
                .build();

        repository.create(player);
        session.getTransaction().commit();

        repository.findAll().forEach(System.out::println);
        assert repository.findAll().size() == 1;
    }
}
