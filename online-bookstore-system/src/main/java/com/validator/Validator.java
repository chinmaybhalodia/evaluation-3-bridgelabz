package com.validator;

import com.exceptions.*;

public class Validator {

    public static boolean validateUsername(String username) throws InvalidUsernameException {
        String usernameRegex = "^[a-z0-9_-]+$";
        if (!username.matches(usernameRegex)) {
            throw new InvalidUsernameException("Username is Invalid");
        }
        return true;
    }

    public static boolean validateEmail(String email) throws InvalidEmailException {
        String emailRegex = "^+[a-zA-Z0-9$&%_+-]+(\\.[a-zA-Z0-9$&%_+-]+)?@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,}){1,2}+$";
        if (!email.matches(emailRegex)) {
            throw new InvalidEmailException("Email is Invalid");
        }
        return true;
    }

    public static boolean validatePhoneNumber(String phone) throws InvalidPhoneNumberException {
        String phoneRegex = "^[0-9]{1,4} [0-9]{5,15}$";
        if (!phone.matches(phoneRegex)) {
            throw new InvalidPhoneNumberException("Phone number is Invalid");
        }
        return true;
    }

    public static boolean validatePassword(String password) throws InvalidPasswordException {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$";
        if (!password.matches(passwordRegex)) {
            throw new InvalidPasswordException("Password is Invalid");
        }
        return true;
    }

    public static boolean validateISBN(String ISBN) throws InvalidISBNException {
        String isbnRegex = "^[0-9]{3}-[0-9]{2}-[0-9]{5}-[0-9]{2}-[0-9]$";
        if (!ISBN.matches(isbnRegex)) {
            throw new InvalidISBNException("ISBN is Invalid");
        }
        return true;
    }

    public static boolean validateName(String name) throws InvalidNameException {
        if (name.isBlank()) {
            throw new InvalidNameException("Name is Invalid");
        }
        return true;
    }
}