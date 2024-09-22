package org.petprojects.tennis.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class OngoingMatchDto {
    private Score<GamePoints> gameScore;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private List<Score<Integer>> setsScore;
    private PlayerDto firstPlayer;
    private PlayerDto secondPlayer;
    private PlayerDto winner;

    public void resetGameScore() {
        gameScore.setFirstPlayerScore(GamePoints.ZERO);
        gameScore.setSecondPlayerScore(GamePoints.ZERO);
    }

    public Score<Integer> getOngoingSetScore() {
        if(setsScore.isEmpty()) {
            Score<Integer> curScore = new Score<>(0, 0);
            setsScore.add(curScore);
            return curScore;
        }
        return setsScore.get(setsScore.size() - 1);
    }
    public void addSet() {
        setsScore.add(new Score<>(0, 0));
    }
    public int  getFirstPlayerWonSets() {
        int counter = 0;
        for(Score<Integer> score : setsScore) {
            if(score.getFirstPlayerScore() > score.getSecondPlayerScore())
                counter++;
        }
        return counter;
    }
    public int  getSecondPlayerWonSets() {
        int counter = 0;
        for(Score<Integer> score : setsScore) {
            if(score.getFirstPlayerScore() < score.getSecondPlayerScore())
                counter++;
        }
        return counter;
    }
}
