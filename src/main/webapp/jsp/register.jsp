<%--
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="authorization.title"/></title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../images/favicon.ico"/>
    <%-- CSS libraries --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%-- Custom CSS --%>
    <link rel="stylesheet" type="text/css" href="../css/register.css">
    <%-- JS libraries --%>
    <script src="../js/classie.js"></script>
    <%-- Custom JS --%>
    <script src="../js/script.js"></script>
</head>
<body>
<main class="container">
    <div class="row">
        <nav class="col-md-6">
            <ul>
                <li class="nav-menu-item nav-menu-item-active" onclick="showForm(event)"><fmt:message key="registration.label"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm(event)"><fmt:message key="login.label"/></li>
            </ul>
        </nav>
        <section class="col-md-6 section-description md-section">
            <h3><fmt:message key="authorization.section.header"/></h3>
        </section>
    </div>
    <div class="row">
        <section id="register" class="active form col-md-6">
            <p>Welcome!</p>
            <form name="registration" method="post" action="/s">
                <div class="row">
                    <input type="text" name="firstName" placeholder="<fmt:message key="registration.form.firstName"/>" required>
                </div>
                <div class="row">
                    <input type="text" name="lastName" placeholder="<fmt:message key="registration.form.lastName"/>" required>
                </div>
                <div class="row">
                    <input type="text" name="login" placeholder="<fmt:message key="registration.form.username"/>" maxlength="40"
                           pattern="^[a-zA-Z][a-zA-Z0-9_]{4,}$" required>
                </div>
                <div class="row">
                    <input type="password" name="password" placeholder="<fmt:message key="registration.form.password"/>"
                           pattern="^.*(?=.{6,})(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+).*$" required>
                </div>
                <div class="row">
                    <input type="password" name="passwordConfirm" placeholder="<fmt:message key="registration.form.confirmPassword"/>"
                           pattern="^.*(?=.{6,})(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+).*$" required>
                </div>
                <div class="row">
                    <input type="text" name="email" placeholder="<fmt:message key="registration.form.email"/>" pattern="^.+@.+[.].+$"
                           required>
                </div>
                <div class="row">
                    <div class="input-wrap">
                        <input id="accept" type="checkbox" name="termsAccept" value="unchecked" required>
                        <label for="accept"></label>
                        <span><fmt:message key="registration.form.acceptTerms.text"/> <a href=""><fmt:message key="registration.form.acceptTerms.agreement"/></a></span>
                    </div>
                </div>
                <div class="row">
                    <button type="submit" name="command" value="register" onclick="onSubmitAction()"><fmt:message key="registration.label"/></button>
                </div>
            </form>
        </section>
        <section id="log-in" class="inactive form col-md-6">
            <form name="login" method="post" action="/s">
                <div class="row">
                    <input type="text" name="authorizationName" placeholder="<fmt:message key="login.form.authName"/>" required>
                </div>
                <div class="row">
                    <input type="password" name="password" placeholder="<fmt:message key="login.form.password"/>" required>
                </div>
                <div class="row">
                    <button type="submit" name="command" value="login"><fmt:message key="login.label"/></button>
                </div>
            </form>
        </section>
        <section class="col-md-6 section-paragraph">
            <p><fmt:message key="authorization.section.p1"/></p>
            <p><fmt:message key="authorization.section.p2"/></p>
            <p><fmt:message key="authorization.section.p3"/></p>
        </section>
    </div>
    <form action="/s" method="get">
        <select name="locale">
            <option value="en_US">English</option>
            <option value="ru_RU">Русский</option>
        </select>
        <button type="submit" name="command" value="changelanguage"><fmt:message key="label.language"/></button>
    </form>
</main>


<jsp:include page="footer.jsp"/>

<%-- JS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

</body>
</html>