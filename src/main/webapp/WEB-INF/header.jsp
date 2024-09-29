<%--
  Created by IntelliJ IDEA.
  User: viktor_k
  Date: 29.09.2024
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<!-- header.jsp -->
<div class="header">
  <div class="logo">Tennis score board</div>
</div>

<div class="nav-menu">
  <a href="/new-match" class="${page == 'main' ? 'active' : ''}">Main</a>
  <a href="/new-match" class="${page == 'newMatch' ? 'active' : ''}">New Match</a>
  <a href="/finished-matches" class="${page == 'matches' ? 'active' : ''}">Matches</a>
</div>

<style>
  /* Add your header and nav-menu CSS here */
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  body {
    font-family: Arial, sans-serif;
  }
  .header {
    background-color: #4CAF50;
    padding: 15px;
    text-align: center;
  }
  .header .logo {
    font-size: 24px;
    color: white;
    font-weight: bold;
  }
  .nav-menu {
    display: flex;
    justify-content: center;
    background-color: #333;
  }
  .nav-menu a {
    color: white;
    padding: 14px 20px;
    text-decoration: none;
    text-align: center;
    display: block;
  }
  .nav-menu a:hover {
    background-color: #ddd;
    color: black;
  }
  .nav-menu a.active {
    background-color: #f1c40f;
    color: #333;
  }
</style>
