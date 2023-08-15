<%-- 
    Document   : Menu
    Created on : 22 May 2023, 13:28:57
    Author     : jarro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Menu</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");
            User user = (User) request.getSession().getAttribute("user");
            if(message!=null && user!=null) {
        %>
        <p><%=message%></p>
        <p>Your Details:<br><%=user.getName()+" "+user.getSurname()%></p>
        <h2>Select An Option Below:</h2>
        <a href="ClientController?submit=SignOut">Sign Out</a></button>
        <a href="ViewJoke.jsp">View a Joke</a>
        <a href="AddJoke.jsp">Add a Joke</a>
        <a href="DeleteJoke.jsp">Delete a Joke</a>
        <a href="SelectJoke.jsp">Update a Joke</a>
        <%if(user.getType().equals("admin")) {%>
                <a href="AddCategory.jsp">Add a Category</a>
            <%}%>
        <%  }%>
    </body>
</html>
