package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Supplier;

public class SupplierRepoTest {

    // 1 - Positive
    @Test
    void testSupplierName() {
        Supplier s = new Supplier();
        s.setName("ABC");

        assertEquals("ABC", s.getName());
    }

    // 2 - Negative (null check)
    @Test
    void testEmailNull() {
        Supplier s = new Supplier();

        assertNull(s.getEmail());
    }

    // 3 - Positive
    @Test
    void testEmailNotNull() {
        Supplier s = new Supplier();
        s.setEmail("abc@gmail.com");

        assertNotNull(s.getEmail());
    }

    // 4 - Positive condition
    @Test
    void testStockGreaterThanTen() {
        int stock = 15;

        assertTrue(stock > 10);
    }

    // 5 - Negative condition
    @Test
    void testException() {
        assertThrows(NullPointerException.class, () -> {
            String str = null;
            str.length();
        });
    }
}