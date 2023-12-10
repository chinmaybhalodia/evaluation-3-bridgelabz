package com.example;

import com.validator.*;
import com.exceptions.*;

import java.util.HashMap;
import java.util.Scanner;

public class UserRegistration {
    public void addUser(Scanner inputReader, HashMap<String, User> users) {
        System.out.print("Enter username: ");
        String username;
        while (true) {
            username = inputReader.nextLine();
            try {
                Validator.validateUsername(username);
                break;
            } catch (InvalidUsernameException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter username: ");
            }
        }

        System.out.print("Enter email: ");
        String email;
        while (true) {
            email = inputReader.nextLine();
            try {
                if (users.containsKey(email)) {
                    throw new InvalidEmailException("User with this email already exists.");
                } else {
                    Validator.validateEmail(email);
                }
                break;
            } catch (InvalidEmailException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter email: ");
            }
        }

        System.out.print("Enter phone number: ");
        String phone;
        while (true) {
            phone = inputReader.nextLine();
            try {
                Validator.validatePhoneNumber(phone);
                break;
            } catch (InvalidPhoneNumberException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter phone number: ");
            }
        }

        System.out.print("Enter password: ");
        String password;
        while (true) {
            password = inputReader.nextLine();
            try {
                Validator.validatePassword(password);
                break;
            } catch (InvalidPasswordException exception) {
                System.out.println(exception.getMessage());
                System.out.print("Enter password: ");
            }
        }

        User user = new User(username, email, phone, password);
        users.put(email, user);
        System.out.println("User " + username + " added successfully!");
    }
}
