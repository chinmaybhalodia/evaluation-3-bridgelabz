package com.example;

public class Book {
    private String title;
    private String author;
    private String ISBN;
    private int price;

    public Book(String title, String author, String ISBN, int price) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.price = price;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public int getPrice() {
        return this.price;
    }
}