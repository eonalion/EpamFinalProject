<%--
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>$Title$</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../images/favicon.ico"/>
    <%-- CSS libraries --%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%-- Custom CSS --%>
    <link rel="stylesheet" type="text/css" href="../css/registration_login_style.css">
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
                <li class="nav-menu-item nav-menu-item-active" onclick="showForm(event)">Register</li>
                <li class="nav-menu-item nav-menu-item-inactive" onclick="showForm(event)">Log in</li>
            </ul>
        </nav>
        <section class="col-md-6 section-description md-section">
            <h3>Some text on the right</h3>
        </section>
    </div>
    <div class="row">
        <section id="register" class="active form col-md-6">
            <p>Welcome!</p>
            <form name="registration" method="post" action="/s">
                <input type="text" name="firstName" placeholder="First name" required>
                <input type="text" name="lastName" placeholder="Last name" required>
                <input type="text" name="login" placeholder="Login (username)" maxlength="40"
                       pattern="^[a-zA-Z][a-zA-Z0-9_]{4,}$" required>
                <input type="password" name="password" placeholder="Password"
                       pattern="^.*(?=.{6,})(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+).*$" required>
                <input type="password" name="passwordConfirm" placeholder="Repeat password"
                       pattern="^.*(?=.{6,})(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+).*$" required>
                <input type="text" name="email" placeholder="E-mail address" pattern="^.+@.+[.].+$"
                       required>
                <div class="input-wrap">
                    <input id="accept" type="checkbox" name="termsAccept" value="unchecked" required>
                    <label for="accept"></label>
                    <span>I agree to the terms of <a href="">[Site] Customer Agreement</a></span>
                </div>
                <button type="submit" name="command" value="register" onclick="onSubmitAction()">Register</button>
            </form>
        </section>
        <section id="log-in" class="inactive form col-md-6">
            <form>
                <input type="text" name="login-email" placeholder="Login or e-mail address" required>
                <input type="password" name="password" placeholder="Password" required>
                <button type="submit" name="log-in">Log in</button>
            </form>
        </section>
        <section class="col-md-6 section-paragraph">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                incididunt ut labore et.
                Ut wisi enim ad minim veniam, quis nostrud.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
                Ut wisi veniam, quis nostrud. Exerci tation ullamcorper
                suscipit lobortis nisl ut aliquip ex ea
                commodo consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit
                lobortis nisl.</p>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et.
                Ut wisi enim ad minim veniam, quis nostrud. Exerci tation ullamcorper suscipit</span> lobortis nisl ut
                aliquip ex ea
                commodo consequat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit
                lobortis nisl.</p>
        </section>
    </div>
</main>

<footer class="container">
    <div class="row copyright-wrap">
        <div class="col-md-12 col-sm-12">
            <p>Copyright &copy; Your Website 2017</p>
        </div>
    </div>
</footer>

<%-- JS --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries --%>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
</body>
</html>
