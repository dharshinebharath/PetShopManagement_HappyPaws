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
    @Test
    void testFirstNameNull() {
        Customers customer = new Customers();
        customer.setFirstName(null);
        customer.setLastName("Kandan");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testFirstNameBlank() {
        Customers customer = new Customers();
        customer.setFirstName("");
        customer.setLastName("Kandan");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testLastNameNull() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName(null);

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testInvalidEmailAndPhoneStillPasses() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName("Kandan");
        customer.setEmail("invalid@@email###");
        customer.setPhoneNumber("ABC123@@@");

        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty());
    }
}
