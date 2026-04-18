package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.GroomingServices;
import jakarta.validation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GroomingServicesRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testNameShouldNotBeBlank() {
        GroomingServices service = new GroomingServices();
        service.setName("");
        service.setPrice(new BigDecimal("100.00"));
        service.setAvailable(true);
        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testPriceShouldNotBeNull() {
        GroomingServices service = new GroomingServices();
        service.setName("Bath");
        service.setPrice(null);
        service.setAvailable(true);
        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testDescriptionCanBeNull() {
        GroomingServices service = new GroomingServices();
        service.setName("Bath");
        service.setDescription(null);
        service.setPrice(new BigDecimal("100.00"));
        service.setAvailable(true);
        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testNegativePrice() {
        GroomingServices service = new GroomingServices();
        service.setName("Bath");
        service.setPrice(new BigDecimal("-50.00"));
        service.setAvailable(true);
        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testDefaultAvailability() {
        GroomingServices service = new GroomingServices();
        service.setName("Bath");
        service.setPrice(new BigDecimal("100.00"));
        assertTrue(service.isAvailable());
    }
}