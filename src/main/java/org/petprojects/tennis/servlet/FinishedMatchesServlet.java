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
        List<FinishedMatchDto> finishedMatches =  finishedMatchesService.getFinishedMatchesList();
        req.setAttribute("finishedMatches", finishedMatches);

        req.getRequestDispatcher("/WEB-INF/finished-matches.jsp").forward(req, resp);
    }
}
