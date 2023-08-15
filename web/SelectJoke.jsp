<%-- 
    Document   : SelectJoke
    Created on : 23 May 2023, 01:07:51
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
        <title>Select Joke To Update</title>
    </head>
    <body>
        <h1>Select A Joke To Edit Below:</h1>
        <div class="joke">
        <%
            List<Joke> userJokes = (List<Joke>) request.getSession(false).getAttribute("jokes");
            if(!userJokes.isEmpty()) {
                for(Joke joke:userJokes) {
        %>
                <p>
                    <label class="jokeLabel" for="JokeDescription">Joke Description:</label><br>
                    <textarea class="selectionTextArea" name="JokeDescription" rows="4" cols="50" readonly><%=joke.getJoke()%></textarea><br><br>
                    <a href="ClientController?submit=SelectedJokeToUpdate&jokeId=<%=joke.getJokeID()%>">Select</a><br><br>
                </p>
        <%  }%>
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
