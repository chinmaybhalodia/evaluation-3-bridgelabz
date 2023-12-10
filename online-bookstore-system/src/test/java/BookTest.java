import com.validator.*;
import com.example.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BookTest {

    // test case to check if book stock has been added or not
    @ParameterizedTest
    @ValueSource(strings = {
            "Asura\nAnand Neelakantan\n123-21-45612-56-7\n350\n100\n123-21-45612-56-7\n150\n",
            "Hamlet\nShakespeare\n423-89-51378-63-2\n500\n50\n423-89-51378-63-2\n50\n",
            "Sita\nAmish Tripathi\n128-45-67841-36-0\n250\n75\n128-45-67841-36-0\n75\n"
    })
    // Book title, author, ISBN and price separated by \n for adding new book
    // Book ISBN and add count separated by \n to add books to stock
    public void testBookStockAdd(String str) {
        String[] bookData = str.split("\n");
        BookStore bookStore = new BookStore();

        ByteArrayInputStream testIn = new ByteArrayInputStream(str.getBytes());
        Scanner inputReader = new Scanner(testIn); // giving test strings as input stream

        bookStore.addBook(inputReader); // adding new books first

        int book_stock_before = bookStore.getStockByISBN(bookData[2]);
        int change = Integer.parseInt(bookData[6]);

        bookStore.addToStock(inputReader); // adding books to stock

        int book_stock_after = bookStore.getStockByISBN(bookData[2]);
        assertEquals(book_stock_before + change, book_stock_after);
    }

    // test case to check if book has been added or not
    @ParameterizedTest
    @ValueSource(strings = {
            "Asura\nAnand Neelakantan\n123-21-45612-56-7\n350\n\n100\n",
            "Hamlet\nShakespeare\n423-89-51378-63-2\n500\n\n50\n",
            "Sita\nAmish Tripathi\n128-45-67841-36-0\n250\n\n75\n"
    })
    // Book title, author, ISBN and price separated by \n for adding new book
    public void testBookAdd(String str) {
        String[] bookData = str.split("\n");
        BookStore bookStore = new BookStore();
        int books_before = bookStore.getTotalBooks();
        int book_stock_before = bookStore.getStockByISBN(bookData[2]);
        int change = Integer.parseInt(bookData[5]);

        ByteArrayInputStream testIn = new ByteArrayInputStream(str.getBytes());
        Scanner inputReader = new Scanner(testIn); // giving test strings as input stream

        bookStore.addBook(inputReader);

        int books_after = bookStore.getTotalBooks();
        int book_stock_after = bookStore.getStockByISBN(bookData[2]);

        assertEquals(books_before + 1, books_after); // checking if book has been added or not
        assertEquals(book_stock_before + change, book_stock_after); // checking if stock has been updated or not
    }

    // sad test cases for book title and author names
    @ParameterizedTest
    @ValueSource(strings = { "", " ", "      " })
    public void testNameSad(String str) {
        try {
            Validator.validateName(str);
            fail("Exception Expected");
        } catch (Exception exception) {
            assertEquals("Name is Invalid", exception.getMessage());
        }
    }

    // happy test cases for book title and author names
    @ParameterizedTest
    @ValueSource(strings = { "Asura", "Shakespeare", "Ikigai", "Amish Tripathi" })
    public void testNameHappy(String str) {
        try {
            assertTrue(Validator.validateName(str));
        } catch (Exception exception) {
            fail("Exception not expected");
        }
    }

    // sad test cases for ISBN
    @ParameterizedTest
    @ValueSource(strings = { "123-456-789", "123-12-51234-21", "123 21 54874 32 1", "", "12-34-56842-12-0",
            "123-12-54874-132" })
    public void testISBNSad(String str) {
        try {
            Validator.validateISBN(str);
            fail("Exception Expected");
        } catch (Exception exception) {
            assertEquals("ISBN is Invalid", exception.getMessage());
        }
    }

    // happy test cases for ISBN
    @ParameterizedTest
    @ValueSource(strings = { "123-21-45612-56-7", "423-89-51378-63-2", "128-45-67841-36-0" })
    public void testISBNHappy(String str) {
        try {
            assertTrue(Validator.validateISBN(str));
        } catch (Exception exception) {
            fail("Exception not Expected");
        }
    }
}
