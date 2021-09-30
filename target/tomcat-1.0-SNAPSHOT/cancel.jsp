<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 29-04-2021
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="com.nagarro.queries.Queries" language="java" %>
<html>
<head>
    <title>Remove Image</title>
</head>
<body>

<% int id = Integer.parseInt(request.getParameter("imgID"));
    Queries.deleteImage(id);
    response.sendRedirect("imageUtility.jsp"); %>

</body>
</html>
