<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Подсчет калорий</title>
</head>
<body>
<hr>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Подсчет калорий</h2>
<table border="1" style="width: 50%">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan=2>Действие</th>
    </tr>
    <c:forEach var="meals" items="${mealList}">
        <c:set var="color" value="${meals.excess ? 'red' : 'green'}"/>
        <javatime:format value="${meals.dateTime}" pattern="dd.MM.yyyy HH:mm" var="parsedDate"/>
        <tr style="color: ${color}">
            <td><c:out value="${parsedDate}"/></td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
            <td><a href="meals?action=edit&id=<c:out value="${meals.id}"/>">Редактировать</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meals.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p>
<h2>Внести изменения</h2>
<table border="1" style="width: 50%">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th></th>
    </tr>
    <form method="POST" action='meals' accept-charset="UTF-8" name="addMeal">
        <input type="hidden" name="id" value="<c:out value="${meal.id}"/>"/>
        <tr>
            <td><input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}" />"/></td>
            <td><input type="text" name="description" value="<c:out value="${meal.description}" />"/></td>
            <td><input type="text" name="calories" value="<c:out value="${meal.calories}" />"/></td>
            <td align="center"><input type="submit" value="Добавить запись"/></td>
        </tr>
    </form>
</table>
</p>
</body>
</html>