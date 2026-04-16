package com.sprint.pet_shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Employees;

public class EmployeesTest {
	// 1
    @Test
    void testEmployeeObject() {
        Employees e = new Employees();
        e.setFirstName("John");

        assertEquals("John", e.getFirstName());
    }

    // 2
    @Test
    void testNullCheck() {
        Employees e = new Employees();

        assertNull(e.getEmail());
    }

    // 3
    @Test
    void testNotNull() {
        Employees e = new Employees();
        e.setEmail("test@gmail.com");

        assertNotNull(e.getEmail());
    }

    // 4
    @Test
    void testListSize() {
        List<Employees> list = new ArrayList<>();
        list.add(new Employees());

        assertEquals(1, list.size());
    }

    // 5
    @Test
    void testEmptyList() {
        List<Employees> list = new ArrayList<>();

        assertTrue(list.isEmpty());
    }

    // 6
    @Test
    void testBooleanCondition() {
        int salary = 5000;

        assertTrue(salary > 3000);
    }

    // 7
    @Test
    void testException() {
        assertThrows(ArithmeticException.class,
                () -> {
                    int x = 10 / 0;
                });
    }
}
