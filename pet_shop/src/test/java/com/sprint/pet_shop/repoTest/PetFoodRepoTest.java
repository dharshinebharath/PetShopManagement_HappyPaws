package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.PetFood;
import jakarta.validation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PetFoodRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ✅ 1. Name should not be blank
    @Test
    void testNameShouldNotBeBlank() {
        PetFood food = new PetFood();
        food.setName("");
        food.setBrand("Pedigree");
        food.setType("Dry");
        food.setQuantity(10);
        food.setPrice(new BigDecimal("200.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // ✅ 2. Brand should not be blank
    @Test
    void testBrandShouldNotBeBlank() {
        PetFood food = new PetFood();
        food.setName("Dog Food");
        food.setBrand("");
        food.setType("Dry");
        food.setQuantity(10);
        food.setPrice(new BigDecimal("200.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // ✅ 3. Type should not be blank
    @Test
    void testTypeShouldNotBeBlank() {
        PetFood food = new PetFood();
        food.setName("Dog Food");
        food.setBrand("Pedigree");
        food.setType("");
        food.setQuantity(10);
        food.setPrice(new BigDecimal("200.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // ✅ 4. Price should not be null
    @Test
    void testPriceShouldNotBeNull() {
        PetFood food = new PetFood();
        food.setName("Dog Food");
        food.setBrand("Pedigree");
        food.setType("Dry");
        food.setQuantity(10);
        food.setPrice(null);

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // ✅ 5. Valid PetFood object (should pass)
    @Test
    void testValidPetFood() {
        PetFood food = new PetFood();
        food.setName("Dog Food");
        food.setBrand("Pedigree");
        food.setType("Dry");
        food.setQuantity(10);
        food.setPrice(new BigDecimal("200.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertTrue(violations.isEmpty());
    }
}