package com.example;

public class Post {
    User user;
    String postText;

    public Post(User user, String postText) {
        this.user = user;
        this.postText = postText;
    }
}