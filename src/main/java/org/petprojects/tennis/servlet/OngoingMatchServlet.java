package org.petprojects.tennis.servlet;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.dto.Scorer;
import org.petprojects.tennis.service.FinishedMatchesPersistenceService;
import org.petprojects.tennis.service.MatchScoreCalculationService;
import org.petprojects.tennis.service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/ongoing-match")
@Slf4j
public class OngoingMatchServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        OngoingMatchDto match = ongoingMatchesService.getMatch(uuid);
        if (match == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        req.setAttribute("firstPlayerName", match.getFirstPlayer().getName());
        req.setAttribute("secondPlayerName", match.getSecondPlayer().getName());
        req.setAttribute("playersScore", match.getGameScore());
        req.setAttribute("sets", match.getSetsScore());
        req.getRequestDispatcher("/WEB-INF/ongoing-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UUID uuid = UUID.fromString(req.getParameter("uuid"));
            OngoingMatchDto match = ongoingMatchesService.getMatch(uuid);
            if (match == null) {
                req.getRequestDispatcher("/WEB-INF/match-is-over.jsp").forward(req, resp);
            }
            //match.setWinner(match.getFirstPlayer());
            matchScoreCalculationService.updateScore(match, req.getParameter("scorer").equals("1") ? Scorer.FIRST_PLAYER : Scorer.SECOND_PLAYER);
            if (match.getWinner() != null) {
                //Saving of th match
                finishedMatchesPersistenceService.save(match);
                ongoingMatchesService.removeMatch(uuid);
                resp.sendRedirect("/finished-matches");
            } else {
                resp.sendRedirect("/ongoing-match?uuid=" + uuid);
            }
        } catch (Exception e) {
            //Forward to error page
            req.getRequestDispatcher("/WEB-INF/error-page.jsp").forward(req, resp);
            log.error(e.getMessage(), e);
        }
    }
}
