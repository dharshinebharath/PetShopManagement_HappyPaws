package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.PetCategories;

import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PetCategoriesRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ✅ 1. Name should not be blank
    @Test
    void testNameShouldNotBeBlank() {
        PetCategories category = new PetCategories();
        category.setName("");

        Set<ConstraintViolation<PetCategories>> violations = validator.validate(category);

        assertFalse(violations.isEmpty());
    }

    // ✅ 2. Valid category (should pass)
    @Test
    void testValidCategory() {
        PetCategories category = new PetCategories();
        category.setName("Dog");

        Set<ConstraintViolation<PetCategories>> violations = validator.validate(category);

        assertTrue(violations.isEmpty());
    }
}