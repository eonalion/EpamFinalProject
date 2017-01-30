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
    <%-- JS libraries --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap3_player.css">
    <link rel="stylesheet" type="text/css" href="../../css/sidebar.css">
    <%-- Custom CSS --%>
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
    <div id="side-nav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a href="../../jsp/user/catalog_artists.jsp">Artists</a>
        <a href="../../jsp/user/catalog_albums.jsp">Albums</a>
        <a href="../../jsp/user/catalog_tracks.jsp">Tracks</a>
    </div>
    <div id="main">
        <div class="row">
            <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Open menu</span>
        </div>
        <h1>Artists</h1>
        <c:forEach var="artist" items="${artistList}">
            <a href="/s?command=show_element&type=artist&id=${artist.artistId}">${artist.name}</a>
            <hr>
        </c:forEach>

    </div>
</main>

<jsp:include page="../../WEB-INF/jspf/footer.jsp"/>

<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/jquery.form.min.js"></script>
<script src="../../js/jquery.validate.min.js"></script>
<script src="../../js/bootstrap-3.3.1.min.js"></script>
<script src="../../js/classie.js"></script>
<script src="../../js/script.js"></script>
<script src="../../js/pagination.js"></script>
<%--
<script src="../../js/bootstrap3_player.js"></script>--%>
<script src="../../js/play_track.js"></script>

<script>
    function openNav() {
        document.getElementById("side-nav").style.width = "200px";
        document.getElementById("main").style.marginLeft = "100px";
    }

    function closeNav() {
        document.getElementById("side-nav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }
</script>
</body>
</html>
