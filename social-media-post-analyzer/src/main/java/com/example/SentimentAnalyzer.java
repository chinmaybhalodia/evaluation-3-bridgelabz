package com.example;

public class SentimentAnalyzer {
    public Sentiment analyzeSentiment(String postText) {
        postText = postText.toLowerCase();
        if (postText.contains("happy") || postText.contains("good") || postText.contains("nice")
                || postText.contains("great") || postText.contains("positive")) {
            return Sentiment.POSITIVE;
        } else if (postText.contains("sad") || postText.contains("bad") || postText.contains("worse")
                || postText.contains("negative")) {
            return Sentiment.NEGATIVE;
        } else {
            return Sentiment.NEUTRAL;
        }
    }
}
