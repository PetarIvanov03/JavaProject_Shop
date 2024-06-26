package store.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {

    private Receipt receipt;
    private Cashier cashier;
    private List<Product> products;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        cashier = new Cashier(1, "John Doe", 2000);
        product1 = new Product(1, "Milk", 1.50, Category.FOOD, LocalDate.now().plusDays(10), 2);
        product2 = new Product(2, "Bread", 1.00, Category.FOOD, LocalDate.now().plusDays(5), 3);
        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        receipt = new Receipt(1, cashier, products);
    }

    @Test
    void printReceipt() {
        // Пренасочваме стандартния изход
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        receipt.printReceipt();

        String expectedOutput = "Receipt #1\r\n" +
                "Cashier: John Doe\r\n" +
                "Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\r\n" +
                "Milk x 2 = 3.42\r\n" +
                "Bread x 3 = 3.24\r\n" +
                "Total: 6.66\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void saveReceiptToFile() throws IOException {
        receipt.saveReceiptToFile();

        File file = new File("receipt_1.txt");
        assertTrue(file.exists());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        assertEquals("Receipt #1", reader.readLine());
        assertEquals("Cashier: John Doe", reader.readLine());
        assertEquals("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), reader.readLine());
        assertEquals("Milk x 2 = 3.42", reader.readLine());
        assertEquals("Bread x 3 = 3.24", reader.readLine());
        assertEquals("Total: 6.66", reader.readLine());
        reader.close();

        file.delete();
    }

    @Test
    void readReceiptFromFile() throws IOException {
        receipt.saveReceiptToFile();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Receipt.readReceiptFromFile("receipt_1.txt");

        String expectedOutput = "Receipt #1\r\n" +
                "Cashier: John Doe\r\n" +
                "Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\r\n" +
                "Milk x 2 = 3.42\r\n" +
                "Bread x 3 = 3.24\r\n" +
                "Total: 6.66\r\n";

        assertEquals(expectedOutput, outContent.toString());

        File file = new File("receipt_1.txt");
        file.delete();
    }

    @Test
    void getId() {
        assertEquals(1, receipt.getId());
    }

    @Test
    void getCashier() {
        assertEquals(cashier, receipt.getCashier());
    }

    @Test
    void getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime receiptDateTime = receipt.getDateTime();

        assertTrue(receiptDateTime.isAfter(now.minusSeconds(1)) && receiptDateTime.isBefore(now.plusSeconds(1)));
    }

    @Test
    void getProducts() {
        assertEquals(products, receipt.getProducts());
    }

    @Test
    void getTotalAmount() {
        assertEquals(6.66, receipt.getTotalAmount(), 0.01);
    }

    @Test
    void testToString() {
        String expectedString = "Receipt Number: 1\n" +
                "Cashier: John Doe\n" +
                "Date and Time: " + receipt.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n" +
                "Products:\n" +
                "Milk - Quantity: 2, Price: 1.71, Total: 3.42\n" +
                "Bread - Quantity: 3, Price: 1.08, Total: 3.24\n" +
                "Total Amount: 6.66\n";

        assertEquals(expectedString, receipt.toString());
    }
}