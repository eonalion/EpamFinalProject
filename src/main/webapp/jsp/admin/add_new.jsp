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
    <link rel="stylesheet" type="text/css" href="../../css/nav_tabs.css">
    <%-- JS libraries --%>
    <script src="../../js/classie.js"></script>
    <%-- Custom JS --%>
    <script src="../../js/script.js"></script>
</head>
<body>
<!--Navigation -->
<c:import url="../../WEB-INF/jspf/admin_navigation.jsp"/>

<main class="container">
    <div class="row">
        <nav class="col-md-12">
            <ul>
                <li class="nav-menu-item nav-menu-item-active" onclick="showForm('add-adminRights', this)"><fmt:message
                        key="main.addNew.adminRights"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm('add-track', this)"><fmt:message
                        key="main.addNew.track"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm('add-artist', this)"><fmt:message
                        key="main.addNew.artist"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm('add-album', this)"><fmt:message
                        key="main.addNew.album"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm('add-genre', this)"><fmt:message
                        key="main.addNew.genre"/></li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm('add-bonus', this)"><fmt:message
                        key="main.addNew.bonus"/></li>
            </ul>
        </nav>
    </div>

    <div class="row">
        <section id="add-adminRights" class="active-section col-md-12">
            <form method="post" action="/s">
                <div class="row">
                    <input type="text" name="firstName" placeholder="<fmt:message key="registration.form.firstName"/>"
                           required>
                </div>
                <div class="row">
                    <input type="text" name="lastName" placeholder="<fmt:message key="registration.form.lastName"/>"
                           required>
                </div>
                <div class="row">
                    <input type="text" name="login" placeholder="<fmt:message key="registration.form.username"/>"
                           maxlength="40"
                           pattern="^\\p{L}(\\p{L}|\\p{N}|[_])*$" required>
                </div>
                <div class="row">
                    <input type="password" name="password" placeholder="<fmt:message key="registration.form.password"/>"
                           pattern="^.*(?=.{6,})(?=.*(\\p{Ll})+)(?=.*(\\p{N})+).*$" required>
                </div>
                <div class="row">
                    <input type="password" name="passwordConfirm"
                           placeholder="<fmt:message key="registration.form.confirmPassword"/>"
                           pattern="^.*(?=.{6,})(?=.*(\\p{Ll})+)(?=.*(\\p{N})+).*$" required>
                </div>
                <div class="row">
                    <input type="text" name="email" placeholder="<fmt:message key="registration.form.email"/>"
                           pattern="^.+@.+[.].+$"
                           required>
                </div>
                <div class="row">
                    <button type="submit" name="command" value="add_admin" onclick=""><fmt:message
                            key="main.addNew.addAdmin"/></button>
                </div>
            </form>
        </section>
        <section id="add-track" class="inactive-section col-md-12">
            <form method="post" action="/s">
                <div class="row">
                    <input type="text" name="title" placeholder="<fmt:message key="main.addNew.form.track"/>"
                           required>
                </div>
                <div class="row">
                    <input type="text" name="trackGenre" placeholder="<fmt:message key="main.addNew.form.genres"/>"
                           required>
                </div>
                <div class="row">
                    <input type="text" name="price" placeholder="<fmt:message key="main.addNew.form.price"/>"
                           required>
                </div>
                <div class="row">
                    <input type="text" name="discount" placeholder="<fmt:message key="main.addNew.form.discount"/>"
                           required>
                </div>
                <div class="row">
                    <button type="submit" name="command" value="add_track" onclick=""><fmt:message
                            key="main.addNew.addTrack"/></button>
                </div>
            </form>
        </section>
        <section id="add-artist" class="inactive-section col-md-12">
            <form method="post" action="/s">
                <div class="row">
                    <input type="text" name="artistName" placeholder="<fmt:message key="main.addNew.form.artist"/>"
                           required>
                </div>
                <div class="row">
                    <input type="text" name="artistCountry" placeholder="<fmt:message key="main.addNew.form.country"/>"
                           >
                </div>
                <div class="row">
                    <input type="text" name="artistDescription" class="description"
                           placeholder="<fmt:message key="main.addNew.form.description"/>" >
                </div>
                <div class="row">
                    <button type="submit" name="command" value="add_artist" onclick=""><fmt:message
                            key="main.addNew.addArtist"/></button>
                </div>
            </form>
        </section>
        <section id="add-album" class="inactive-section col-md-12">
            <form method="post" action="/s">
                <div class="row">
                    <input type="text" name="albumTitle" placeholder="<fmt:message key="main.addNew.form.album"/>"
                           required>
                </div>
                <div class="row">
                    <input type="date" name="albumReleaseDate"
                           placeholder="<fmt:message key="main.addNew.form.releaseDate"/>" >
                </div>
                <div class="row">
                    <button type="submit" name="command" value="add_album" onclick=""><fmt:message
                            key="main.addNew.addAlbum"/></button>
                </div>
            </form>
        </section>
        <section id="add-genre" class="inactive-section col-md-12">
            <form method="post" action="/s">
                <div class="row">
                    <input type="text" name="genreName" placeholder="<fmt:message key="main.addNew.form.genre"/>"
                           required>
                </div>
                <div class="row">
                    <button type="submit" name="command" value="add_genre" onclick=""><fmt:message
                            key="main.addNew.addGenre"/></button>
                </div>
            </form>
        </section>
        <section id="add-bonus" class="inactive-section col-md-12">
            <form method="post" action="/s">
                <div class="row">
                    <input type="text" name="bonusPrice" placeholder="<fmt:message key="main.addNew.form.price"/>"
                           pattern="^\d+(\.\d+)?$" required>
                </div>
                <div class="row">
                    <input type="text" name="bonusDiscount" placeholder="<fmt:message key="main.addNew.form.discount"/>"
                           required>
                </div>
                <div class="row">
                    <button type="submit" name="command" value="add_bonus" onclick=""><fmt:message
                            key="main.addNew.addBonus"/></button>
                </div>
            </form>
        </section>
    </div>
</main>

<%-- Footer --%>
<c:import url="../../WEB-INF/jspf/footer.jsp"/>

</body>
</html>