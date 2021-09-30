<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 29-04-2021
  Time: 09:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.nagarro.queries.Queries, com.nagarro.entity.users.Users" %>
<html>
<head>
    <title>Successful</title>
</head>
<body>

<% String newPass = request.getParameter("NewPassword");
   String repeat = request.getParameter("Password");
   String userName = request.getParameter("UserName");

   // If userName is not correct
   try {
       Queries.checkUser(userName);
   }
   catch (Exception e) {
       response.sendRedirect("forgotError.jsp");
   }

   // If passwords do not match
   if (!newPass.equals(repeat)) {
       response.sendRedirect("forgotError.jsp");
   }

   else {
       Queries.changePassword(userName, newPass);
       response.setContentType("text/html");
       out.write("<h1>Password Changed Successfully !!</h1><br><br>");
       out.write("<a href='index.jsp'>Go back to Sign In</a>");
   }%>

</body>
</html>
