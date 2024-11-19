<%--
  Created by IntelliJ IDEA.
  User: viktor_k
  Date: 25.09.2024
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <script src="../js/app.js"></script>
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="../img/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="main-menu">Home</a>
                <a class="nav-link" href="finished-matches">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Current match</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <td class="table-text">${requestScope.firstPlayerName}</td>
                    <td class="table-text">${requestScope.playersSetsScore[0]}</td>
                    <td class="table-text">${requestScope.playersGamesScore.firstPlayerScore}</td>
                    <td class="table-text">${requestScope.playersScore.firstPlayerScore.code}</td>
                    <td class="table-text">
                        <form method="post" class="score-btn-form" action="ongoing-match?uuid=${param['uuid']}">
                            <input type="hidden" name="scorer" value = "1">
                            <button class="score-btn" type="submit">Score</button>
                        </form>
                    </td>
                </tr>
                <tr class="player2">
                    <td class="table-text">${requestScope.secondPlayerName}</td>
                    <td class="table-text">${requestScope.playersSetsScore[1]}</td>
                    <td class="table-text">${requestScope.playersGamesScore.secondPlayerScore}</td>
                    <td class="table-text">${requestScope.playersScore.secondPlayerScore.code}</td>
                    <td class="table-text">
                        <form method="post" class="score-btn-form" action="ongoing-match?uuid=${param['uuid']}">
                            <input type="hidden" name="scorer" value = "2">
                            <button class="score-btn" type="submit">Score</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a> roadmap.</p>
    </div>
</footer>
</body>
</html>
