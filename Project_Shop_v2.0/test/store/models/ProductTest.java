package store.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;
    private Category category;
    private LocalDate expirationDate;

    @BeforeEach
    void setUp() {
        category = Category.FOOD;
        expirationDate = LocalDate.now().plusDays(15);
        product = new Product(1, "Milk", 1.50, category, expirationDate, 100);
    }
    @Test
    void calculateSellPrice_ExpirationInLessThan5Days() {
        product = new Product(1, "Milk", 1, Category.FOOD, LocalDate.now().plusDays(4), 10);
        double expectedSellPrice = 1 * Category.FOOD.getMarkUp() * 0.90;
        assertEquals(expectedSellPrice, product.calculateSellPrice(1), 0.01);
    }

    @Test
    void calculateSellPrice_ExpirationIn5To10Days() {
        product = new Product(1, "Bread", 1, Category.FOOD, LocalDate.now().plusDays(7), 10);
        double expectedSellPrice = 1 * Category.FOOD.getMarkUp() * 0.95;
        assertEquals(expectedSellPrice, product.calculateSellPrice(1), 0.01);
    }

    @Test
    void calculateSellPrice_ExpirationInMoreThan10Days() {
        product = new Product(1, "Butter", 1, Category.FOOD, LocalDate.now().plusDays(15), 10);
        double expectedSellPrice = 1 * Category.FOOD.getMarkUp();
        assertEquals(expectedSellPrice, product.calculateSellPrice(1), 0.01);
    }

    @Test
    void calculateSellPrice() {
        assertEquals(1.2, product.calculateSellPrice(1));
    }

    @Test
    void getId() {
        assertEquals(1, product.getId());
    }

    @Test
    void getName() {
        assertEquals("Milk", product.getName());
    }

    @Test
    void getBuyPrice() {
        assertEquals(1.50, product.getBuyPrice());
    }

    @Test
    void getCategory() {
        assertEquals(category, product.getCategory());
    }

    @Test
    void getExpirationDate() {
        assertEquals(expirationDate, product.getExpirationDate());
    }

    @Test
    void getSellPrice() {
        assertEquals(1.50*1.20, product.getSellPrice());
    }

    @Test
    void getQuantity() {
        assertEquals(100, product.getQuantity());
    }

    @Test
    void setId() {
        product.setId(2);
        assertEquals(2, product.getId());
    }

    @Test
    void setName() {
        product.setName("Bread");
        assertEquals("Bread", product.getName());
    }

    @Test
    void setBuyPrice() {
        product.setBuyPrice(2.00);
        assertEquals(2.00, product.getBuyPrice());
    }

    @Test
    void setCategory() {
        product.setCategory(Category.NON_FOOD);
        assertEquals(Category.NON_FOOD, product.getCategory());
    }

    @Test
    void setExpirationDate() {
        LocalDate newDate = LocalDate.now().plusDays(20);
        product.setExpirationDate(newDate);
        assertEquals(newDate, product.getExpirationDate());
    }

    @Test
    void setSellPrice() {
        product.setSellPrice(2.00);
        assertEquals(2.00, product.getSellPrice());
    }

    @Test
    void setQuantity() {
        product.setQuantity(50);
        assertEquals(50, product.getQuantity());
    }

    @Test
    void isValid() {
        assertTrue(product.isValid());
    }

    @Test
    void addQuantity() {
        product.addQuantity(50);
        assertEquals(150, product.getQuantity());
    }

    @Test
    void getSellPriceAsString() {
        assertEquals("1.80", product.getSellPriceAsString(product.getSellPrice()));
    }

    @Test
    void getProductInfo() {
        assertEquals("id = 1 - Milk, price: 1.80, quantity: 100", product.getProductInfo());
    }

    @Test
    void getProductInfoCustomer() {
        assertEquals("Milk, price: 1.80 x 100 - total: 180.00", product.getProductInfoCustomer());
    }

    @Test
    void purchaseProduct() {
        product.purchaseProduct(10);
        assertEquals(90, product.getQuantity());
    }

    @Test
    void checkQuantityAndValidity() {
        assertTrue(product.checkQuantityAndValidity(10));
    }

    @Test
    void testToString() {
        assertEquals("id = 1 - Milk, price: 1.80, quantity: 100", product.toString());
    }
}