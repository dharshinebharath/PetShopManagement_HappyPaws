package com.sprint.pet_shop;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.sprint.pet_shop.entity.Customers;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
public class CustomersTest {
    private static Validator validator;
    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    } 
    @Test
    void testValidCustomer() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName("Kandan");
        customer.setEmail("revathi@gmail.com");
        customer.setPhoneNumber("9876543210");
        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);
        assertTrue(violations.isEmpty());
    }
    //Test 2: Null values
    @Test
    void testNullFirstName() {
        Customers customer = new Customers();
        customer.setFirstName(null);
        customer.setLastName("Kandan");
        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);
        assertFalse(violations.isEmpty());
    }
    // Test 3: Blank values
    @Test
    void testBlankFirstName() {
        Customers customer = new Customers();
        customer.setFirstName(""); // blank
        customer.setLastName("Kandan");
        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);
        assertFalse(violations.isEmpty());
    }
    // Test 4: Missing last name
    @Test
    void testMissingLastName() {
        Customers customer = new Customers();
        customer.setFirstName("Revathi");
        customer.setLastName(null);
        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);
        assertFalse(violations.isEmpty());
    }
    // Test 5: Special characters & long values
    @Test
    void testSpecialAndLongValues() {
        Customers customer = new Customers();
        String longName = "A".repeat(50); 
        customer.setFirstName(longName);
        customer.setLastName("Test@123");
        customer.setEmail("invalid-email@@");
        customer.setPhoneNumber("12345abc");
        Set<ConstraintViolation<Customers>> violations = validator.validate(customer);
        // Only firstName & lastName are validated → should pass
        assertTrue(violations.isEmpty());
    }
}
	