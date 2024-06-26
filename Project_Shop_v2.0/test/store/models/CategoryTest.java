package store.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getMarkUp() {
        // Test for FOOD category
        assertEquals(1.20, Category.FOOD.getMarkUp(), 0.01);

        // Test for NON_FOOD category
        assertEquals(1.10, Category.NON_FOOD.getMarkUp(), 0.01);
    }

    @Test
    void values() {
        // Test the values() method
        Category[] expectedValues = { Category.FOOD, Category.NON_FOOD };
        assertArrayEquals(expectedValues, Category.values());
    }

    @Test
    void valueOf() {
        // Test the valueOf() method
        assertEquals(Category.FOOD, Category.valueOf("FOOD"));
        assertEquals(Category.NON_FOOD, Category.valueOf("NON_FOOD"));
    }
}