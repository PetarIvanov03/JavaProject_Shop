package store.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {
    private Cashier cashier;

    @BeforeEach
    void setUp() {
        cashier = new Cashier(1, "John Doe", 3000);
    }

    @Test
    void getId() {
        assertEquals(1, cashier.getId());
    }

    @Test
    void getName() {
        assertEquals("John Doe", cashier.getName());
    }

    @Test
    void getSalary() {
        assertEquals(3000, cashier.getSalary());
    }

    @Test
    void setId() {
        cashier.setId(2);
        assertEquals(2, cashier.getId());
    }

    @Test
    void setName() {
        cashier.setName("Jane Doe");
        assertEquals("Jane Doe", cashier.getName());
    }

    @Test
    void setSalary() {
        cashier.setSalary(3500);
        assertEquals(3500, cashier.getSalary());

        cashier.setSalary(-500); // Setting a negative salary should default to 0
        assertEquals(0, cashier.getSalary());
    }

    @Test
    void testToString() {
        String expectedString = "id = 1 - John Doe with salary: 3000.0";
        assertEquals(expectedString, cashier.toString());
    }
}