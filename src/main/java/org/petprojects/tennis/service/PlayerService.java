package org.petprojects.tennis.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.petprojects.tennis.dao.PlayerRepository;
import org.petprojects.tennis.dto.PlayerDto;
import org.petprojects.tennis.entity.Player;
import org.petprojects.tennis.mapper.PlayerMapper;
import org.petprojects.tennis.util.HibernateUtil;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {
    final Session session = HibernateUtil.getSessionFactory().openSession();
    final PlayerRepository repository = new PlayerRepository(session);
    final static PlayerService INSTANCE = new PlayerService();

    public static PlayerService getInstance() {
        return INSTANCE;
    }
    public PlayerDto getPlayerById(Integer id) {
        Player player = repository.findById(id);
        return PlayerMapper.playerToDto(player);
    }
    public PlayerDto getPlayerByName(String name) {
        Player player = repository.findByName(name);
        return PlayerMapper.playerToDto(player);
    }
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Transaction transaction = session.beginTransaction();
        Player player = repository.create(PlayerMapper.dtoToPlayer(playerDto));
        transaction.commit();
        return PlayerMapper.playerToDto(player);
    }

}
