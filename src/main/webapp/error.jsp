<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 28-04-2021
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <style><%@include file="/css/style.css"%></style>
</head>
<body>
<div class="login">
    <div class="heading">
        <h2>Sign in</h2>
        <form action="home" method="get">

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-users"></i></span>
                <input type="text" class="form-control" name="UserName" placeholder="Username or email" required />
            </div>

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="Password" placeholder="Password" required />
            </div>

            <div class="input-group input-group-md">
                <span class="input-group-addon"><i class="fa fa-frown-o"></i></span>
                <span class="form-control"><a href="forgot.jsp">Forgot Password</a></span>
            </div>

            <div class="input-group input-group-md">
                <span class="form-control wrong">User Name or Password is incorrect</span>
            </div>

            <button type="submit" class="float">Login</button>
        </form>
    </div>
</div>
</body>
</html>