<%-- 
    Document   : UpdateJoke
    Created on : 22 May 2023, 14:07:12
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
        <title>Update Joke</title>
    </head>
    <body>
        <h1>Edit The Joke Details Below:</h1>
        <%
            String message = (String) request.getAttribute("message");
            List<Category> categories = (List<Category>) request.getSession(false).getAttribute("categories");
            Joke joke = (Joke) request.getAttribute("joke");
        %>
        <h1><%=message%></h1>
        <form action="ClientController" method="get">
            <p><label for="JokeDescription">Joke Description:</label></p> 
            <textarea class="inputTextArea" name="JokeDescription" rows="4" cols="50" required><%=joke.getJoke()%></textarea>
            <br>
            <p style="text-align: center;"><label style="text-align: center;" class="jokeCategories" for="categories">Joke Categories:</label></p>
            <br>
            <div class="scroll" id="Categories">
            <%
                List<Integer> jokeCategories = joke.getCategoryIDs();
                for(Category category:categories) {
            %>
            <label class="container" for="<%=category.getCategoryID()%>">
                <%=category.getName()%>
            <%
                    if(jokeCategories.contains(category.getCategoryID())) {
            %>
                <input type="checkbox" id="<%=category.getCategoryID()%>" name="<%=category.getCategoryID()%>" value="<%=category.getCategoryID()%>"  checked/>
            <%      } else {%>
                <input type="checkbox" id="<%=category.getCategoryID()%>" name="<%=category.getCategoryID()%>" value="<%=category.getCategoryID()%>"/>
            <%      }%>
                <span class="checkmark"></span>
            </label>
              <%}%>
            </div>
            <br>
            <input type="hidden" name="jokeId" value="<%=joke.getJokeID()%>"/>
            <p style="text-align: center;"><input type="submit" name="submit" value="UpdateJoke" ></p>
        </form>
            <br>
        <p style="text-align: center;"><a href="ClientController?submit=menu">Back to Menu</a></p>
    </body>
</html>
