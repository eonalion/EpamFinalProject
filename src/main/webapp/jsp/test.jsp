
<%--
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1><fmt:message key="label.language"/></h1>
<h1><fmt:message key="label.language"/></h1>
<form action="/s" method="get">
    <select name="locale">
        <option value="en_US">English</option>
        <option value="ru_RU">Русский</option>
    </select>
    <button type="submit" name="command" value="changelanguage"><fmt:message key="label.language"/></button>
</form>
</body>
</html>
