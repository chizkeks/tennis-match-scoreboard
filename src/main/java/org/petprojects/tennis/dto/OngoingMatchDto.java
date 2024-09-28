package org.petprojects.tennis.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class OngoingMatchDto {
    private Score<GamePoints> gameScore;
    @Setter(AccessLevel.NONE)
    private List<Score<Integer>> setsScore = new ArrayList<>();
    private PlayerDto firstPlayer;
    private PlayerDto secondPlayer;
    private PlayerDto winner;
    private Score<Integer> tieBreakScore = new Score<>(0, 0);

    public void resetGameScore() {
        gameScore.setFirstPlayerScore(GamePoints.ZERO);
        gameScore.setSecondPlayerScore(GamePoints.ZERO);
    }

    public Score<Integer> getOngoingSetScore() {
        if(setsScore.isEmpty()) {
            Score<Integer> curScore = new Score<>(0, 0);
            setsScore.add(curScore);
        }
        return setsScore.get(setsScore.size() - 1);
    }
    public void addSet() {
        setsScore.add(new Score<>(0, 0));
    }
    public void addSet(Score<Integer> set) {
        setsScore.add(set);
    }
    public int  getFirstPlayerWonSets() {
        int counter = 0;
        for(Score<Integer> score : setsScore) {
            if(score.getFirstPlayerScore() > score.getSecondPlayerScore()
            && score.getFirstPlayerScore() >= 6)
                counter++;
        }
        return counter;
    }
    public int  getSecondPlayerWonSets() {
        int counter = 0;
        for(Score<Integer> score : setsScore) {
            if(score.getFirstPlayerScore() < score.getSecondPlayerScore()
                    && score.getSecondPlayerScore() >= 6)
                counter++;
        }
        return counter;
    }

}
