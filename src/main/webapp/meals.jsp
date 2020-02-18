<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<style>
    table {
        margin: auto;
    }
</style>
<div style="text-align: center">
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Моя еда</h2>
</div>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr style="align-content: center">
        <th style="border: 0" width="25%">От даты</th>
        <th style="border: 0" width="25%">До даты</th>
        <th style="border: 0" width="25%">От времени</th>
        <th style="border: 0" width="25%">До времени</th>
    </tr>
    <form method="GET" action='meals' accept-charset="UTF-8" name="filterMeal">
        <input type="hidden" name="action" value="filter">
        <tr align="center">
            <td style="border: 0"><input type="date" name="startDate" value="${param.startDate}" required/></td>
            <td style="border: 0"><input type="date" name="endDate" value="${param.endDate}" required/></td>
            <td style="border: 0"><input type="time" name="startTime" value="${param.startTime}" required/></td>
            <td style="border: 0"><input type="time" name="endTime" value="${param.endTime}" required/></td>
        </tr>
        <tr>
            <td align="right" style="border: 0" colspan="4">
                <button type="reset" onclick="window.location.href='meals'">Отмена</button>
                <button type="submit">Отфильтровать</button>
            </td>
        </tr>
    </form>
    </tr>
    </thead>
</table>
<br><br>
<div style="text-align: center">
    <button type="button" onclick="window.location.href='meals?action=create'">Добавить</button>
</div>
<br><br>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
        <tr class="${meal.excess ? 'excess' : 'normal'}">
            <td>
                    ${fn:formatDateTime(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>