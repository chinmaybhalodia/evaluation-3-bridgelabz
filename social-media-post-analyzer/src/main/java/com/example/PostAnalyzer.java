package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import com.exceptions.*;
import com.validator.*;

public class PostAnalyzer {
    public HashMap<String, ArrayList<Post>> posts = new HashMap<>();
    public HashMap<String, User> users = new HashMap<>();

    public void addUser(Scanner inputReader) {
        System.out.print("Enter name of the user: ");
        String name;
        while (true) {
            name = inputReader.nextLine();
            try {
                Validator.validateName(name);
                break;
            } catch (InvalidNameException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter name of the user: ");
            }
        }

        System.out.print("Enter username for the user: ");
        String username;
        while (true) {
            username = inputReader.nextLine();
            try {
                Validator.validateUsernameAdd(username, users);
                break;
            } catch (InvalidUsernameException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter username for the user: ");
            }
        }

        System.out.print("Enter user phone number: ");
        String phone;
        while (true) {
            phone = inputReader.nextLine();
            try {
                Validator.validatePhoneNumber(phone);
                break;
            } catch (InvalidPhoneNumberException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter user phone number: ");
            }
        }

        User user = new User(username, name, phone);
        users.put(username, user);
        posts.put(username, new ArrayList<>());
        System.out.println("Added user successfully.");
    }

    public Sentiment newPost(Scanner inputReader) {
        System.out.print("Enter username: ");
        String username;
        while (true) {
            username = inputReader.nextLine();
            try {
                Validator.validateUsernamePost(username, users);
                break;
            } catch (InvalidUsernameException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter username: ");
            }
        }

        System.out.print("Enter post text: ");
        String postText;
        while (true) {
            postText = inputReader.nextLine();
            try {
                Validator.validatePostText(postText);
                break;
            } catch (InvalidPostException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter post text: ");
            }
        }

        ArrayList<Post> userposts = posts.get(username);
        Post post = new Post(users.get(username), postText);
        userposts.add(post);
        posts.put(username, userposts);
        System.out.println("Created post successfully.");

        SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();
        return sentimentAnalyzer.analyzeSentiment(postText);
    }
}
