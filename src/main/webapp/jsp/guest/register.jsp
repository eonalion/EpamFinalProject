<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${visitor.locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="authorization.title"/></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%-- icon --%>
    <link rel="shortcut icon" href="../../images/favicon.ico"/>
    <%-- CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/register.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap3_player.css">
</head>
<body>
<main class="container">
    <div class="row">
        <nav class="col-md-6">
            <ul>
                <li class="nav-menu-item nav-menu-item-active" onclick="showForm('register-section', this)"><fmt:message
                        key="registration.label"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm('log-in-section', this)"><fmt:message
                        key="login.label"/></li>
            </ul>
        </nav>
        <section class="col-md-6 section-description md-section">
            <h3><fmt:message key="authorization.section.header"/></h3>
        </section>
    </div>
    <%--    <div class="row"><h3><c:out value="${sessionScope.message}"/></h3></div>--%>
    <div class="row">
        <section id="register-section" class="active-section col-md-6">
            <p>Welcome!</p>
            <form name="registration" id="registration" method="post" action="/s">
                <div class="row">
                    <input type="text" name="firstName" placeholder="<fmt:message key="registration.form.firstName"/>"
                    >
                    <p>fdhgk</p>
                </div>
                <div class="row">
                    <input type="text" name="lastName" placeholder="<fmt:message key="registration.form.lastName"/>"
                    >
                </div>
                <div class="row">
                    <input type="text" name="login" placeholder="<fmt:message key="registration.form.username"/>"
                           maxlength="40"
                           pattern="^\\p{L}(\\p{L}|\\p{N}|[_])*$">
                </div>
                <div class="row">
                    <input type="password" name="password" placeholder="<fmt:message key="registration.form.password"/>"
                           pattern="^.*(?=.{6,})(?=.*(\\p{Ll})+)(?=.*(\\p{N})+).*$">
                </div>
                <div class="row">
                    <input type="password" name="passwordConfirm"
                           placeholder="<fmt:message key="registration.form.confirmPassword"/>"
                           pattern="^.*(?=.{6,})(?=.*(\\p{Ll})+)(?=.*(\\p{N})+).*$">
                </div>
                <div class="row">
                    <input type="text" name="email" placeholder="<fmt:message key="registration.form.email"/>"
                           pattern="^.+@.+[.].+$"
                    >
                </div>
                <div class="row">
                    <div class="input-wrap">
                        <input id="accept" type="checkbox" name="termsAccept" value="unchecked" required>
                        <label for="accept"></label>
                        <span><fmt:message key="registration.form.acceptTerms.text"/> <a href=""><fmt:message
                                key="registration.form.acceptTerms.agreement"/></a></span>
                    </div>
                </div>
                <div class="row">
                    <button class="btn-custom" type="submit" name="command" value="register" onclick=""><fmt:message
                            key="registration.label"/></button>
                </div>
            </form>
        </section>
        <section id="log-in-section" class="inactive-section col-md-6">
            <form name="login" id="log-in" method="post" action="/s">
                <div class="row">
                    <input type="text" name="authorizationName" placeholder="<fmt:message key="login.form.authName"/>"
                           required>
                </div>
                <div class="row">
                    <input type="password" name="password" placeholder="<fmt:message key="login.form.password"/>"
                           required>
                </div>
                <div class="row">
                    <button class="btn-custom" type="submit" name="command" value="log_in"><fmt:message
                            key="login.label"/></button>
                </div>
            </form>
        </section>
        <section class="col-md-6 section-paragraph">
            <p><fmt:message key="authorization.section.p1"/></p>
            <p><fmt:message key="authorization.section.p2"/></p>
            <p><fmt:message key="authorization.section.p3"/></p>
        </section>
    </div>
    <form class="row" action="/s" method="get">
        <select name="locale">
            <option value="en_US">English</option>
            <option value="ru_RU">Русский</option>
        </select>
        <button class="btn-custom" type="submit" name="command" value="change_language"><fmt:message
                key="label.language"/></button>
    </form>

  <%--  <div class="row">
        <audio controls
               data-info-album-art="https://farm9.staticflickr.com/8642/16106988340_058071cdbe_z.jpg"
               data-info-album-title="8874"
               data-info-artist="Iain Houston and Felix Gibbons"
               data-info-title="BeBop Aliens"
               data-info-label="Independent"
               data-info-year="2005"
               data-info-att="Music: Iain Houston and Felix Gibbons."
               data-info-att-link="https://github.com/iainhouston">
            <source src="/s?command=load_track&trackId=22&location=a/Artist2_Album3_Track4.mp3" type="audio/mpeg">
        </audio>
    </div>--%>
</main>
<%-- JS libraries --%><%--
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>--%>
<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/bootstrap-3.3.1.min.js"></script>
<script src="../../js/classie.js"></script>
<script src="../../js/registration_login.js"></script>
<script src="../../js/script.js"></script>
<script src="../../js/bootstrap3_player.js"></script>

</body>
</html>