<%--
  Created by IntelliJ IDEA.
  User: Viktor
  Date: 25.09.2024
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<style>
    html, body {
        margin: 0;
        padding: 0;
        height: 100%;
        width: 100%;
    }
    .container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
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
<body>
<!-- Include header.jsp -->
<c:set var="page" value="newMatch"/>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="scoreboard">
        <div class="caption">Tennis Scoreboard</div>
        <div class="player">Player ${requestScope.firstPlayerName}</div>
        <div class="score" id="score1">${requestScope.playersScore.firstPlayerScore.code}</div>
        <c:forEach var="game" items="${requestScope.sets}" varStatus="loop">
            <div class="game">Games in set ${loop.count}: ${game.firstPlayerScore}</div>
        </c:forEach>
        <br/>
        <div class="player">Player ${requestScope.secondPlayerName}</div>
        <div class="score" id="score2">${requestScope.playersScore.secondPlayerScore.code}</div>
        <c:forEach var="game" items="${requestScope.sets}" varStatus="loop">
            <div class="game">Games in set ${loop.count}: ${game.secondPlayerScore}</div>
        </c:forEach>
        <br/>
        <div class="buttons">
            <form method="post" action="ongoing-match?uuid=${param['uuid']}">
                <input type="hidden" name="scorer" value="1">
                <button type="submit">Point Player ${requestScope.firstPlayerName}</button>
            </form>
            <form method="post" action="ongoing-match?uuid=${param['uuid']}">
                <input type="hidden" name="scorer" value="2">
                <button type="submit">Point Player ${requestScope.secondPlayerName}</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>