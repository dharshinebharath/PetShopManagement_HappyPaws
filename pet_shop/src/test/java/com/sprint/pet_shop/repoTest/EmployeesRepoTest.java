package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Employees;

public class EmployeesRepoTest {
	// 1 - Positive
    @Test
    void testEmployeeName() {
        Employees e = new Employees();
        e.setFirstName("John");

        assertEquals("John", e.getFirstName());
    }

    // 2 - Negative (null check)
    @Test
    void testEmailNull() {
        Employees e = new Employees();

        assertNull(e.getEmail());
    }

    // 3 - Positive
    @Test
    void testEmailNotNull() {
        Employees e = new Employees();
        e.setEmail("test@gmail.com");

        assertNotNull(e.getEmail());
    }

    // 4 - Positive condition
    @Test
    void testSalaryCondition() {
        int salary = 5000;

        assertTrue(salary > 3000);
    }

    // 5 - Negative (exception case)
    @Test
    void testException() {
        assertThrows(ArithmeticException.class, () -> {
            int x = 10 / 0;
        });
    }
}
