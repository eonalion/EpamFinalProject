<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
<h1>Error</h1>
<h4>${message}</h4>
<button type="button" onclick="window.history.back()">Back</button>
</body>
</html>
