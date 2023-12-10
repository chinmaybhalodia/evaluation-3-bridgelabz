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

    // test case to check if a user has been added or not
    @ParameterizedTest
    @ValueSource(strings = {
            "chinmay01\nchinmay@gmail.com\n91 9313402393\nChinmay-123",
            "dummyuser\ndummy@gmail.com\n91 4531456985\nPassword@01",
            "helloworld123\nhello.world@gmail.com\n91 9456235647\nhelloWorld_7"
    })
    public void testUserAdd(String str) {
        BookStore bookStore = new BookStore();
        int users_before = bookStore.getTotalUsers();

        ByteArrayInputStream testIn = new ByteArrayInputStream(str.getBytes());
        Scanner inputReader = new Scanner(testIn); // giving test strings as input stream

        bookStore.addUser(inputReader);

        int users_after = bookStore.getTotalUsers();
        assertEquals(users_before + 1, users_after);
    }

    // sad test cases for username
    @ParameterizedTest
    @ValueSource(strings = { "chinmay bhalodia", "Chinmay123", " ", "chinmay.bhalodia" })
    public void testUsernameSad(String str) {
        try {
            Validator.validateUsername(str);
            fail("Exception Expected");
        } catch (Exception exception) {
            assertEquals("Username is Invalid", exception.getMessage());
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

    // sad tests for email
    @ParameterizedTest
    @ValueSource(strings = { "chinmay@.com", "chinmay@gmail", " ", "chinmay", "@gmail.com" })
    public void testEmailSad(String str) {
        try {
            Validator.validateEmail(str);
            fail("Exception expected");
        } catch (Exception exception) {
            assertEquals("Email is Invalid", exception.getMessage());
        }
    }

    // happy tests for email
    @ParameterizedTest
    @ValueSource(strings = { "chinmay@gmail.com", "chinmay.xyz@gmail.com", "chinmay@xyz.gmail.com" })
    public void testEmailHappy(String str) {
        try {
            assertTrue(Validator.validateEmail(str));
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
            assertEquals("Phone number is Invalid", exception.getMessage());
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

    // sad test cases for password
    @ParameterizedTest
    @ValueSource(strings = { "chinmay", "1234", " ", "Chinmay123", "Chinmay_pwd", "Chinmay" })
    public void testPasswordSad(String str) {
        try {
            Validator.validatePassword(str);
            fail("Exception Expected");
        } catch (Exception exception) {
            assertEquals("Password is Invalid", exception.getMessage());
        }
    }

    // happy test cases for password
    @ParameterizedTest
    @ValueSource(strings = { "Chinmay-123", "Hello_World_001", "Password@123" })
    public void testPasswordHappy(String str) {
        try {
            assertTrue(Validator.validatePassword(str));
        } catch (Exception exception) {
            fail("Exception not Expected");
        }
    }
}
