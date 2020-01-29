<%--
  Created by IntelliJ IDEA.
  User: sinrul
  Date: 16.01.2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <title>Exception</title>
</head>
<body>
<table>
    <c:choose>
        <c:when test="${!empty id}">
        <tr>
            <td>Сотрудник с ID = '${id}' не найден</td>
        </tr>
        </c:when>
        <c:when test="${!empty name}">
            <tr>
                <td>Сотрудник(и) с именем '${name}' не найден(ы)</td>
            </tr>
        </c:when>
        <c:when test="${!empty date}">
            <tr>
                <td>Сотрудник(и) с датой рождения: '${date}' не найден(ы)</td>
            </tr>
        </c:when>
    </c:choose>
        <tr>
            <td><a class="id" href="/">Назад</a></td>
        </tr>

</table>
</body>
</html>
