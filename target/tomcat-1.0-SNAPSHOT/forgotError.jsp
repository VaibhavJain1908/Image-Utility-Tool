<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 29-04-2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
    <style><%@include file="/css/style.css"%></style>
</head>
<body>
<div class="login">
    <div class="heading">
        <h2>Forgot Password</h2>
        <form action="successful.jsp" method="post">

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-users"></i></span>
                <input type="text" class="form-control" name="UserName" placeholder="Username or email" required />
            </div>

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="text" class="form-control" name="NewPassword" placeholder="New Password" required />
            </div>

            <div class="input-group input-group-lg">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" class="form-control" name="Password" placeholder="Repeat" required />
            </div>

            <div class="input-group input-group-md">
                <span class="form-control wrong">Either User Name is incorrect or Passwords do not match</span>
            </div>

            <button type="submit" class="float">Change Password</button>
        </form>
    </div>
</div>
</body>
</html>
