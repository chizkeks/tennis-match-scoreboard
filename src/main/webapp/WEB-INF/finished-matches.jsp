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
  body {
    font-family: Arial, sans-serif;
    margin: 20px;
  }
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
<head>
    <title>List of finished matches</title>
</head>
<body>
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
      <div class="match">
        <tr>
          <td>${match.id}</td>
          <td>${match.firstPlayer.name}</td>
          <td>${match.secondPlayer.name}</td>
          <td>${match.winner.name}</td>
        </tr>
      </div>
    </c:forEach>
  </tbody>
  </table>
</body>

</html>
