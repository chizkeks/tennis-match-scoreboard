<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 15.09.2024
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <h1> Creation of a new match</h1>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/new-match" method="post">
            <label for="first_player">First player name:</label><br>
            <input type="text" id="first_player" name="first_player" required><br>
            <label for="second_player">Second player name:</label><br>
            <input type="text" id="second_player" name="second_player" required>
            <button type="button">Начать</button>
        </form>
    </body>
</html>

