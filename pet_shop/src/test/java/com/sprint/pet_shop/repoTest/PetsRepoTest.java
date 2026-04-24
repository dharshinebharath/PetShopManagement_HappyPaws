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
    @Test
    void testNameShouldNotBeBlank() {
        Pets pet = getValidPet();
        pet.setName("");

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testBreedShouldNotBeBlank() {
        Pets pet = getValidPet();
        pet.setBreed("");

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testPriceShouldNotBeNull() {
        Pets pet = getValidPet();
        pet.setPrice(null);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testCategoryShouldNotBeNull() {
        Pets pet = getValidPet();
        pet.setCategory(null);

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertFalse(violations.isEmpty());
    }
    @Test
    void testValidPet() {
        Pets pet = getValidPet();

        Set<ConstraintViolation<Pets>> violations = validator.validate(pet);

        assertTrue(violations.isEmpty());
    }
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
