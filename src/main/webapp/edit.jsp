<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 29-04-2021
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <style><%@include file="/css/image.css"%></style>
</head>
<body>
<% int id = Integer.parseInt(request.getParameter("id")); %>
<p>Please select an image file to upload(Max Size 1 MB)</p>
<form id="myForm" method="post" action="upload"
      enctype="multipart/form-data">
    <input type="text" name="imgName" value="<%=request.getParameter("name")%>" required />
    <input type="hidden" name="id" value="<%= id %>" />
    <input type="file" name="file" accept="image/*" required />
    <input type="submit" value="Submit" class="btn1" />
    <a href="imageUtility.jsp"><input type="button" value="Cancel" class="btn2" /></a>
</form>

</body>
</html>
