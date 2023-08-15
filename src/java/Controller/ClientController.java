/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Models.*;
import Service.Service_Impl;
import Service.Service_Interface;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarro
 */
@WebServlet(name = "ClientController", urlPatterns = {"/ClientController"})
public class ClientController extends HttpServlet {
    private Service_Interface service;
    private int userId = 0;
    private HttpSession session;

    public ClientController() {
         service = new Service_Impl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("submit")) {
            case "register":
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                request.setAttribute("message", service.addUser(new User(name, surname, email, password)));
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "login":
                User user = new User();     
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("password"));
                user = service.login(user);
                if (user != null) {
                    session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("jokes", service.getAllUserJokes(user.getUserID()));
                    session.setAttribute("categories", service.getAllCategories());
                    request.setAttribute("message", "Logged in successfully.");
                    request.getRequestDispatcher("Menu.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Login Failed: Email or Password was incorrect.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            case "SignOut":
                session = null;
                request.setAttribute("message", "");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "ViewJoke":
                String message = "";
                List<Integer> selectedIds = new ArrayList<>();
                for (Category category : service.getAllCategories()) {
                    String catIdStr = request.getParameter(String.valueOf(category.getCategoryID()));
                    if (catIdStr != null) {
                        selectedIds.add(Integer.valueOf(catIdStr));
                    }
                }
                if (selectedIds.isEmpty()) {
                    message = "No categories have been selected yet.";
                } else {
                    Joke randomJoke = service.getRandomJoke(selectedIds);
                    request.setAttribute("joke", randomJoke);
                    if (randomJoke == null) {
                        message = "No joke with selected categories exists.";
                    }
                }
                session.setAttribute("categories", service.getAllCategories());
                request.setAttribute("message", message);
                request.getRequestDispatcher("ViewJoke.jsp").forward(request, response);
                break;
            case "AddJoke":
                message = "";
                List<Integer> categoryIds = new ArrayList<>();
                for (Category category : service.getAllCategories()) {
                    String catIdStr = request.getParameter(String.valueOf(category.getCategoryID()));
                    if (catIdStr != null) {
                        categoryIds.add(Integer.valueOf(catIdStr));
                    }
                }
                String jokeDescription = request.getParameter("JokeDescription");
                if (!jokeDescription.isEmpty()&&!categoryIds.isEmpty()) {
                    message =  service.addJoke(new Joke(jokeDescription, ((User)session.getAttribute("user")).getUserID(), categoryIds));
                } else if (jokeDescription.isEmpty()){
                    message = "Your Joke was empty. Please try again.";
                } else if (categoryIds.isEmpty()) {
                    message = "You have not selected any categories. Please try again.";
                } else {
                    message = "Something went wrong adding your joke.";
                }
                request.setAttribute("message", message);
                session.setAttribute("categories", service.getAllCategories());
                session.setAttribute("jokes", service.getAllUserJokes(((User)session.getAttribute("user")).getUserID()));
                request.getRequestDispatcher("AddJoke.jsp").forward(request, response);
                break;
            case "DeleteJoke":
                int jokeId = Integer.parseInt(request.getParameter("jokeId"));
                request.setAttribute("message", service.deleteJoke(jokeId));
                session.setAttribute("jokes", service.getAllUserJokes(((User)session.getAttribute("user")).getUserID()));
                request.getRequestDispatcher("DeleteJoke.jsp").forward(request, response);
                break;
            case "AddCategory":
                request.setAttribute("message", service.addCategory(new Category(request.getParameter("categoryName"))));
                request.getRequestDispatcher("AddCategory.jsp").forward(request, response);
                break;
            case "UpdateJoke":
                Joke joke = service.getJoke(Integer.parseInt(request.getParameter("jokeId")));
                joke.setJoke(request.getParameter("JokeDescription"));
                List<Integer> categoryIdsToUpdate = new ArrayList<>();
                for (Category category : service.getAllCategories()) {
                    String catIdStr = request.getParameter(String.valueOf(category.getCategoryID()));
                    if (catIdStr != null) {
                        categoryIdsToUpdate.add(Integer.valueOf(catIdStr));
                    }
                }
                message = "";
                if (!joke.getJoke().isEmpty()&&!categoryIdsToUpdate.isEmpty()) {
                    message =  service.updateJoke(joke);
                } else if (joke.getJoke().isEmpty()){
                    message = "Your Joke was empty. Please try again.";
                } else if (categoryIdsToUpdate.isEmpty()) {
                    message = "You have not selected any categories. Please try again.";
                } else {
                    message = "Something went wrong adding your joke.";
                }
                joke.setCategoryIDs(categoryIdsToUpdate);
                request.setAttribute("message", message);
                session.setAttribute("categories", service.getAllCategories());
                session.setAttribute("jokes", service.getAllUserJokes(((User)session.getAttribute("user")).getUserID()));
                request.setAttribute("joke", joke);
                request.getRequestDispatcher("UpdateJoke.jsp").forward(request, response);
                break;
            case "SelectedJokeToUpdate":
                request.setAttribute("message", "");
                session.setAttribute("categories", service.getAllCategories());
                request.setAttribute("joke", service.getJoke(Integer.parseInt(request.getParameter("jokeId"))));
                request.getRequestDispatcher("UpdateJoke.jsp").forward(request, response);
                break;
            case "menu":
                request.setAttribute("message", "");
                request.getRequestDispatcher("Menu.jsp").forward(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

}
