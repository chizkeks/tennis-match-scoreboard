package org.petprojects.tennis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.dto.Scorer;
import org.petprojects.tennis.service.FinishedMatchesPersistenceService;
import org.petprojects.tennis.service.MatchScoreCalculationService;
import org.petprojects.tennis.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/ongoing-match")
public class OngoingMatchServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatchDto match = ongoingMatchesService.getMatch(uuid);
        req.setAttribute("firstPlayerName", match.getFirstPlayer().getName());
        req.setAttribute("secondPlayerName", match.getSecondPlayer().getName());
        req.setAttribute("playersScore", match.getGameScore());
        req.setAttribute("sets", match.getSetsScore());
        req.getRequestDispatcher("/WEB-INF/ongoing-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatchDto match = ongoingMatchesService.getMatch(uuid);
        match.setWinner(match.getFirstPlayer());
        matchScoreCalculationService.updateScore(match, req.getParameter("scorer").equals("1") ? Scorer.FIRST_PLAYER : Scorer.SECOND_PLAYER);
        req.setAttribute("firstPlayerName", match.getFirstPlayer().getName());
        req.setAttribute("secondPlayerName", match.getSecondPlayer().getName());
        req.setAttribute("playersScore", match.getGameScore());
        req.setAttribute("sets", match.getSetsScore());
        if(match.getWinner() != null) {
            //Saving of th match
            finishedMatchesPersistenceService.save(match);
            resp.sendRedirect("/finished-matches");
        } else {
            req.getRequestDispatcher("/WEB-INF/ongoing-match.jsp").forward(req, resp);
        }

    }
}
