<%-- 
    Document   : DeleteJoke
    Created on : 22 May 2023, 14:07:01
    Author     : jarro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Delete Joke</title>
    </head>
    <body>
        <h1>Select A Joke To Delete Below:</h1>
        <div class="joke">
        <%
            String message = (String) request.getAttribute("message");
            if(message==null) {
                message = "";
            }
        %>
        <p><%=message%></p>
        <%
            List<Joke> userJokes = (List<Joke>) request.getSession(false).getAttribute("jokes");
            if(!userJokes.isEmpty()) {
                for(Joke joke:userJokes) {
        %>
        <p>
                    <label class="jokeLabel" for="JokeDescription">Joke Description:</label><br>
                    <textarea class="selectionTextArea" name="JokeDescription" rows="4" cols="50" readonly><%=joke.getJoke()%></textarea><br><br>
                    <a href="ClientController?submit=DeleteJoke&jokeId=<%=joke.getJokeID()%>">Delete</a><br><br>
        </p>
        <%
                }
        %>
        </div>
        <%
            }  else {
        %>
        <p>You have not added any jokes to the server yet.</p>
        <%  }%>
        <br>
        <br>
        <p style="text-align: center"><a href="ClientController?submit=menu">Back to Menu</a></p>
    </body>
</html>
