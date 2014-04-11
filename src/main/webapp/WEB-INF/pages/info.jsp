<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h1>Title: ${title}</h1>
    <table border="1">
        <thead>
            <td><b>Link name: </b></td>
            <td><b>Link URL:</b></td>
        </thead>
        <c:forEach items="${links}" var="link">
            <tr>
                <td>${link.key}</td>
                <td>${link.value}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>