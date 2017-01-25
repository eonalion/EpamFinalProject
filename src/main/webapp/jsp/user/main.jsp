<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adt" uri="http://suboch.by/jsp/" %>
<fmt:setLocale value="${visitor.locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<!DOCTYPE html>
<html>
<head>
    <title></title>
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
    <script src="../../js/jquery-3.1.1.min.js"></script>
    <%--
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
        <script src="http://malsup.github.com/jquery.form.js"></script>--%>
    <script src="../../js/jquery.form.min.js"></script>
    <script src="../../js/jquery.validate.min.js"></script>
    <%-- Custom JS --%>
    <script src="../../js/main.js"></script>
    <script src="../../js/script.js"></script>
</head>

<body><%--
<!-- Navigation -->
<c:import url="user_navigation.jsp"/>--%>

<c:choose>
    <c:when test="${visitor.role eq 'ADMIN'}">
        <jsp:include page="../../WEB-INF/jspf/admin_navigation.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="../../WEB-INF/jspf/user_navigation.jsp"/>
    </c:otherwise>
</c:choose>

<main>
    <div class="container">
        <!-- Content Section -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="section-heading"><fmt:message key="main.welcomeText.head"/> ${account.login}!</h2>
                        <p class="lead section-lead"><fmt:message key="main.welcomeText.p1"/></p>
                        <p class="section-paragraph"><fmt:message key="main.welcomeText.p2"/></p>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <p class="section-lead"><fmt:message key="main.textBeforeItems"/></p>
                    </div>
                </div>
            </div>
        </section>


        <c:forEach items="${trackList}" var="track">
            <div class="col-md-4 col-sm-6 item">
                <div class="thumbnail">
                        <%--<img src="/s?......http://placehold.it/300x300" alt="">--%>
                    <img src="http://placehold.it/300x300" alt="">
                    <div class="caption">
                        <h4><a href="#">${track.title}</a></h4>
                            <%-- <h5><a href="#">${track.artist}</a></h5>--%>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-4 col-sm-4">
                                    <p class="price">${track.price}$</p>
                                </div>
                                <div class="col-md-8 col-sm-8">
                                    <form class="add-to-cart" method="post" action="/s">
                                        <button type="submit" class="add-to-cart-button" name="" value=""><fmt:message key="main.label.addToCart"/></button>
                                    </form>
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

    </div>
</main>

<%-- Footer --%>
<c:import url="../../WEB-INF/jspf/footer.jsp"/>

</body>
</html>
