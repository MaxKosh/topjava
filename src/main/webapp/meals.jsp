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
<section>
    <div style="text-align: center;">
        <h3><a href="index.html">Home</a></h3>
        <hr/>
        <h2>Моя еда</h2>


        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr style="align-content: center">
                <th  style="border: 0" width="25%">От даты</th>
                <th  style="border: 0" width="25%">До даты</th>
                <th  style="border: 0" width="25%">От времени</th>
                <th  style="border: 0" width="25%">До времени</th>
            </tr>
            <form method="GET" action='meals/filter' accept-charset="UTF-8" name="filterMeal">
                <tr>
                    <td align="center" style="border: 0"><input type="date" name="startDate"/></td>
                    <td align="center" style="border: 0"><input type="date" name="endDate"/></td>
                    <td align="center" style="border: 0"><input type="time" name="startTime"/></td>
                    <td align="center" style="border: 0"><input type="time" name="endTime"/></td>
                </tr>
                <tr>
                    <td  align="right" style="border: 0" colspan="4">
                        <button onclick="window.history.back()" type="button">Отменить</button>
                        <button type="submit">Отфильтровать</button>
                    </td>
                </tr>
            </form>
            </tr>
            </thead>
        </table>
        <br><br>

        <div style="margin-right: 30%;"><a href="meals?action=create">Добавить</a></div>
        <br><br>
        <style>
            table {
                margin: auto; /* Выравниваем таблицу по центру окна  */
            }
        </style>
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
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>