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
    <title>Теннисное Табло Судьи</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #e0e0e0;
        }
        .scoreboard {
            width: 400px;
            padding: 20px;
            background-color: white;
            border: 2px solid #000;
            border-radius: 10px;
            text-align: center;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        }
        .player {
            font-size: 20px;
            margin: 10px 0;
        }
        .score {
            font-size: 36px;
            font-weight: bold;
        }
        .game {
            font-size: 16px;
            margin: 5px 0;
        }
        .buttons {
            margin-top: 20px;
        }
        button {
            padding: 10px 15px;
            margin: 5px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
${param.name}
<div class="scoreboard">
    <div class="caption">Теннисный матч (максимум 3 сета)</div>
    <div class="player">Игрок 1: ${requestScope.firstPlayerName}</div>
    <div class="score" id="score1">${requestScope.playersScore.firstPlayerScore.code}</div>
    <c:forEach var="game" items="${requestScope.sets}" varStatus="loop">
        <div class="game">Геймы в ${loop.count} сете: ${game.firstPlayerScore}</div>
    </c:forEach>
    <br/>
    <div class="player">Игрок 2: ${requestScope.secondPlayerName}</div>
    <div class="score" id="score2">${requestScope.playersScore.secondPlayerScore.code}</div>
    <c:forEach var="game" items="${requestScope.sets}" varStatus="loop">
        <div class="game">Геймы в ${loop.count} сете: ${game.secondPlayerScore}</div>
    </c:forEach>
    <br/>
    <div class="buttons">
        <form method="post" action="ongoing-match?player=1">
        <button type="submit">Очко Игрока 1</button>
        </form>
        <form method="post" action="ongoing-match?player=2">
        <button type="submit">Очко Игрока 2</button>
        </form>
    </div>
</div>
</body>
</html>
