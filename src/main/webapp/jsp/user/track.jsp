<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adt" uri="http://suboch.by/jsp/" %>
<fmt:setLocale value="${visitor.locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<!DOCTYPE html>
<html>
<head>
    <title>Current track</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%-- icon --%>
    <link rel="shortcut icon" href="../../images/favicon.ico"/>
    <%-- CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/default.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap3_player.css">
    <link rel="stylesheet" type="text/css" href="../../css/sidebar.css">
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
        <div class="row">
            <h1>${currentTrack.title}</h1>
            Album:<a href="/s?command=show_element&id=${currentTrack.albumId}">Album title</a>
            Artist:<a href="/s?command=show_album&id=${currentTrack.albumId}">Artist name</a>
        </div>
        <div class="row">
            <img src="/s?command=load_image&elementId=${currentTrack.albumId}&target=album"
                 onerror="this.src='../../images/default_album.png'" alt="">
        </div>
        <hr>
        <div class="row">
            Price:<p class="price">${currentTrack.price}$</p>
            <form class="add-to-cart" method="post" action="/s">
                <button type="submit" class="btn-custom add-to-cart-button" name="" value="">
                    <fmt:message key="main.label.addToCart"/></button>
            </form>
        </div>
        <div class="row">
            <button class="btn-custom btn-play"
                    onclick='playTrack(${currentTrack.trackId}, "${currentTrack.location}")'>
            </button>
        </div>
        <div class="row">
            <audio id="player" controls
                   data-info-album-art="/s?command=load_image&elementId="
                   data-info-album-title="${currentTrack.price}"
                   data-info-artist="Iain Houston and Felix Gibbons"
                   data-info-title="${currentTrack.title}"
                   data-info-label="Independent"
                   data-info-year="2005"
                   data-info-att="Music: Iain Houston and Felix Gibbons."
                   data-info-att-link="}">
                <source src="" type="audio/mpeg">
            </audio>
        </div>
    </div>

</main>

<c:import url="../../WEB-INF/jspf/footer.jsp"/>
<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/bootstrap-3.3.1.min.js"></script>
<script src="../../js/bootstrap3_player.js"></script>
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
