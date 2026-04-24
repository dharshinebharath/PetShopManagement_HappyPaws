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
    void testValidGroomingService() {
        GroomingServices service = new GroomingServices();
        service.setName("Full Grooming");
        service.setDescription("Complete pet grooming service");
        service.setPrice(new BigDecimal("500.00"));
        service.setAvailable(true);

        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);

        assertTrue(violations.isEmpty());
    }
    @Test
    void testNameShouldNotBeBlank() {
        GroomingServices service = new GroomingServices();
        service.setName("");
        service.setPrice(new BigDecimal("300.00"));
        service.setAvailable(true);

        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testPriceShouldNotBeNull() {
        GroomingServices service = new GroomingServices();
        service.setName("Bath Service");
        service.setPrice(null);
        service.setAvailable(true);

        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testDescriptionCanBeNull() {
        GroomingServices service = new GroomingServices();
        service.setName("Nail Cutting");
        service.setPrice(new BigDecimal("150.00"));
        service.setAvailable(true);
        service.setDescription(null);

        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);

        assertTrue(violations.isEmpty());
    }
    @Test
    void testZeroPriceAllowedAsBoundary() {
        GroomingServices service = new GroomingServices();
        service.setName("Free Checkup");
        service.setPrice(BigDecimal.ZERO);
        service.setAvailable(true);

        Set<ConstraintViolation<GroomingServices>> violations = validator.validate(service);

        assertTrue(violations.isEmpty());
    }
}
