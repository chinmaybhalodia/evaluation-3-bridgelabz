package com.validator;

import java.util.HashMap;

import com.example.User;
import com.exceptions.*;

public class Validator {
    public static boolean validateName(String name) throws InvalidNameException {
        String regex = "^[A-Z]{1}[a-zA-z]+$";
        if (name.matches(regex)) {
            return true;
        } else {
            throw new InvalidNameException("Invalid input name for user");
        }
    }

    public static boolean validateUsername(String username)
            throws InvalidUsernameException {
        String regex = "^[a-z0-9_-]+$";
        if (username.matches(regex)) {
            return true;
        } else {
            throw new InvalidUsernameException("Invalid username for user.");
        }
    }

    public static boolean validateUsernameAdd(String username, HashMap<String, User> users)
            throws InvalidUsernameException {
        String regex = "^[a-z0-9_-]+$";
        if (username.matches(regex) && !users.containsKey(username)) {
            return true;
        } else {
            throw new InvalidUsernameException("Invalid username for user.");
        }
    }

    public static boolean validateUsernamePost(String username, HashMap<String, User> users)
            throws InvalidUsernameException {
        String regex = "^[a-z0-9_-]+$";
        if (username.matches(regex) && users.containsKey(username)) {
            return true;
        } else {
            throw new InvalidUsernameException("Invalid username for user.");
        }
    }

    public static boolean validatePhoneNumber(String phone) throws InvalidPhoneNumberException {
        String regex = "^[0-9]{2,4}\\s[0-9]{10}$";
        if (phone.matches(regex)) {
            return true;
        } else {
            throw new InvalidPhoneNumberException("Invalid phone number for user.");
        }
    }

    public static boolean validatePostText(String postText) throws InvalidPostException {
        String regex = "^[a-zA-Z0-9]+[a-zA-Z0-9\\s\\.!#\\$%@&\\*()]*$";
        if (postText.matches(regex)) {
            return true;
        } else {
            throw new InvalidPostException("Invalid input: post text must not be empty.");
        }
    }
}
