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
<style>
  /*body {*/
  /*  font-family: Arial, sans-serif;*/
  /*  margin: 20px;*/
  /*}*/
  table {
    width: 100%;
    border-collapse: collapse;
    margin: 0;
    border: 2px solid #000;
    border-radius: 10px;
  }
  th, td {
    padding: 12px;
    text-align: left;
    border: 1px solid #dddddd;
  }
  th {
    background-color: #4CAF50; /* Green background */
    color: white; /* White text */
  }
  tr:nth-child(even) {
    background-color: #f2f2f2; /* Light grey background for even rows */
  }
  tr:hover {
    background-color: #ddd; /* Darker grey on hover */
  }
</style>

<html>
<body>
<!-- Include header.jsp -->
<c:set var="page" value="newMatch"/>
<jsp:include page="header.jsp" />
<form action="finished-matches" method="get">
  <label for="player_name">Player name:</label><br>
  <input type="text" id="player_name" name="player_name" value="${param['player_name']}"><br>
  <button type="submit">Search</button>
</form>
<table>
  <thead>
    <tr>
      <th>Match ID</th>
      <th>First player name</th>
      <th>Second player name</th>
      <th>Winner of the match</th>
    </tr>
  </thead>

  <tbody>
    <c:forEach var="match" items="${requestScope.finishedMatches}" varStatus="loop">
        <tr>
          <td>${match.id}</td>
          <td>${match.firstPlayer.name}</td>
          <td>${match.secondPlayer.name}</td>
          <td>${match.winner.name}</td>
        </tr>

    </c:forEach>
  </tbody>
  </table>
<c:set var="pageInt" value="${requestScope.currentPage}"/>
<div class="buttons-container">
  <form action="finished-matches?player_name=${param['player_name']}&page=${pageInt - 1}" method="post">
    <button type="submit" class="btn" <c:if test="${pageInt <= 1}">disabled</c:if>>Previous</button>
  </form>
  <!-- Page numbers -->
  <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
    <form action="finished-matches?player_name=${param['player_name']}&page=${i}" method="post" style="display:inline;">
      <button type="submit" class="btn" <c:if test="${pageInt == i}">disabled</c:if>>${i}</button>
    </form>
  </c:forEach>
  <form action="finished-matches?player_name=${param['player_name']}&page=${pageInt + 1}" method="post">
    <button type="submit" class="btn" <c:if test="${requestScope.totalPages == pageInt or requestScope.totalPages == 0}">disabled</c:if>>Next</button>
  </form>
</div>
<style>
  /* Button Styling */
  .btn {
    background-color: #4CAF50; /* Green background */
    border: none; /* Remove border */
    color: white; /* White text */
    padding: 12px 20px; /* Some padding */
    text-align: center; /* Center the text */
    text-decoration: none; /* Remove underline */
    display: inline-block; /* Keep it inline */
    font-size: 16px; /* Increase font size */
    margin: 10px 5px; /* Add some space between buttons */
    cursor: pointer; /* Pointer/hand icon on hover */
    border-radius: 10px; /* Rounded corners */
    transition: background-color 0.3s ease; /* Animation on hover */
  }

  /* Hover effect for the buttons */
  .btn:hover {
    background-color: #45a049; /* Darker green */
  }

  /* Disabled button styling */
  .btn:disabled {
    background-color: #ddd; /* Gray background */
    color: #aaa; /* Gray text */
    cursor: not-allowed; /* Disable hover effect */
  }

  /* Adjust button spacing and alignment */
  .buttons-container {
    display: flex;
    justify-content: center; /* Center the buttons */
    align-items: center;
  }

  /* Add additional styling for mobile */
  @media (max-width: 600px) {
    .btn {
      width: 100%; /* Make buttons full-width on small screens */
    }
  }
</style>
</body>
</html>
