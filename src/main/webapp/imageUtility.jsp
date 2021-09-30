<%--
  Created by IntelliJ IDEA.
  User: vaibhavjain03
  Date: 28-04-2021
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.nagarro.queries.Queries,
  java.util.List, com.nagarro.entity.images.Images, com.nagarro.servlet.Servlet"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Image Management Utility</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <style><%@include file="/css/image.css"%></style>
</head>
<body>
<% int id = Servlet.getID();
   List<Images> images = Queries.getImages(id);
   int i=1; %>
<h3>Image Management Utility</h3>
<p>Please select an image file to upload(Max Size 1 MB)</p>
<form id="myForm" method="post" action="home"
       enctype="multipart/form-data">
    <input type="text" name="imgName" required />
    <input type="file" name="file" accept="image/*" required />
    <input type="submit" value="Submit" class="btn1" />
    <input type="button" onclick="myFunction()" value="Cancel" class="btn2" />
</form>
<br>
<br>
<h4>Uploaded Images</h4>
<table cellspacing="0" border="1">
    <thead>
    <tr>
        <th class="serial">S.No.</th>
        <th>Name</th>
        <th>Size</th>
        <th class="preview">Preview</th>
        <th>Action</th>
    </tr>
    </thead>

    <tbody>
    <% for (Images image : images) {
    %><tr>
        <td><%=i%></td>
        <td><%=image.getName()%></td>
        <td><%=image.getSize()%> KB</td>
        <td><img class="img" src="<%="/images/" + image.getFileName()%>" alt="alt" /> </td>
        <div>
            <td>
                <a href="edit.jsp?id=<%=String.valueOf(image.getId())%>&name=<%=image.getName()%>"><img class="action" src="/images/edit.png" alt="edit"></a>
                <a href="cancel.jsp?imgID=<%=String.valueOf(image.getId())%>"><img class="action cancel" src="/images/cancel.png" alt="cancel"></a>
            </td>
        </div>

    </tr>
    <% i++; } %>
    </tbody>
</table>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</html>
