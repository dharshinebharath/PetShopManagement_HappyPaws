package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Employees;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class EmployeesRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ✅ 1. Positive: valid employee
    @Test
    void testValidEmployee() {
        Employees emp = new Employees();
        emp.setFirstName("John");
        emp.setLastName("Smith");
        emp.setPosition("Veterinarian");
        emp.setHireDate(LocalDate.now());
        emp.setPhoneNumber("9876543210");
        emp.setEmail("john@example.com");

        Set<ConstraintViolation<Employees>> violations = validator.validate(emp);

        assertTrue(violations.isEmpty());
    }

    // ✅ 2. Positive: another valid employee (different data)
    @Test
    void testAnotherValidEmployee() {
        Employees emp = new Employees();
        emp.setFirstName("Emily");
        emp.setLastName("Johnson");
        emp.setPosition("Groomer");
        emp.setHireDate(LocalDate.now());
        emp.setPhoneNumber("9123456780");
        emp.setEmail("emily@example.com");

        Set<ConstraintViolation<Employees>> violations = validator.validate(emp);

        assertTrue(violations.isEmpty());
    }

    // ❌ 3. Negative: first name is null
    @Test
    void testFirstNameNull() {
        Employees emp = new Employees();
        emp.setFirstName(null);
        emp.setLastName("Smith");
        emp.setPosition("Vet");
        emp.setHireDate(LocalDate.now());
        emp.setPhoneNumber("9876543210");
        emp.setEmail("john@example.com");

        Set<ConstraintViolation<Employees>> violations = validator.validate(emp);

        assertFalse(violations.isEmpty());
    }

    // ❌ 4. Negative: position blank
    @Test
    void testPositionBlank() {
        Employees emp = new Employees();
        emp.setFirstName("John");
        emp.setLastName("Smith");
        emp.setPosition("");
        emp.setHireDate(LocalDate.now());
        emp.setPhoneNumber("9876543210");
        emp.setEmail("john@example.com");

        Set<ConstraintViolation<Employees>> violations = validator.validate(emp);

        assertFalse(violations.isEmpty());
    }

    // ❌ 5. Negative: email null
    @Test
    void testEmailNull() {
        Employees emp = new Employees();
        emp.setFirstName("John");
        emp.setLastName("Smith");
        emp.setPosition("Vet");
        emp.setHireDate(LocalDate.now());
        emp.setPhoneNumber("9876543210");
        emp.setEmail(null);

        Set<ConstraintViolation<Employees>> violations = validator.validate(emp);

        assertFalse(violations.isEmpty());
    }
}