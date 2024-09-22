package org.petprojects.tennis.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.petprojects.tennis.dto.GamePoints;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.dto.Score;
import org.petprojects.tennis.dto.Scorer;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {
    public static MatchScoreCalculationService getInstance() {return MatchScoreCalculationServiceHelper.singletonObject;}
    private static class MatchScoreCalculationServiceHelper {
        public static MatchScoreCalculationService singletonObject = new MatchScoreCalculationService();
    }

    public void updateScore(OngoingMatchDto ongoingMatch, Scorer scorer) {
        //Check game score
        if(scorer.equals(Scorer.FIRST_PLAYER)) {
            if(ongoingMatch.getGameScore().getFirstPlayerScore().equals(GamePoints.FORTY)
                    && ongoingMatch.getGameScore().getSecondPlayerScore().equals(GamePoints.ADVANTAGE)) {
                ongoingMatch.getGameScore().setSecondPlayerScore(GamePoints.FORTY);
            }
            try {
                ongoingMatch.getGameScore().setFirstPlayerScore(ongoingMatch.getGameScore().getFirstPlayerScore().next());
            } catch (IllegalStateException e) {
                ongoingMatch.resetGameScore();
            }
        } else {
            if(ongoingMatch.getGameScore().getSecondPlayerScore().equals(GamePoints.FORTY)
                    && ongoingMatch.getGameScore().getFirstPlayerScore().equals(GamePoints.ADVANTAGE)) {
                ongoingMatch.getGameScore().setFirstPlayerScore(GamePoints.FORTY);
            }
            try {
                ongoingMatch.getGameScore().setSecondPlayerScore(ongoingMatch.getGameScore().getSecondPlayerScore().next());
            }catch (IllegalStateException e) {
                ongoingMatch.resetGameScore();
            }
        }
        //Check set score

        if(ongoingMatch.getGameScore().getFirstPlayerScore().equals(GamePoints.ZERO)
        && ongoingMatch.getGameScore().getSecondPlayerScore().equals(GamePoints.ZERO)) {
            if(scorer.equals(Scorer.FIRST_PLAYER)) {
                ongoingMatch.getOngoingSetScore().setFirstPlayerScore(ongoingMatch.getOngoingSetScore().getFirstPlayerScore() + 1);
            } else {
                ongoingMatch.getOngoingSetScore().setSecondPlayerScore(ongoingMatch.getOngoingSetScore().getSecondPlayerScore() + 1);
            }
        }
        //Check match score

        if(ongoingMatch.getOngoingSetScore().getFirstPlayerScore().equals(6)
                && ongoingMatch.getOngoingSetScore().getSecondPlayerScore() < 5
                || ongoingMatch.getOngoingSetScore().getFirstPlayerScore().equals(7)) {
            ongoingMatch.setWinner(ongoingMatch.getFirstPlayer());
        }
        else if(ongoingMatch.getOngoingSetScore().getSecondPlayerScore().equals(6)
                && ongoingMatch.getOngoingSetScore().getFirstPlayerScore() < 5
                || ongoingMatch.getOngoingSetScore().getSecondPlayerScore().equals(7)) {
            ongoingMatch.setWinner(ongoingMatch.getSecondPlayer());
        } else {
            ongoingMatch.addSet();
        }

        // Do we have a winner?
        if(ongoingMatch.getFirstPlayerWonSets() == 2) {
            ongoingMatch.setWinner(ongoingMatch.getFirstPlayer());
        } else if(ongoingMatch.getSecondPlayerWonSets() == 2) {
            ongoingMatch.setWinner(ongoingMatch.getSecondPlayer());
        }
    }
}
