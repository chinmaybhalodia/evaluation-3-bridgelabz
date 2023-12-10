package com.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);
        PostAnalyzer postAnalyzer = new PostAnalyzer();

        System.out.println("Adding new user.");
        postAnalyzer.addUser(inputReader);

        System.out.println("\nCreating new post.");
        Sentiment sen1 = postAnalyzer.newPost(inputReader);
        System.out.println("Posted successfully. Post sentiment was: " + sen1);

        System.out.println("\nCreating new post.");
        Sentiment sen2 = postAnalyzer.newPost(inputReader);
        System.out.println("Posted successfully. Post sentiment was: " + sen2);

        System.out.println("\nCreating new post.");
        Sentiment sen3 = postAnalyzer.newPost(inputReader);
        System.out.println("Posted successfully. Post sentiment was: " + sen3);
    }
}