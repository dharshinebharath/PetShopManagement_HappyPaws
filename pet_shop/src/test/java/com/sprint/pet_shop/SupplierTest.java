package com.sprint.pet_shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Supplier;

public class SupplierTest {
	// 1
    @Test
    void testSupplierName() {
        Supplier s = new Supplier();
        s.setName("ABC");

        assertEquals("ABC", s.getName());
    }

    // 2
    @Test
    void testNull() {
        Supplier s = new Supplier();

        assertNull(s.getEmail());
    }

    // 3
    @Test
    void testNotNull() {
        Supplier s = new Supplier();
        s.setEmail("abc@gmail.com");

        assertNotNull(s.getEmail());
    }

    // 4
    @Test
    void testListSize() {
        List<Supplier> list = new ArrayList<>();
        list.add(new Supplier());

        assertEquals(1, list.size());
    }

    // 5
    @Test
    void testEmptyList() {
        List<Supplier> list = new ArrayList<>();

        assertTrue(list.isEmpty());
    }

    // 6
    @Test
    void testCondition() {
        int stock = 20;

        assertTrue(stock > 10);
    }

    // 7
    @Test
    void testFalseCondition() {
        int stock = 5;

        assertFalse(stock > 10);
    }

    // 8
    @Test
    void testException() {
        assertThrows(NullPointerException.class,
                () -> {
                    String str = null;
                    str.length();
                });
    }
}
