package Service;

import Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service_Impl implements Service_Interface{
    private Client client;
    private WebTarget webTarget;
    private ObjectMapper mapper;
    private Response response;
    private final String uri = "http://localhost:8080/JokeServerRest/app/jokeserver/";

    public Service_Impl() {
        client = ClientBuilder.newClient();
        mapper = new ObjectMapper();
    }
    
    @Override
    public List<Joke> getAllUserJokes(Integer userID){
        List<Joke> jokes = null;
        String categoriesUri = uri + "getUserJokes/{userId}";
        webTarget = client.target(categoriesUri).resolveTemplate("userId", userID);
        try {
            jokes = mapper.readValue(webTarget.request(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Joke>>(){});
        } catch (IOException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jokes;
    }
    
    @Override
    public Joke getRandomJoke(List<Integer> categoryIDs){
        String getRandomJokeUri = uri + "getRandomJoke";
        webTarget = client.target(getRandomJokeUri);
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(toJsonString(categoryIDs)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(Joke.class);
    }
    
    @Override
    public synchronized String addJoke(Joke joke){
        String addJokeUri = uri + "addJoke";
        webTarget = client.target(addJokeUri);
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(toJsonString(joke)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }
    
    @Override
    public String updateJoke(Joke joke){
        String updateJokeUri = uri + "updateJoke";
        webTarget = client.target(updateJokeUri);
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(toJsonString(joke)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }
    
    @Override
    public String deleteJoke(Integer jokeId){
        String deleteJokeUri = uri + "getJoke/{jokeId}";
        webTarget = client.target(deleteJokeUri).resolveTemplate("jokeId", jokeId);
        response = webTarget.request().get();
        return response.readEntity(String.class);
    }
    
    @Override
    public Joke getJoke(Integer jokeID) {
        String getJokeUri = uri + "getJoke/{jokeId}";
        webTarget = client.target(getJokeUri).resolveTemplate("jokeId", jokeID);
        response = webTarget.request().get();
        return response.readEntity(Joke.class);
    }
    
    @Override
    public synchronized String addUser(User user){
        notifyAll();
        String addUserUri = uri + "addUser";
        webTarget = client.target(addUserUri);
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(toJsonString(user)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }
    
    @Override
    public User login(User user) {
        String loginUri = uri + "login";
        webTarget = client.target(loginUri);
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(toJsonString(user)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(User.class);
    }
    
    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = null;
        String categoriesUri = uri + "getCategories";
        webTarget = client.target(categoriesUri);
        try {
            categories = mapper.readValue(webTarget.request(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Category>>(){});
        } catch (IOException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    
    @Override
    public synchronized String addCategory(Category category){
        String addCategoryUri = uri + "addCategory";
        webTarget = client.target(addCategoryUri);
        try {
            response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.json(toJsonString(category)));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Service_Impl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }
    
    private String toJsonString(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
