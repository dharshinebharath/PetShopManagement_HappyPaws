package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Vaccinations;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VaccinationsRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // 1️⃣ VALID CASE (Positive)
    @Test
    void testValidVaccination() {
        Vaccinations v = new Vaccinations();
        v.setName("Rabies Vaccine");
        v.setDescription("Prevents rabies infection");
        v.setPrice(new BigDecimal("500.00"));
        v.setAvailable(true);

        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);

        assertTrue(violations.isEmpty());
    }

    // 2️⃣ NEGATIVE: Name blank
    @Test
    void testNameShouldNotBeBlank() {
        Vaccinations v = new Vaccinations();
        v.setName("");
        v.setPrice(new BigDecimal("300.00"));
        v.setAvailable(true);

        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);

        assertFalse(violations.isEmpty());
    }

    // 3️⃣ NEGATIVE: Price null
    @Test
    void testPriceShouldNotBeNull() {
        Vaccinations v = new Vaccinations();
        v.setName("Parvo Vaccine");
        v.setPrice(null);
        v.setAvailable(true);

        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);

        assertFalse(violations.isEmpty());
    }

    // 4️⃣ EDGE CASE: Description can be null (Valid)
    @Test
    void testDescriptionCanBeNull() {
        Vaccinations v = new Vaccinations();
        v.setName("Distemper Vaccine");
        v.setPrice(new BigDecimal("250.00"));
        v.setAvailable(true);
        v.setDescription(null);

        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);

        assertTrue(violations.isEmpty());
    }

    // 5️⃣ EDGE CASE: Negative price allowed (since no constraint exists)
    @Test
    void testNegativePriceAllowedCurrently() {
        Vaccinations v = new Vaccinations();
        v.setName("Test Vaccine");
        v.setPrice(new BigDecimal("-100.00"));
        v.setAvailable(true);

        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);

        // passes because no @Positive or @Min validation exists
        assertTrue(violations.isEmpty());
    }
}