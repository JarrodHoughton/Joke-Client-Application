<%-- 
    Document   : AddCategory
    Created on : 22 May 2023, 14:07:52
    Author     : jarro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Add Category</title>
    </head>
    <body>
        <h1>Add A Category:</h1>
        <%
            String message = (String) request.getAttribute("message");
            if(message!=null) {
        %>
        <h1><%=message%></h1>
        <%  }%>
        <form action="ClientController?submit=AddCategory" method="get">
            <p><label for="categoryName">Please enter the category name:</label></p> 
            <textarea name="categoryName" rows="4" cols="50" required></textarea>
            <br>
            <input type="submit" name="submit" value="AddCategory">
        </form>
        <br>
        <br>
        <p><a href="ClientController?submit=menu">Back to Menu</a></p>
    </body>
</html>
