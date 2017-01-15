<%--
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<!-- Footer -->
<footer>
    <div class="container">
        <div class="row nav-footer">
            <div class="col-md-3 col-sm-3  col-sx-3 footer-wrap">
                <header><fmt:message key="footer.menu"/></header>
                <ul>
                    <li><a href=""><fmt:message key="nav.home"/></a></li>
                    <li><a href=""><fmt:message key="nav.catalog"/></a></li>
                    <li><a href=""><fmt:message key="nav.cart"/></a></li>
                </ul>
            </div>
            <div class="col-md-3  col-sm-3 col-sx-3  footer-wrap">
                <header><fmt:message key="footer.profile"/></header>
                <ul>
                    <li><a href=""><fmt:message key="nav.account.settings"/></a></li>
                    <li><a href=""><fmt:message key="nav.account.purchases"/></a></li>
                    <li><a href="/s?command=logout"><fmt:message key="nav.account.logout"/></a></li>
                </ul>
            </div>
            <div class="col-md-3  col-sm-3  col-sx-3 footer-wrap">
                <header><fmt:message key="label.language"/></header>
               <%-- <form action="/s" method="get">
                    <select name="locale">
                        <option value="en_US"><fmt:message key="label.language.en"/></option>
                        <option value="ru_RU"><fmt:message key="label.language.ru"/></option>
                    </select>
                    <button type="submit" name="command" value="changelanguage"><fmt:message
                            key="label.language"/></button>
                </form>--%>
                <ul>
                    <li><a href="/s?command=changelanguage&locale=en_US"><fmt:message
                            key="label.language.en"/></a></li>
                    <li><a href="/s?command=changelanguage&locale=ru_RU"><fmt:message
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