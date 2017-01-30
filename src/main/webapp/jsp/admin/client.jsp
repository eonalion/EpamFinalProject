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
    <title>Current album</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%-- icon --%>
    <link rel="shortcut icon" href="../../images/favicon.ico"/>
    <%-- CSS --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/default.css">
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
        <a href="#">Create</a>
        <a href="#">Edit</a>
        <a href="#">Clients</a>
        <a href="#">Contact</a>
    </div>
    <div id="main">
        <div class="row">
            <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Open menu</span>
        </div>
        <div class="row">
            <h1>${currentAccount.login}</h1>
            <c:if test="${account.adminRights}"> <label>admin</label> </c:if>
        </div>
        <div class="row">
            <img src="/s?command=load_image&elementId=${currentAccount.accountId}&target=account"
                 onerror="this.src='../../images/default_avatar.jpg'" alt="">
        </div>
        <hr>
        <div class="row">
            First name:<p>${currentAccount.firstName}</p>
            Last name:<p>${currentAccount.lastName}</p>
            E-mail:<p>${currentAccount.email}</p>
        </div>
        <hr>
        <%-- Purchases --%>
        <%--<c:set var="i" value="1" scope="page"/>
        <ol>
            <c:forEach var="track" items="albumTracks">
                <li>
                    <a href="/s?command=show_element&type=track&id=${track.trackId}">${track.title}</a>
                </li>
                <c:set var="i" value="${i+1}" scope="page"/>
            </c:forEach>
        </ol>--%>
    </div>
</main>

<%-- Footer --%>
<c:import url="../../WEB-INF/jspf/footer.jsp"/>
<script src="../../js/jquery-3.1.1.min.js"></script>
<script src="../../js/bootstrap-3.3.1.min.js"></script>

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
