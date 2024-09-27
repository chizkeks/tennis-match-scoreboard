package org.petprojects.tennis.servlet;

import jakarta.persistence.NoResultException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.petprojects.tennis.dto.GamePoints;
import org.petprojects.tennis.dto.OngoingMatchDto;
import org.petprojects.tennis.dto.PlayerDto;
import org.petprojects.tennis.dto.Score;
import org.petprojects.tennis.service.OngoingMatchesService;
import org.petprojects.tennis.service.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private static final PlayerService playerService = PlayerService.getInstance();
    private static final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = req.getParameter("first_player");
        String secondPlayerName = req.getParameter("second_player");

        PlayerDto firstPlayer;
        PlayerDto secondPlayer;
        OngoingMatchDto ongoingMatchDto;

        try {
            firstPlayer = playerService.getPlayerByName(firstPlayerName);
        } catch (NoResultException e) {
            firstPlayer = new PlayerDto();
            firstPlayer.setName(firstPlayerName);
        }
        try {
            secondPlayer = playerService.getPlayerByName(secondPlayerName);
        } catch (NoResultException e) {
            secondPlayer = new PlayerDto();
            secondPlayer.setName(secondPlayerName);
        }
        ongoingMatchDto = new OngoingMatchDto();
        ongoingMatchDto.setFirstPlayer(firstPlayer);
        ongoingMatchDto.setSecondPlayer(secondPlayer);
        ongoingMatchDto.setGameScore(new Score<>(GamePoints.ZERO, GamePoints.ZERO));
        UUID uuid = ongoingMatchesService.addMatch(ongoingMatchDto);
        //req.getSession().setAttribute("uuid", uuid);
        resp.sendRedirect("/ongoing-match?uuid=" + uuid.toString());
    }
}
