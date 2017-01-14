<%--
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>

<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <%-- JS libraries --%>
    <script src="../../js/classie.js"></script>
    <%-- Custom JS --%>
    <script src="../../js/script.js"></script>
</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top complex-navbar">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><fmt:message key="nav.website"/></a>
        </div>
        <ul class="nav navbar-nav">
            <li><a class="nav-menu-item nav-menu-item-active" href="#"><fmt:message key="nav.home"/></a></li>
            <li><a class="nav-menu-item nav-menu-item-inactive" href="#"><fmt:message key="nav.catalog"/></a></li>
            <li class="dropdown"><a class="dropdown-toggle nav-menu-item nav-menu-item-inactive" data-toggle="dropdown"
                                    href="#"><fmt:message key="nav.addNew"/><span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#"><fmt:message key="nav.addNew.admin"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.track"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.album"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.artist"/></a></li>
                    <li><a href="#"><fmt:message key="nav.addNew.bonus"/></a></li>
                </ul>
            </li>
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
                    <li>
                        <form method="get" action="/s" id="admin_logout">
                            <input type="hidden" name="command" value="logout"/>
                            <a href="javascript: document.getElementById('admin_logout').submit()"><fmt:message
                                    key="nav.account.logout"/></a>
                        </form>
                    </li>
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

<main>
    <div class="container">
        <!-- Content Section -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2 class="section-heading"><fmt:message key="main.welcomeText.head"/></h2>
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

        <!-- Page Features -->
        <div class="row text-center">
            <div class="col-md-4 col-sm-6 item">
                <div class="thumbnail">
                    <img src="http://placehold.it/300x300" alt="">
                    <div class="caption">
                        <h4><a href="#">Track</a></h4>
                        <h5><a href="#">Artist</a></h5>
                        <div class="col-md-2 col-sm-2">
                            <p class="price">0.2$</p>
                        </div>
                        <div class="col-md-10 col-sm-10">
                            <button name="" value=""><fmt:message key="main.label.addToCart"/></button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 col-sm-6 item">
                <div class="thumbnail">
                    <img src="http://placehold.it/300x300" alt="">
                    <div class="caption">
                        <h4><a href="#">Track</a></h4>
                        <h5><a href="#">Artist</a></h5>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 col-sm-2">
                                    <p class="price">0.2$</p>
                                </div>
                                <div class="col-md-10 col-sm-10">
                                    <button name="" value="">Add to cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 col-sm-6 item">
                <div class="thumbnail">
                    <img src="http://placehold.it/300x300" alt="">
                    <div class="caption">
                        <h4><a href="#">Track</a></h4>
                        <h5><a href="#">Artist</a></h5>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 col-sm-2">
                                    <p class="price">0.2$</p>
                                </div>
                                <div class="col-md-10 col-sm-10">
                                    <button name="" value="">Add to cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-4 col-sm-6 item">
                <div class="thumbnail">
                    <img src="http://placehold.it/300x300" alt="">
                    <div class="caption">
                        <h4><a href="#">Track</a></h4>
                        <h5><a href="#">Artist</a></h5>
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-2 col-sm-2">
                                    <p class="price">0.2$</p>
                                </div>
                                <div class="col-md-10 col-sm-10">
                                    <button name="" value="">Add to cart</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.row -->

    </div>
</main>

<%-- Footer --%>
<jsp:include page="../footer.jsp"/>

<%-- JS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

</body>
</html>
