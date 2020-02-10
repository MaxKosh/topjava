<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Приемы пищи</title>
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
        <c:set var="color" value="${meals.excess == true? 'red' : 'green'}"/>
        <javatime:format value="${meals.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
        <tr style="color: ${color}">
            <td><c:out value="${parsedDate}"/></td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meals.mealId}"/>">Редактировать</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meals.mealId}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<table border="1" style="width: 50%">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Действие</th>
    </tr>
    <form method="POST" action='meals' name="frmAddMeal">
        Meal ID : <input
            type="text" readonly="readonly" name="mealId"
            value="<c:out value="${meal.mealId}" />"/> <br/>
        <tr>
            <td><input type="text" name="dateTime" value="<c:out value="${meals.dateTime}" />"/></td>
            <td><input type="text" name="description" value="<c:out value="${meals.description}" />"/></td>
            <td><input type="text" name="calories" value="<c:out value="${meals.calories}" />"/></td>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </form>
</table>
</body>
</html>