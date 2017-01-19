<%--
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adt" uri="http://suboch.by/jsp/" %>
<fmt:setLocale value="${locale}" scope="session"/>
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
    <link rel="stylesheet" type="text/css" href="../../css/main.css">
    <%-- JS libraries --%>
    <script src="../../js/classie.js"></script>
    <%-- Custom JS --%>
    <script src="../../js/script.js"></script>
</head>

<body>
<!-- Navigation -->
<c:import url="admin_navigation.jsp"/>

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
<c:import url="../../WEB-INF/jspf/footer.jsp"/>

</body>
</html>
