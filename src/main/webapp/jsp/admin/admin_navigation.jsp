<%--
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<nav class="navbar navbar-inverse navbar-fixed-top complex-navbar">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><fmt:message key="nav.website"/></a>
        </div>
        <ul class="nav navbar-nav">
            <li><a class="nav-menu-item nav-menu-item-active" href="#"><fmt:message key="nav.home"/></a></li>
            <li><a class="nav-menu-item nav-menu-item-inactive" href="#"><fmt:message key="nav.catalog"/></a></li>
            <li><a class="nav-menu-item nav-menu-item-inactive" href="/s?command=addnew"><fmt:message key="nav.addNew"/></a></li>
            <%--<li class="dropdown"><a class="dropdown-toggle nav-menu-item nav-menu-item-inactive" data-toggle="dropdown"
                                    href="#"><fmt:message key="nav.addNew"/><span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><fmt:message key="nav.addNew.admin"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.track"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.album"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.artist"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.bonus"/></a></li>
                </ul>
            </li>--%>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a class="nav-menu-item nav-menu-item-inactive" href="#"><span
                    class="glyphicon glyphicon-cart"></span><fmt:message key="nav.cart"/></a>
            </li>
            <li class="dropdown"><a class="dropdown-toggle nav-menu-item nav-menu-item-inactive" data-toggle="dropdown"
                                    href="#"><fmt:message key="nav.account"/><span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><fmt:message key="nav.account.settings"/></a></li>
                    <li><a href="#"><fmt:message key="nav.account.purchases"/></a></li>
                    <li><a href="/s?command=logout"><fmt:message key="nav.account.logout"/></a></li>
                </ul>
            </li>
        </ul>
        <form class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control form-search-control" placeholder="<fmt:message key="nav.search"/>">
            </div>
        </form>
    </div>
</nav>