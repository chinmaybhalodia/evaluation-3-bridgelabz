import com.example.PostAnalyzer;
import com.validator.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PostTest {
    // test case for checking if post is being posted or not
    @ParameterizedTest
    @ValueSource(strings = {
            "Chinmay\nchinmaybhalodia\n91 9313402393\nchinmaybhalodia\nHello my name is chinmay.",
            "Dummy\ndummy_user\n91 9313402393\ndummy_user\nI am very happy today!",
            "John\njohn_doe_123\n91 9456234756\njohn_doe_123\nFeeling low as I had a very bad day."
    })
    // name, username and phone number to add a user
    // username and post text for creating new user post
    public void testPostAdd(String str) {
        String[] userData = str.split("\n");
        PostAnalyzer postAnalyzer = new PostAnalyzer();

        ByteArrayInputStream testIn = new ByteArrayInputStream(str.getBytes());
        Scanner inputReader = new Scanner(testIn); // giving test strings as input stream

        postAnalyzer.addUser(inputReader); // adding the user first using test data
        int user_posts_before = postAnalyzer.posts.get(userData[3]).size();

        postAnalyzer.newPost(inputReader); // creating new post for the newly added user using test data
        int user_posts_after = postAnalyzer.posts.get(userData[3]).size();

        assertEquals(user_posts_before + 1, user_posts_after);
    }

    // sad tests for post
    @ParameterizedTest
    @ValueSource(strings = { " ", "        ", "" })
    public void testPostSad(String str) {
        try {
            Validator.validatePostText(str);
            fail("Exception expected");
        } catch (Exception exception) {
            assertEquals("Invalid input: post text must not be empty.", exception.getMessage());
        }
    }

    // happy tests for post
    @ParameterizedTest
    @ValueSource(strings = { "My Name is Chinmay.", "Hello! # $ % @ & * ( )", "Welcome" })
    public void testPostHappy(String str) {
        try {
            assertTrue(Validator.validatePostText(str));
        } catch (Exception exception) {
            fail("Exception not expected");
        }
    }
}
