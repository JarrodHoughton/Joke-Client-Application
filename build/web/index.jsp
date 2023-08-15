<%-- 
    Document   : index
    Created on : 22 May 2023, 11:35:31
    Author     : jarro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Login</title>
    </head>
    <body>
         <h1>Welcome to the Joke Server!</h1>
        <%
            String message = (String) request.getAttribute("message");
            if(message!=null) {
        %>
        <p><%=message%></p>
        <%  }%>
        <h3>Sign In</h3>
        <form action="ClientController" method="post">
            <label for="Email">Email</label><br>
            <input id="Email" type="text" name="email" required><br>
            <label for="Password">Password</label><br>
            <input id="Password" type="password" name="password" required><br>
            <input type="submit" name="submit" value="login">
        </form>
        <br>
        <br>
        <a href="Register.jsp">Register</a>
    </body>
</html>
