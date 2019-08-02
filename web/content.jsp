<%@ page import="java.util.List" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show people</title>
</head>
<body>
<h2>Zawartość tabeli:</h2>
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
    <% List<String> cols = (List<String>) request.getAttribute("columns");
        List<String> content = (List<String>) request.getAttribute("content");
        for (int i = 0; i < content.size(); i += cols.size()) {
    %>
    <tr>
        <% for (int j = 0; j < cols.size(); j++) {
        %>
        <td><%=content.get(i + j) %>
        </td>
        <% } %>
    </tr>
    <% } %>

    </tbody>
</table>
</body>
</html>