package com.example;

import java.util.HashMap;
import java.util.Scanner;

public class BookStore {
    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, Book> books = new HashMap<>();
    private HashMap<String, Integer> stock = new HashMap<>();

    UserRegistration userRegistration = new UserRegistration();
    BookListing bookListing = new BookListing();
    OrderProcessing orderProcessing = new OrderProcessing();

    public void addUser(Scanner inputReader) {
        userRegistration.addUser(inputReader, users);
    }

    public void addBook(Scanner inputReader) {
        bookListing.addBook(inputReader, stock, books);
    }

    public void addToStock(Scanner inputReader) {
        bookListing.addToStock(inputReader, stock);
    }

    public void purchaseBook(Scanner inputReader) {
        orderProcessing.processOrder(inputReader, stock);
    }

    public void printStock() {
        System.out.println(stock.toString());
    }
}
