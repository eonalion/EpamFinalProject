<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
</head>
<body>
<c:if test="${visitor.role == 'GUEST'}">
    <c:redirect url="/jsp/guest/register.jsp"/>
</c:if>
<h1>404</h1>
</body>
</html>
