// This test exercises the expected behavior for supplier repo test.
package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Supplier;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class SupplierRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void testValidSupplier() {
        Supplier s = new Supplier();
        s.setName("Pet Supply Co");
        s.setContactPerson("John Smith");
        s.setPhoneNumber("9876543210");
        s.setEmail("petsupply@gmail.com");

        Set<ConstraintViolation<Supplier>> violations = validator.validate(s);

        assertTrue(violations.isEmpty());
    }
    @Test
    void testAnotherValidSupplier() {
        Supplier s = new Supplier();
        s.setName("Animal Care Ltd");
        s.setContactPerson("Emily Johnson");
        s.setPhoneNumber("9123456780");
        s.setEmail("animalcare@gmail.com");

        Set<ConstraintViolation<Supplier>> violations = validator.validate(s);

        assertTrue(violations.isEmpty());
    }
    @Test
    void testNameNull() {
        Supplier s = new Supplier();
        s.setName(null);
        s.setContactPerson("John Smith");
        s.setPhoneNumber("9876543210");
        s.setEmail("test@gmail.com");

        Set<ConstraintViolation<Supplier>> violations = validator.validate(s);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testContactPersonBlank() {
        Supplier s = new Supplier();
        s.setName("Pet Supply Co");
        s.setContactPerson("");
        s.setPhoneNumber("9876543210");
        s.setEmail("test@gmail.com");

        Set<ConstraintViolation<Supplier>> violations = validator.validate(s);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testEmailNull() {
        Supplier s = new Supplier();
        s.setName("Pet Supply Co");
        s.setContactPerson("John Smith");
        s.setPhoneNumber("9876543210");
        s.setEmail(null);

        Set<ConstraintViolation<Supplier>> violations = validator.validate(s);

        assertFalse(violations.isEmpty());
    }
}
