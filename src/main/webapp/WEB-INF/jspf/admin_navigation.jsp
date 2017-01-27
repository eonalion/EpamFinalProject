<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adt" uri="http://suboch.by/jsp/" %>
<fmt:setLocale value="${visitor.locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<nav class="navbar navbar-inverse navbar-fixed-top complex-navbar">
    <div class="filter"></div>
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><fmt:message key="nav.website"/></a>
        </div>
        <ul class="nav navbar-nav">
            <li><a class="nav-menu-item nav-menu-item-active" href="/jsp/user/main.jsp"><fmt:message
                    key="nav.home"/></a>
            </li>
            <li><a class="nav-menu-item nav-menu-item-inactive"
                   href="/jsp/user/catalog.jsp"><fmt:message key="nav.catalog"/></a></li>
            <li><a class="nav-menu-item nav-menu-item-inactive"
                   href="/jsp/admin/add_new.jsp"><fmt:message
                    key="nav.addNew"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="nav-menu-item nav-menu-item-inactive" href="/jsp/user/cart.jsp"></span>
                <fmt:message key="nav.cart"/></a>
            </li>
            <li class="dropdown"><a class="avatar-a dropdown-toggle nav-menu-item nav-menu-item-inactive"
                                    data-toggle="dropdown"
                                    href="#" style="padding: 6px">${account.login}
                <c:choose>
                    <c:when test="${account.avatar != null}">
                        <%--<img class="avatar-nav" src="data:avatar/jpg;base64,${adt:base64Encoder(account.avatar)}"/>--%>
                        <img class="avatar-nav" src="/s?command=load_image&elementId=${account.accountId}&target=account"/>
                    </c:when>
                    <c:otherwise>
                        <img class="avatar-nav" src="../../images/default_avatar.jpg">
                    </c:otherwise>
                </c:choose>
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/jsp/user/settings.jsp"><fmt:message
                            key="nav.account.settings"/></a></li>
                    <li><a href="/jsp/user/purchases.jsp"><fmt:message
                            key="nav.account.purchases"/></a></li>
                    <li><a href="/s?command=log_out"><fmt:message key="nav.account.logout"/></a></li>
                </ul>
            </li>
        </ul>
        <form class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control form-search-control"
                       placeholder="<fmt:message key="nav.search"/>">
            </div>
        </form>
    </div>
</nav>