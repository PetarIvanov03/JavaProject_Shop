package store.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Checkout checkout;
    private Cashier cashier;

    @BeforeEach
    void setUp() {
        cashier = new Cashier(1, "John Doe", 3000.0);
        checkout = new Checkout(1);
    }

    @Test
    void getId() {
        assertEquals(1, checkout.getId());
    }

    @Test
    void getCashier() {
        checkout.setCashier(cashier);
        assertEquals(cashier, checkout.getCashier());
    }

    @Test
    void setId() {
        checkout.setId(2);
        assertEquals(2, checkout.getId());
    }

    @Test
    void setCashier() {
        checkout.setCashier(cashier);
        assertEquals(cashier, checkout.getCashier());
    }

    @Test
    void isAvailable() {
        assertFalse(checkout.isAvailable(), "Checkout should not be available without a cashier");
        checkout.setCashier(cashier);
        assertTrue(checkout.isAvailable(), "Checkout should be available with a cashier");
    }

    @Test
    void testToString() {
        assertEquals("Checkout 1 with no cashier", checkout.toString(), "toString() should indicate no cashier");
        checkout.setCashier(cashier);
        assertEquals("Checkout 1 with cashier: John Doe", checkout.toString(), "toString() should indicate cashier name");
    }
}