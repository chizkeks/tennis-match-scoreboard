<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 27.09.2024
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tennis Scoreboard | Finished Matches</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../css/style.css">

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
    <h1>Matches</h1>
    <div class="input-container">
      <form class = "player-filter-form" action="finished-matches" method="get">
        <input class="input-filter" placeholder="Filter by name" type="text" id="playerName" name="playerName" value="${param['playerName']}"/>
        <div>
          <button type="submit" class="btn-filter">Filter</button>
        </div>
      </form>
    </div>

    <table class="table-matches">
      <tr>
        <th>Player One</th>
        <th>Player Two</th>
        <th>Winner</th>
      </tr>
      <c:forEach var="match" items="${requestScope.finishedMatches}" varStatus="loop">
        <tr>
          <td>${match.firstPlayer.name}</td>
          <td>${match.secondPlayer.name}</td>
          <td><span class="winner-name-td">${match.winner.name}</span></td>
        </tr>
      </c:forEach>
    </table>

    <c:set var="pageInt" value="${requestScope.currentPage}"/>
    <div class="pagination">
      <a class="prev" href="finished-matches?page=${pageInt - 1}&playerName=${playerName}" <c:if test="${pageInt <= 1}">disabled</c:if>> < </a>
      <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
        <a <c:if test="${pageInt == i}">class="num-page current"</c:if>
           <c:if test="${pageInt != i}">class="num-page"</c:if>
           href="/finished-matches?page=${i}&playerName=${playerName}">${i}</a>
      </c:forEach>
      <a class="next" href="finished-matches?page=${pageInt + 1}&playerName=${playerName}" ${requestScope.totalPages == pageInt or requestScope.totalPages == 0}> > </a>
    </div>
  </div>
</main>
<footer>
  <div class="footer">
    <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
      roadmap.</p>
  </div>
</footer>
</body>
</html>