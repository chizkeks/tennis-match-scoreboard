package org.petprojects.tennis.dto;

import lombok.Data;

import java.util.List;

@Data
public class OngoingMatchDto {
    private Score<GamePoints> gameScore;
    private List<Score<Integer>> setsScore;
    private PlayerDto firstPlayer;
    private PlayerDto secondPlayer;

}
