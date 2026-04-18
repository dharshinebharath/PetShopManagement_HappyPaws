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

    // 1️⃣ VALID CASE (Positive)
    @Test
    void testValidPetFood() {
        PetFood food = new PetFood();
        food.setName("Dog Food");
        food.setBrand("Pedigree");
        food.setType("Dry");
        food.setQuantity(10);
        food.setPrice(new BigDecimal("250.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertTrue(violations.isEmpty());
    }

    // 2️⃣ NEGATIVE: Name blank
    @Test
    void testNameShouldNotBeBlank() {
        PetFood food = new PetFood();
        food.setName("");
        food.setBrand("Pedigree");
        food.setType("Dry");
        food.setQuantity(10);
        food.setPrice(new BigDecimal("250.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // 3️⃣ NEGATIVE: Brand blank
    @Test
    void testBrandShouldNotBeBlank() {
        PetFood food = new PetFood();
        food.setName("Cat Food");
        food.setBrand("");
        food.setType("Wet");
        food.setQuantity(5);
        food.setPrice(new BigDecimal("150.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // 4️⃣ NEGATIVE: Type blank
    @Test
    void testTypeShouldNotBeBlank() {
        PetFood food = new PetFood();
        food.setName("Rabbit Food");
        food.setBrand("Happypet");
        food.setType("");
        food.setQuantity(8);
        food.setPrice(new BigDecimal("180.00"));

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }

    // 5️⃣ EDGE CASE: Price null
    @Test
    void testPriceShouldNotBeNull() {
        PetFood food = new PetFood();
        food.setName("Fish Food");
        food.setBrand("AquaCare");
        food.setType("Dry");
        food.setQuantity(12);
        food.setPrice(null);

        Set<ConstraintViolation<PetFood>> violations = validator.validate(food);

        assertFalse(violations.isEmpty());
    }
}