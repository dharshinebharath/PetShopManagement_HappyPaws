package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Customers;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CustomersRepoTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // 1️⃣ VALID CASE (Positive)
    @Test
    void testValidCustomer() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName("Kandan");
        customer.setEmail("revathi@gmail.com");
        customer.setPhoneNumber("9876543210");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertTrue(violations.isEmpty());
    }

    // 2️⃣ NEGATIVE: First name null
    @Test
    void testFirstNameNull() {
        Customers customer = new Customers();
        customer.setFirstName(null);
        customer.setLastName("Kandan");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertFalse(violations.isEmpty());
    }

    // 3️⃣ NEGATIVE: First name blank
    @Test
    void testFirstNameBlank() {
        Customers customer = new Customers();
        customer.setFirstName("");
        customer.setLastName("Kandan");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertFalse(violations.isEmpty());
    }

    // 4️⃣ NEGATIVE: Last name null
    @Test
    void testLastNameNull() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName(null);

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertFalse(violations.isEmpty());
    }

    // 5️⃣ EDGE CASE: Invalid email & phone STILL VALID (because no constraints)
    @Test
    void testInvalidEmailAndPhoneStillPasses() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName("Kandan");
        customer.setEmail("invalid@@email###");
        customer.setPhoneNumber("ABC123@@@");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        // No validation rules exist → will pass
        assertTrue(violations.isEmpty());
    }
}