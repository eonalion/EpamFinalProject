<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<!-- Footer -->
<footer>
    <div class="container">
        <div class="row nav-footer">
            <div class="col-md-3 col-sm-3  col-sx-3 footer-wrap">
                <header><fmt:message key="footer.menu"/></header>
                <ul>
                    <li><a href="/s?command=forward&forward_action=main"><fmt:message key="nav.home"/></a></li>
                    <li><a href="/s?command=forward&forward_action=catalog"><fmt:message key="nav.catalog"/></a></li>
                    <li><a href="/s?command=forward&forward_action=cart"><fmt:message key="nav.cart"/></a></li>
                </ul>
            </div>
            <div class="col-md-3  col-sm-3 col-sx-3  footer-wrap">
                <header><fmt:message key="footer.profile"/></header>
                <ul>
                    <li><a href="/s?command=forward&forward_action=settings"><fmt:message key="nav.account.settings"/></a></li>
                    <li><a href="/s?command=forward&forward_action=purchases"><fmt:message key="nav.account.purchases"/></a></li>
                    <li><a href="/s?command=log_out"><fmt:message key="nav.account.logout"/></a></li>
                </ul>
            </div>
            <div class="col-md-3  col-sm-3  col-sx-3 footer-wrap">
                <header><fmt:message key="label.language"/></header>
                <ul>
                    <li><a href="/s?command=change_language&locale=en_US"><fmt:message
                            key="label.language.en"/></a></li>
                    <li><a href="/s?command=change_language&locale=ru_RU"><fmt:message
                            key="label.language.ru"/></a></li>
                </ul>
            </div>
            <div class="col-md-3  col-sm-3 col-sx-3 footer-wrap">
                <header><fmt:message key="footer.links"/></header>
                <ul>
                    <li><a href=""><fmt:message key="footer.links.facebook"/></a></li>
                    <li><a href=""><fmt:message key="footer.links.twitter"/></a></li>
                </ul>
            </div>
        </div>
        <div class="row copyright-wrap">
            <div class="col-md-12 col-sm-12">
                <p><fmt:message key="footer.copyright"/></p>
            </div>
        </div>
    </div>
</footer>


<%-- JS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</body>
</html>
