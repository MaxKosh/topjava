<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Приемы пищи</title>
</head>
<body>
<hr>
<h2>Приемы пищи</h2>
<table border="1" style="width: 50%">
    <tr>
        <th>Meal Id</th>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th colspan=2>Действие</th>
    </tr>
    <c:forEach var="meals" items="${mealList}">
        <c:set var="color" value="${meals.excess == true? 'red' : 'green'}"/>
        <javatime:format value="${meals.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
        <tr style="color: ${color}">
            <td><c:out value="${meals.mealId}"/></td>
            <td><c:out value="${parsedDate}"/></td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
            <td><a href="MealController?action=edit&mealId=<c:out value="${meals.mealId}"/>">Редактировать</a></td>
            <td><a href="MealController?action=delete&mealId=<c:out value="${meals.mealId}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="MealController?action=insert">Добавить прием пищи</a></p>
</body>
</html>