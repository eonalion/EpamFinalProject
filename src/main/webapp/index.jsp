
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<html>
<body>
<jsp:forward page="jsp/register.jsp"/>
</body>
</html>
