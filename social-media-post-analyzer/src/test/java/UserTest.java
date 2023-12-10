import com.validator.*;
import com.example.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserTest {

    // test case for checking if the user is added correctly or not
    @ParameterizedTest
    @ValueSource(strings = {
            "Chinmay\nchinmaybhalodia\n91 9313402393",
            "Hello\nhelloworld\n91 9456723541",
            "John\njohn_doe_123\n91 9456135681"
    }) // Name, username and phone number inputs (separated by \n) for new user
    public void testUserAdd(String str) {
        PostAnalyzer postAnalyzer = new PostAnalyzer();
        int users_size_before = postAnalyzer.users.size();

        ByteArrayInputStream testIn = new ByteArrayInputStream(str.getBytes());
        Scanner inputReader = new Scanner(testIn); // giving test strings as input stream

        postAnalyzer.addUser(inputReader);

        int users_size_after = postAnalyzer.users.size();
        assertEquals(users_size_before + 1, users_size_after);
    }

    // sad tests for name
    @ParameterizedTest
    @ValueSource(strings = { "chinmay", "Chinmay Bhalodia", " ", "1234", "Chinmay_123" })
    public void testNameSad(String str) {
        try {
            Validator.validateName(str);
            fail("Exception expected");
        } catch (Exception exception) {
            assertEquals("Invalid input name for user", exception.getMessage());
        }
    }

    // happy tests for name
    @ParameterizedTest
    @ValueSource(strings = { "Chinmay", "Hello", "Alice", "Bob" })
    public void testNameHappy(String str) {
        try {
            assertTrue(Validator.validateName(str));
        } catch (Exception exception) {
            fail("Exception not expected");
        }
    }

    // sad tests for username
    @ParameterizedTest
    @ValueSource(strings = { "chinmay bhalodia", "Chinmay123", " ", "chinmay.bhalodia" })
    public void testUsernameSad(String str) {
        try {
            Validator.validateUsername(str);
            fail("Exception expected");
        } catch (Exception exception) {
            assertEquals("Invalid username for user.", exception.getMessage());
        }
    }

    // happy tests for username
    @ParameterizedTest
    @ValueSource(strings = { "chinmay", "chinmay123", "chinmay_1", "chinmay-1234" })
    public void testUsernameHappy(String str) {
        try {
            assertTrue(Validator.validateUsername(str));
        } catch (Exception exception) {
            fail("Exception not expected");
        }
    }

    // sad tests for phone
    @ParameterizedTest
    @ValueSource(strings = { "9313402393", "931 314 232", "93134 02393" })
    public void testPhoneNumberSad(String str) {
        try {
            Validator.validatePhoneNumber(str);
            fail("Exception expected");
        } catch (Exception exception) {
            assertEquals("Invalid phone number for user.", exception.getMessage());
        }
    }

    // happy test cases for phone
    @ParameterizedTest
    @ValueSource(strings = { "91 9313402393", "91 9313471075", "91 9845612347" })
    public void testPhoneNumberHappy(String str) {
        try {
            assertTrue(Validator.validatePhoneNumber(str));
        } catch (Exception exception) {
            fail("Exception not expected");
        }
    }
}
