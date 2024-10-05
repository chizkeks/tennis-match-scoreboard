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
<style>
    /* Form Styling */
    .match-form {
        display: flex;
        flex-direction: column; /* Stack the form elements vertically */
        justify-content: center; /* Center the form content */
        align-items: center; /* Align the form elements horizontally */
        gap: 10px; /* Space between elements */
        margin: 20px 0; /* Add margin above and below */
    }

    .match-form label {
        font-size: 16px;
        font-weight: bold;
        margin-bottom: 5px; /* Add space between label and input */
    }

    .match-form input[type="text"] {
        padding: 10px;
        font-size: 16px;
        border-radius: 5px;
        border: 1px solid #ccc;
        width: 250px; /* Set a fixed width for inputs */
        margin-bottom: 10px; /* Space between inputs */
    }

    .match-form .btn {
        background-color: #4CAF50; /* Green background */
        border: none; /* Remove border */
        color: white; /* White text */
        padding: 10px 15px; /* Adjust padding */
        font-size: 16px; /* Adjust font size */
        cursor: pointer; /* Pointer on hover */
        border-radius: 5px; /* Rounded corners */
        transition: background-color 0.3s ease; /* Smooth transition */
    }

    .match-form .btn:hover {
        background-color: #45a049; /* Darker green on hover */
    }

    /* Mobile responsiveness */
    @media (max-width: 600px) {
        .match-form input[type="text"] {
            width: 100%; /* Make input fields full width on smaller screens */
        }
    }
</style>
<html>
    <body>
    <!-- Include header.jsp -->
    <c:set var="page" value="newMatch"/>
    <jsp:include page="header.jsp" />
    <form action="new-match" method="post" class="match-form">
        <label for="first_player">First Player Name:</label>
        <input type="text" id="first_player" name="first_player" required placeholder="Enter first player name">

        <label for="second_player">Second Player Name:</label>
        <input type="text" id="second_player" name="second_player" required placeholder="Enter second player name">

        <button type="submit" class="btn">Start</button>
    </form>
</html>

