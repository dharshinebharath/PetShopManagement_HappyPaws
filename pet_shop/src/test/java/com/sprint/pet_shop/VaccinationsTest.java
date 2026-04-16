package com.sprint.pet_shop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.Vaccinations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class VaccinationsTest {

	 private Validator validator;

	    @BeforeEach
	    void setUp() {
	        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	        validator = factory.getValidator();
	    }

	    @Test
	    void testNameShouldNotBeBlank() {
	        Vaccinations v = new Vaccinations();
	        v.setName("");
	        v.setPrice(new BigDecimal("100.00"));
	        v.setAvailable(true);
	        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);
	        assertFalse(violations.isEmpty());
	    }

	    @Test
	    void testPriceShouldNotBeNull() {
	        Vaccinations v = new Vaccinations();
	        v.setName("Rabies");
	        v.setPrice(null);
	        v.setAvailable(true);
	        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);
	        assertFalse(violations.isEmpty());
	    }

	    @Test
	    void testDescriptionCanBeNull() {
	        Vaccinations v = new Vaccinations();
	        v.setName("Rabies");
	        v.setDescription(null);
	        v.setPrice(new BigDecimal("100.00"));
	        v.setAvailable(true);
	        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);
	        assertTrue(violations.isEmpty());
	    }

	    @Test
	    void testNegativePrice() {
	        Vaccinations v = new Vaccinations();
	        v.setName("Rabies");
	        v.setPrice(new BigDecimal("-50.00"));
	        v.setAvailable(true);
	        Set<ConstraintViolation<Vaccinations>> violations = validator.validate(v);
	        assertTrue(violations.isEmpty());
	    }

	    @Test
	    void testDefaultAvailability() {
	        Vaccinations v = new Vaccinations();
	        v.setName("Rabies");
	        v.setPrice(new BigDecimal("100.00"));
	        assertTrue(v.getAvailable());
	    }
}
