<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show people</title>
</head>
<body>
<h2>Dodany rekord:</h2>
<table>
    <thead>
    <tr>
        <c:forEach var="column" items="${columns}">
            <th>
                <c:out value="${column}"></c:out>
            </th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="cell" items="${record}">
            <td>
                <c:out value="${cell}"></c:out>
            </td>
        </c:forEach>
    </tr>

    </tbody>
</table>
</body>
</html>