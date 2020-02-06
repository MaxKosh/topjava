<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1" style="width: 50%">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    <c:forEach var="meals" items="${mealList}">
        <c:set var="color" value="${meals.excess == true? 'red' : 'green'}"/>
        <javatime:format value="${meals.dateTime}" pattern="yyyy-MM-dd HH:mm" var="parsedDate"/>
        <tr style="color: ${color}">
            <td><c:out value="${parsedDate}"/>
                <c:out value="${parsedTime}"/></td>
            <td><c:out value="${meals.description}"/></td>
            <td><c:out value="${meals.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>