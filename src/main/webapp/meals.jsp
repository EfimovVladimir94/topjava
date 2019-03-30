<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
        }

        td {
            border: 1px solid #000000;
            text-align: left;
            padding: 8px;
        }

        th {
            border: 1px solid #000000;
            background-color: #efefeb;
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<br>
<table border="1">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <jsp:useBean id="formatter" scope="request" type="java.time.format.DateTimeFormatter"/>

    <c:forEach var="mealTo" items="${mealList}">
        <c:choose>
            <c:when test="${mealTo.excees}">
                <tr style="color: red">
            </c:when>
            <c:otherwise>
                <tr style="color: green">
            </c:otherwise>
        </c:choose>
        <td>${mealTo.dateTime.format(formatter)}</td>
        <td>${mealTo.description}</td>
        <td>${mealTo.calories}</td>
        <td>
            <a href="${pageContext.request.contextPath}/meals?action=delete&mealToId=${mealTo.id}">delete</a>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/meals?action=edit&mealToId=${mealTo.id}">edit</a>
        </td>
        </tr>
    </c:forEach>
</table>

<h3>Add new entry here:</h3>
<table border="2">
    <tr>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/meals">
                Date: <label><input type="datetime-local" name="dateTime"></label><br>
                Description: <label><input type="text" name="description"></label><br>
                Calories: <label><input type="number" name="calories"></label><br>
                <input type="submit" value="Add">
            </form>
        </td>
    </tr>
</table>

</body>
</html>