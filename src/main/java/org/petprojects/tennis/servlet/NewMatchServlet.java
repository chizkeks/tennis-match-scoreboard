package org.petprojects.tennis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.petprojects.tennis.dto.PlayerDto;
import org.petprojects.tennis.exception.PlayerNotFoundException;
import org.petprojects.tennis.service.PlayerService;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private static final PlayerService playerService = PlayerService.getInstance();

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

        try {
            firstPlayer = playerService.getPlayerByName(firstPlayerName);
        } catch (PlayerNotFoundException e) {
            firstPlayer = new PlayerDto();
            firstPlayer.setName(firstPlayerName);
            firstPlayer = playerService.createPlayer(firstPlayer);
        }
        try {
            secondPlayer = playerService.getPlayerByName(secondPlayerName);
        } catch (PlayerNotFoundException e) {
            secondPlayer = new PlayerDto();
            secondPlayer.setName(secondPlayerName);
            secondPlayer = playerService.createPlayer(secondPlayer);
        }
    }
}
