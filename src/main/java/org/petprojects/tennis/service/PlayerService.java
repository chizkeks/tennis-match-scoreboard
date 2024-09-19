package org.petprojects.tennis.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.petprojects.tennis.dao.PlayerRepository;
import org.petprojects.tennis.dto.PlayerDto;
import org.petprojects.tennis.entity.Player;
import org.petprojects.tennis.exception.PlayerNotFoundException;
import org.petprojects.tennis.mapper.PlayerMapper;
import org.petprojects.tennis.util.HibernateUtil;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {
    final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    final PlayerRepository repository = new PlayerRepository(sessionFactory.getCurrentSession());
    final static PlayerService INSTANCE = new PlayerService();

    public static PlayerService getInstance() {
        return INSTANCE;
    }
    public PlayerDto getPlayerById(Integer id) {
        Player player = repository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player with id: " + id + " not found"));
        return PlayerMapper.playerToDto(player);
    }
    public PlayerDto getPlayerByName(String name) {
        Player player = repository.findByName(name)
                .orElseThrow(() -> new PlayerNotFoundException("Player with name: " + name + " not found"));
        return PlayerMapper.playerToDto(player);
    }
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = repository.create(PlayerMapper.dtoToPlayer(playerDto));
        return PlayerMapper.playerToDto(player);
    }

}
