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
    private static final FinishedMatchesPersistenceService finishedMatchesService = new FinishedMatchesPersistenceService();
    private static final long serialVersionUID = 1L;
    private static final int MATCH_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setMatches(req, resp);
    }

    private void setMatches(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FinishedMatchDto> finishedMatches;
        int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
        req.setAttribute("currentPage", page);
        String playerName = req.getParameter("player_name");
        try {
            int size;
            if(playerName != null && !playerName.isEmpty()) {
                size = finishedMatchesService.getFinishedMatchesByPlayerName(playerName).size();
                finishedMatches = finishedMatchesService.getFinishedMatchesByPlayerNameWithPagination(playerName, page - 1, MATCH_PER_PAGE);
            } else {
                size = finishedMatchesService.getFinishedMatchesList().size();
                finishedMatches = finishedMatchesService.getFinishedMatchesWithPagination(page - 1, MATCH_PER_PAGE);
            }
           req.setAttribute("finishedMatches", finishedMatches);

            if(size > 0) {
                req.setAttribute("totalPages", size/MATCH_PER_PAGE + (size % MATCH_PER_PAGE == 0 ? 0 : 1));
            } else
                req.setAttribute("totalPages", 0);
            req.getRequestDispatcher("/WEB-INF/finished-matches.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("errorMessage", e.getStackTrace());
            //Forward to error page
            req.getRequestDispatcher("/WEB-INF/error-page.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setMatches(req, resp);
    }
}
