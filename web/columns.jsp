<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show people</title>
</head>
<body>
<h2>Lista kolumn danej tabeli:</h2>
<ul>
    <c:forEach var="column" items="${columns}">
        <li>
            <c:out value="${column}"></c:out>
        </li>
    </c:forEach>
</ul>
</body>
</html>