package Service;

import Models.*;

import java.util.List;

public interface Service_Interface {
    //Joke Class methods
    List<Joke> getAllUserJokes(Integer userID);
    Joke getRandomJoke(List<Integer> categoryIDs);
    String addJoke(Joke joke);
    String updateJoke(Joke joke);
    String deleteJoke(Integer jokeId);
    Joke getJoke(Integer jokeID);

    //User Class methods
    String addUser(User user);
    User login(User user);

    //Category Class methods
    List<Category> getAllCategories();
    String addCategory(Category category);
}
