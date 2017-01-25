<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${visitor.locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <title>Catalog</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../images/favicon.ico"/>
    <%-- CSS libraries --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%-- Custom CSS --%>
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <%-- JS libraries --%>
    <script src="../../js/classie.js"></script>
    <%-- Custom JS --%>
    <script src="../../js/script.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${visitor.role eq 'ADMIN'}">
        <jsp:include page="../../WEB-INF/jspf/admin_navigation.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../../WEB-INF/jspf/user_navigation.jsp"/>
    </c:otherwise>
</c:choose>

<main class="container">

    <c:forEach begin="0" end="${tracks.size}" varStatus="loop">
        <div class="col-md-4 col-sm-6 item">
            <div class="thumbnail">
                <img src="http://placehold.it/300x300" alt="">
                <div class="caption">
                    <h4><a href="#">Track</a></h4>
                    <h5><a href="#">Artist</a></h5>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-4 col-sm-4">
                                <p class="price">0.2$</p>
                            </div>
                            <div class="col-md-8 col-sm-8">
                                <button name="" value="">Add to cart</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="col-md-12">
        <ul class="pagination">
            <li><a href="#">«</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">»</a></li>
        </ul>
    </div>
</main>

<jsp:include page="../../WEB-INF/jspf/footer.jsp"/>
</body>
</html>
