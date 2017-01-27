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
    <title>Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%-- icon --%>
    <link rel="shortcut icon" href="../../images/favicon.ico"/>
    <%-- CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <link rel="stylesheet" type="text/css" href="../../css/bootstrap3_player.css">
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

        <c:forEach var="track" items="${trackList}">
            <%-- <c:set var="currentTrack" value="${track}"/>--%>
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
                                        <button type="submit" class="btn-custom add-to-cart-button" name="" value="">
                                            <fmt:message key="main.label.addToCart"/></button>

                                    </form>
                                    <button class="btn-custom btn-play"
                                            onclick='playTrack(${track.trackId}, "${track.location}")'>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <div class="col-md-12">
            <ul class="pagination pagination-sm">
                <li><a href="/s?command=switch_page&toFirstPage=true&toLastPage=false">«</a></li>
                <c:forEach var="i" begin="1" end="${pageAmount}">
                    <li><a href="/s?command=switch_page&pageNumber=${i}&toFirstPage=false&toLastPage=false">${i}</a>
                    </li>
                </c:forEach>
                <li><a href="/s?command=switch_page&toFirstPage=false&toLastPage=true">»</a></li>
            </ul>
        </div>

        <audio id="player" controls
               data-info-album-art="https://farm9.staticflickr.com/8642/16106988340_058071cdbe_z.jpg"
               data-info-album-title="${currentTrack.price}"
               data-info-artist="Iain Houston and Felix Gibbons"
               data-info-title="${currentTrack.title}"
               data-info-label="Independent"
               data-info-year="2005"
               data-info-att="Music: Iain Houston and Felix Gibbons."
               data-info-att-link="https://github.com/iainhouston">
            <source src="" type="audio/mpeg">
        </audio>
    </div>
</main>


<%-- Footer --%>
<c:import url="../../WEB-INF/jspf/footer.jsp"/>

<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/jquery.form.min.js"></script>
<script src="../../js/jquery.validate.min.js"></script>
<script src="../../js/bootstrap-3.3.1.min.js"></script>
<script src="../../js/classie.js"></script>
<script src="../../js/script.js"></script>
<script src="../../js/pagination.js"></script>
<%--<script src="../../js/main.js"></script>--%>
<script src="../../js/bootstrap3_player.js"></script>
<script src="../../js/play_track.js"></script>

</body>
</html>
