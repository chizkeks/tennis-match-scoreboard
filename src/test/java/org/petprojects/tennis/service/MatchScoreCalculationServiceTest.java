package org.petprojects.tennis.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.petprojects.tennis.dto.*;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {

    private MatchScoreCalculationService matchScoreCalculationService;
    private OngoingMatchDto ongoingMatch;
    private PlayerDto firstPlayer;
    private PlayerDto secondPlayer;

    @BeforeEach
    void setUp() {
        matchScoreCalculationService = MatchScoreCalculationService.getInstance();

        firstPlayer = new PlayerDto();
        firstPlayer.setName("Player 1");
        secondPlayer = new PlayerDto();
        secondPlayer.setName("Player 2");

        ongoingMatch = new OngoingMatchDto();
        ongoingMatch.setFirstPlayer(firstPlayer);
        ongoingMatch.setSecondPlayer(secondPlayer);
        ongoingMatch.setGameScore(new Score<>(GamePoints.ZERO, GamePoints.ZERO));
    }

    @Test
    void testRegularGameScoring_FirstPlayerWins() {
        // Simulate scoring for the first player
        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(GamePoints.FIFTEEN, ongoingMatch.getGameScore().getFirstPlayerScore());

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(GamePoints.THIRTY, ongoingMatch.getGameScore().getFirstPlayerScore());

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(GamePoints.FORTY, ongoingMatch.getGameScore().getFirstPlayerScore());

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(GamePoints.ZERO, ongoingMatch.getGameScore().getFirstPlayerScore()); // Game won, reset
        assertEquals(1, ongoingMatch.getOngoingSetScore().getFirstPlayerScore()); // 1 game won
    }

    @Test
    void testAdvantageGameScoring() {
        // First player to 40
        ongoingMatch.getGameScore().setFirstPlayerScore(GamePoints.FORTY);
        ongoingMatch.getGameScore().setSecondPlayerScore(GamePoints.FORTY);

        // First player gains advantage
        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(GamePoints.ADVANTAGE, ongoingMatch.getGameScore().getFirstPlayerScore());

        // Second player brings it back to deuce
        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.SECOND_PLAYER);
        assertEquals(GamePoints.FORTY, ongoingMatch.getGameScore().getFirstPlayerScore());
        assertEquals(GamePoints.FORTY, ongoingMatch.getGameScore().getSecondPlayerScore());
    }

    @Test
    void testTieBreakScoring_FirstPlayerWins() {
        // Simulate a 6-6 set, entering tie-break
        ongoingMatch.getOngoingSetScore().setFirstPlayerScore(6);
        ongoingMatch.getOngoingSetScore().setSecondPlayerScore(6);

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(1, ongoingMatch.getTieBreakScore().getFirstPlayerScore());

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        assertEquals(2, ongoingMatch.getTieBreakScore().getFirstPlayerScore());

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.SECOND_PLAYER);
        assertEquals(1, ongoingMatch.getTieBreakScore().getSecondPlayerScore());

        // First player wins tie-break 7-1
        for (int i = 0; i < 5; i++) {
            matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);
        }

        assertEquals(7, ongoingMatch.getTieBreakScore().getFirstPlayerScore());
        assertEquals(1, ongoingMatch.getTieBreakScore().getSecondPlayerScore());
        assertEquals(7, ongoingMatch.getOngoingSetScore().getFirstPlayerScore()); // Set won by first player
    }

    @Test
    void testMatchWinning_FirstPlayer() {
        // First player has won 1 set already
        ongoingMatch.addSet(new Score<>(6, 3));
        // Create another set
        ongoingMatch.addSet();
        // First player wins second set
        ongoingMatch.getOngoingSetScore().setFirstPlayerScore(6);
        ongoingMatch.getOngoingSetScore().setSecondPlayerScore(4);

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);

        assertNotNull(ongoingMatch.getWinner());
        assertEquals(firstPlayer, ongoingMatch.getWinner()); // First player is the winner
    }

    @Test
    void testMatchWinning_SecondPlayer() {
        // Second player has won 1 set already
        ongoingMatch.addSet(new Score<>(3, 6));
        // Create another set
        ongoingMatch.addSet();
        // Second player wins second set
        ongoingMatch.getOngoingSetScore().setFirstPlayerScore(4);
        ongoingMatch.getOngoingSetScore().setSecondPlayerScore(6);

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.SECOND_PLAYER);

        assertNotNull(ongoingMatch.getWinner());
        assertEquals(secondPlayer, ongoingMatch.getWinner()); // Second player is the winner
    }

    @Test
    void testMatchNoWinnerYet() {
        // No sets won yet, no winner should be declared
        ongoingMatch.getOngoingSetScore().setFirstPlayerScore(5);
        ongoingMatch.getOngoingSetScore().setSecondPlayerScore(4);

        matchScoreCalculationService.updateScore(ongoingMatch, Scorer.FIRST_PLAYER);

        assertNull(ongoingMatch.getWinner()); // No winner yet
    }
}
