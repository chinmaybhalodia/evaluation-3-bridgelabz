import com.example.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderTest {
    // testing of purchase order has been successful or not
    @ParameterizedTest
    @ValueSource(strings = {
            "Asura\nAnand Neelakantan\n123-21-45612-56-7\n350\n100\n123-21-45612-56-7\n50\n",
            "Hamlet\nShakespeare\n423-89-51378-63-2\n500\n50\n423-89-51378-63-2\n50\n",
            "Sita\nAmish Tripathi\n128-45-67841-36-0\n250\n75\n128-45-67841-36-0\n50\n"
    })
    // Book title, author, ISBN and price separated by \n for adding new book
    // Book ISBN and purchase count separated by \n to purchase number of books
    public void testPurchase(String str) {
        String[] bookData = str.split("\n");
        BookStore bookStore = new BookStore();

        ByteArrayInputStream testIn = new ByteArrayInputStream(str.getBytes());
        Scanner inputReader = new Scanner(testIn); // giving test strings as input stream

        bookStore.addBook(inputReader); // adding new books first

        int book_stock_before = bookStore.getStockByISBN(bookData[2]);
        int purchased = Integer.parseInt(bookData[6]);

        bookStore.purchaseBook(inputReader); // purchasing books by ISBN number

        int book_stock_after = bookStore.getStockByISBN(bookData[2]);
        assertEquals(book_stock_before - purchased, book_stock_after);
    }
}
