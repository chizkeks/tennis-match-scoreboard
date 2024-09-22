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
        if(setsScore == null || setsScore.isEmpty()) {
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
