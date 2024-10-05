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
  .winner {
    font-weight: bold;
    color: #4CAF50; /* Green color for winner */
  }
  .winner-icon {
    color: gold;
    font-size: 18px;
    margin-left: 5px;
  }
  /* Form Styling */
  .search-form {
    display: flex;
    justify-content: center; /* Center the form horizontally */
    align-items: center; /* Align items vertically */
    gap: 10px; /* Space between elements */
    margin: 20px 0; /* Add margin around the form */
  }

  .search-form label {
    font-size: 16px;
    font-weight: bold;
    margin-right: 10px; /* Space between label and input */
  }

  .search-form input[type="text"] {
    padding: 10px;
    font-size: 16px;
    border-radius: 5px;
    border: 1px solid #ccc;
    width: 200px;
  }

  .search-form .btn {
    background-color: #4CAF50; /* Green background */
    border: none; /* Remove border */
    color: white; /* White text */
    padding: 10px 15px; /* Adjust padding */
    font-size: 16px; /* Adjust font size */
    cursor: pointer; /* Pointer on hover */
    border-radius: 5px; /* Rounded corners */
    transition: background-color 0.3s ease; /* Smooth transition */
  }

  .search-form .btn:hover {
    background-color: #45a049; /* Darker green on hover */
  }

  /* Additional styling for mobile responsiveness */
  @media (max-width: 600px) {
    .search-form {
      flex-direction: column; /* Stack the form elements vertically on smaller screens */
      align-items: flex-start; /* Align items to the left */
    }

    .search-form input[type="text"] {
      width: 100%; /* Make the input field full-width on small screens */
    }
  }
</style>

<html>
<body>
<!-- Include header.jsp -->
<c:set var="page" value="newMatch"/>
<jsp:include page="header.jsp" />
<form action="finished-matches" method="get" class="search-form">
  <label for="player_name">Player Name:</label>
  <input type="text" id="player_name" name="player_name" value="${param['player_name']}" placeholder="Enter player name">
  <button type="submit" class="btn">Search</button>
</form>
<table>
  <thead>
    <tr>
      <th>Match ID</th>
      <th>First player name</th>
      <th>Second player name</th>
    </tr>
  </thead>

  <tbody>
  <c:if test="${requestScope.finishedMatches.size() == 0}">
    <tr>
      <td>no records</td>
    </tr>
  </c:if>
    <c:forEach var="match" items="${requestScope.finishedMatches}" varStatus="loop">
      <tr>
        <td>${match.id}</td>
        <!-- Highlight first player if they are the winner -->
        <td class="${match.winner.id == match.firstPlayer.id ? 'winner' : ''}">
            ${match.firstPlayer.name}
          <c:if test="${match.winner.id == match.firstPlayer.id}">
            <span class="winner-icon">🏆</span>
          </c:if>
        </td>
        <!-- Highlight second player if they are the winner -->
        <td class="${match.winner.id == match.secondPlayer.id ? 'winner' : ''}">
            ${match.secondPlayer.name}
          <c:if test="${match.winner.id == match.secondPlayer.id}">
            <span class="winner-icon">🏆</span>
          </c:if>
        </td>
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
