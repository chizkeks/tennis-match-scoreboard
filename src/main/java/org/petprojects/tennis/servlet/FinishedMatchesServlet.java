package org.petprojects.tennis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.petprojects.tennis.dto.FinishedMatchDto;
import org.petprojects.tennis.service.FinishedMatchesPersistenceService;

import java.io.IOException;
import java.util.List;

@WebServlet("/finished-matches")
public class FinishedMatchesServlet extends HttpServlet {
    private static final FinishedMatchesPersistenceService finishedMatchesService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setMatches(req, resp);
    }

    private void setMatches(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FinishedMatchDto> finishedMatches;
        int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        req.setAttribute("currentPage", page);
        String playerName = req.getParameter("playerName");

        finishedMatches = finishedMatchesService.getFinishedMatchesByPlayerNameWithPagination(playerName, page - 1);
        req.setAttribute("finishedMatches", finishedMatches);
        req.setAttribute("playerName", playerName);
        int size = 0;
        if(playerName == null || playerName.isEmpty()) {
            size = finishedMatchesService.getFinishedMatchesList().size();
        } else {
            size = finishedMatches.size();
        }

        if(size > 0) {
            req.setAttribute("totalPages", size/5 + 1);
        } else
            req.setAttribute("totalPages", 0);
        req.getRequestDispatcher("/WEB-INF/finished-matches.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setMatches(req, resp);
    }
}
