<%-- 
    Document   : Register
    Created on : 23 May 2023, 09:39:18
    Author     : jarro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Register</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");
            if(message!=null) {
        %>
        <h3><%=message%></h3>
        <%  }%>
        <h1>Please enter your details below to register:</h1>
        <label for="register">Enter details:</label>
        <form action="ClientController" id="register"  method="post">
            <label for="Name">Name</label><br>
            <input id="Name" type="text" name="name" required><br>
            <label for="Surname">Surname</label><br>
            <input id="Surname" type="text" name="surname" required><br>
            <label for="Email">Email</label><br>
            <input id="Email" type="text" name="email" required><br>
            <label for="Password">Password</label><br>
            <input id="Password" type="password" name="password" required><br>
            <input type="submit" name="submit" value="register" required>
        </form>
        <br>
        <br>
        <p><a href="index.jsp">Back to Login</a></p>
    </body>
</html>
