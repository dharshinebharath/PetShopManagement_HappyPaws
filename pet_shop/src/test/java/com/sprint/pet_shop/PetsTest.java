package com.sprint.pet_shop;

import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.PetCategories;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PetsTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ✅ 1. Name should not be blank
    @Test
    void testNameShouldNotBeBlank() {
        Pets pet = new Pets();
        pet.setName("");
        pet.setBreed("Dog");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("10000"));
        pet.setDescription("Good dog");
        pet.setImage_url("url");

        PetCategories category = new PetCategories();
//        category.setCategory_id(1);
        pet.setCategory(category);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ✅ 2. Breed should not be blank
    @Test
    void testBreedShouldNotBeBlank() {
        Pets pet = new Pets();
        pet.setName("Dog");
        pet.setBreed("");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("10000"));
        pet.setDescription("Good dog");
        pet.setImage_url("url");

        PetCategories category = new PetCategories();
//        category.setCategory_id(1);
        pet.setCategory(category);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ✅ 3. Price should not be null
    @Test
    void testPriceShouldNotBeNull() {
        Pets pet = new Pets();
        pet.setName("Dog");
        pet.setBreed("Dog");
        pet.setAge(2);
        pet.setPrice(null);
        pet.setDescription("Good dog");
        pet.setImage_url("url");

        PetCategories category = new PetCategories();
//        category.setCategory_id(1);
        pet.setCategory(category);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ✅ 4. Category should not be null
    @Test
    void testCategoryShouldNotBeNull() {
        Pets pet = new Pets();
        pet.setName("Dog");
        pet.setBreed("Dog");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("10000"));
        pet.setDescription("Good dog");
        pet.setImage_url("url");
        pet.setCategory(null);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }

    // ✅ 5. Valid pet (should pass)
    @Test
    void testValidPet() {
        Pets pet = new Pets();
        pet.setName("Dog");
        pet.setBreed("Labrador");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("10000"));
        pet.setDescription("Friendly dog");
        pet.setImage_url("url");

        PetCategories category = new PetCategories();
//        category.setCategory_id(1);
        pet.setCategory(category);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertTrue(violations.isEmpty());
    }
}