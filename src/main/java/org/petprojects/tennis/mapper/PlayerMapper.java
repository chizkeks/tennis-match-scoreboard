package org.petprojects.tennis.mapper;

import org.petprojects.tennis.dto.PlayerDto;
import org.petprojects.tennis.entity.Player;

public class PlayerMapper {
    public static PlayerDto playerToDto(Player player) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        return playerDto;
    }
    public static Player dtoToPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setName(playerDto.getName());
        return player;
    }

}
