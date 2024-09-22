package org.petprojects.tennis.service;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.petprojects.tennis.dto.GamePoints;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.dto.Score;
import org.petprojects.tennis.dto.Scorer;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {

    private static final int GAMES_TO_WIN_SET = 6;
    private static final int TIE_BREAK_POINTS_TO_WIN = 7;
    private static final int SETS_TO_WIN_MATCH = 2;

    public static MatchScoreCalculationService getInstance() {return MatchScoreCalculationServiceHelper.singletonObject;}
    private static class MatchScoreCalculationServiceHelper {
        public static MatchScoreCalculationService singletonObject = new MatchScoreCalculationService();
    }

    public void updateScore(OngoingMatchDto ongoingMatch, Scorer scorer) {
        if (isTieBreak(ongoingMatch)) {
            updateTieBreakScore(ongoingMatch, scorer);
        } else {
            updateRegularGameScore(ongoingMatch, scorer);
        }
        // Check if a game is won, and update set score
        if (isGameWon(ongoingMatch)) {
            updateSetScore(ongoingMatch, scorer);
        }

        // Check if a set is won, and update match score
        if (isSetWon(ongoingMatch)) {
            ongoingMatch.addSet();
        }

        // Check if the match has a winner
        checkForMatchWinner(ongoingMatch);
    }

    // Update the tie-break score
    private void updateTieBreakScore(OngoingMatchDto match, Scorer scorer) {
        Score<Integer> tieBreakScore = match.getTieBreakScore();
        if (scorer == Scorer.FIRST_PLAYER) {
            tieBreakScore.setFirstPlayerScore(tieBreakScore.getFirstPlayerScore() + 1);
        } else {
            tieBreakScore.setSecondPlayerScore(tieBreakScore.getSecondPlayerScore() + 1);
        }
    }

    //Update the game score
    private void updateRegularGameScore(OngoingMatchDto match, Scorer scorer) {
        Score<GamePoints> gameScore = match.getGameScore();
        if (scorer == Scorer.FIRST_PLAYER) {
            if(gameScore.getFirstPlayerScore().equals(GamePoints.FORTY) && gameScore.getSecondPlayerScore().equals(GamePoints.ADVANTAGE)) {
                gameScore.setFirstPlayerScore(GamePoints.FORTY);
            } else if(gameScore.getFirstPlayerScore().equals(GamePoints.FORTY)
                    && gameScore.getFirstPlayerScore().ordinal() - gameScore.getSecondPlayerScore().ordinal() > 0) {
                match.resetGameScore();
            } else {
                advanceScore(gameScore.getFirstPlayerScore(), gameScore::setFirstPlayerScore, match);
            }

        } else {
            if(gameScore.getSecondPlayerScore().equals(GamePoints.FORTY) && gameScore.getFirstPlayerScore().equals(GamePoints.ADVANTAGE)) {
                gameScore.setFirstPlayerScore(GamePoints.FORTY);
            } else if(gameScore.getSecondPlayerScore().equals(GamePoints.FORTY)
                    && gameScore.getSecondPlayerScore().ordinal() - gameScore.getFirstPlayerScore().ordinal() > 0) {
                match.resetGameScore();
            } else {
                advanceScore(gameScore.getSecondPlayerScore(), gameScore::setSecondPlayerScore, match);
            }

        }
    }

    private void advanceScore(GamePoints currentScore,
                              java.util.function.Consumer<GamePoints> scoreUpdater,
                              OngoingMatchDto match) {
        try {
            scoreUpdater.accept(currentScore.next());
        } catch (IllegalStateException e) {
            match.resetGameScore();
        }
    }

    //Check if a game is ended
    private boolean isGameWon(OngoingMatchDto match) {
        if(isTieBreak(match) && !isTieBreakWinner(match)) {return false;}
        else if(isTieBreakWinner(match)) {return true;}
        return match.getGameScore().getFirstPlayerScore() == GamePoints.ZERO &&
                match.getGameScore().getSecondPlayerScore() == GamePoints.ZERO;
    }

    private boolean isTieBreakWinner(OngoingMatchDto ongoingMatch) {
        int firstPlayerPoints = ongoingMatch.getTieBreakScore().getFirstPlayerScore();
        int secondPlayerPoints = ongoingMatch.getTieBreakScore().getSecondPlayerScore();
        return (firstPlayerPoints >= 7 || secondPlayerPoints >= 7) && Math.abs(firstPlayerPoints - secondPlayerPoints) >= 2;
    }

    //Update the ongoing set score
    private void updateSetScore(OngoingMatchDto match, Scorer scorer) {
        if (scorer == Scorer.FIRST_PLAYER) {
            match.getOngoingSetScore().setFirstPlayerScore(match.getOngoingSetScore().getFirstPlayerScore() + 1);
        } else {
            match.getOngoingSetScore().setSecondPlayerScore(match.getOngoingSetScore().getSecondPlayerScore() + 1);
        }
    }

    //Check if an ongoing set has a winner
    private boolean isSetWon(OngoingMatchDto match) {
        int firstPlayerGames = match.getOngoingSetScore().getFirstPlayerScore();
        int secondPlayerGames = match.getOngoingSetScore().getSecondPlayerScore();

        return (firstPlayerGames == GAMES_TO_WIN_SET && secondPlayerGames < GAMES_TO_WIN_SET - 1)
                || (secondPlayerGames == GAMES_TO_WIN_SET && firstPlayerGames < GAMES_TO_WIN_SET - 1)
                || (firstPlayerGames == TIE_BREAK_POINTS_TO_WIN && secondPlayerGames < TIE_BREAK_POINTS_TO_WIN - 1)
                || (secondPlayerGames == TIE_BREAK_POINTS_TO_WIN && firstPlayerGames < TIE_BREAK_POINTS_TO_WIN - 1);
    }

    //Check if there is a tie-break in a match
    private boolean isTieBreak(OngoingMatchDto match) {
        return match.getOngoingSetScore().getFirstPlayerScore() == GAMES_TO_WIN_SET &&
                match.getOngoingSetScore().getSecondPlayerScore() == GAMES_TO_WIN_SET;
    }

    //Set a winner of a match if there is one
    private void checkForMatchWinner(OngoingMatchDto match) {
        if (match.getFirstPlayerWonSets() == SETS_TO_WIN_MATCH) {
            match.setWinner(match.getFirstPlayer());
        } else if (match.getSecondPlayerWonSets() == SETS_TO_WIN_MATCH) {
            match.setWinner(match.getSecondPlayer());
        }
    }
}
