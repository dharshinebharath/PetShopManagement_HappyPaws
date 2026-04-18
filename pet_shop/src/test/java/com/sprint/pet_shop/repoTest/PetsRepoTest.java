package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.PetCategories;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.PetCategories;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PetsRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ❌ 1. Name should not be blank (Negative)
    @Test
    void testNameShouldNotBeBlank() {
        Pets pet = getValidPet();
        pet.setName(""); // invalid

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ❌ 2. Breed should not be blank (Negative)
    @Test
    void testBreedShouldNotBeBlank() {
        Pets pet = getValidPet();
        pet.setBreed(""); // invalid

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ❌ 3. Price should not be null (Negative)
    @Test
    void testPriceShouldNotBeNull() {
        Pets pet = getValidPet();
        pet.setPrice(null); // invalid

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ❌ 4. Category should not be null (Negative)
    @Test
    void testCategoryShouldNotBeNull() {
        Pets pet = getValidPet();
        pet.setCategory(null); // invalid

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ✅ 5. Valid Pet (Positive)
    @Test
    void testValidPet() {
        Pets pet = getValidPet();

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertTrue(violations.isEmpty());
    }

    // 🔥 Helper Method (VERY CLEAN)
    private Pets getValidPet() {
        Pets pet = new Pets();
        pet.setName("Dog");
        pet.setBreed("Labrador");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("10000"));
        pet.setDescription("Friendly dog");
        pet.setImage_url("url");

        PetCategories category = new PetCategories();
        category.setName("Dog");
        pet.setCategory(category);

        return pet;
    }
}