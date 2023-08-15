<%-- 
    Document   : ViewJoke
    Created on : 22 May 2023, 14:01:46
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
        <title>View A Joke</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");
            if(message==null) {
                message = "";
            }
            List<Category> categories = (List<Category>) request.getSession(false).getAttribute("categories");
            Joke joke = (Joke) request.getAttribute("joke");
        %>
        <section>
        <aside class="categories">
        <h1>Please select joke categories:</h1>
        <div class="scroll">
        <form action="ClientController" method="get">
            <%
                for(Category category:categories) {
            %>
            <label class="container" for="<%=category.getCategoryID()%>">
                <%=category.getName()%>
                <input type="checkbox" id="<%=category.getCategoryID()%>" name="<%=category.getCategoryID()%>" value="<%=category.getCategoryID()%>"/>
                <span class="checkmark"></span>
            </label>
                <%}%>
        </div>
        </aside>
        <article class="jokeArticle">
        <input type="submit" name="submit" value="ViewJoke"/>
        </form>
        <%
          if(joke!=null) {
        %>
        <br>
        <label for="Joke">Random joke from selected categories:</label>
        <br>
        <textarea id="Joke" readonly><%=joke.getJoke()%></textarea>
        <%}%>
        <h3><%=message%></h3>
        <br>
        <br>
        <p><a href="ClientController?submit=menu">Back to Menu</a></p>
        </article>
        </section>
    </body>
</html>
