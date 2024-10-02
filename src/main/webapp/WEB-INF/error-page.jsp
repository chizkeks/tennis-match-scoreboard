<%--
  Created by IntelliJ IDEA.
  User: Ilya
  Date: 29.09.2024
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <body>
    <jsp:include page="header.jsp" />
    <h1>
      Oops, something went wrong :(
      Try again
    </h1>
    <h2>Error description: ${requestScope.errorMessage}</h2>
  </body>
</html>
