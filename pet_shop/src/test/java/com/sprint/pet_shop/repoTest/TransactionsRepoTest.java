package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.TransactionStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TransactionsRepoTest {
    @Test
    void testValidTransactionCreation() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionDate(Date.valueOf("2024-03-01"));
        transaction.setAmount(BigDecimal.valueOf(1500));
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        Customers customer = new Customers();
        customer.setCustomerId(5L);

        Pets pet = new Pets();
        pet.setPet_id(8L);

        transaction.setCustomer(customer);
        transaction.setPet(pet);

        assertNotNull(transaction.getCustomer());
        assertNotNull(transaction.getPet());
    }

    @Test
    void testLargeAmountTransaction() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setAmount(BigDecimal.valueOf(9999999.99));

        assertEquals(BigDecimal.valueOf(9999999.99), transaction.getAmount());
    }

    @Test
    void testSuccessfulStatusAssignment() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        assertEquals(TransactionStatus.SUCCESS, transaction.getTransactionStatus());
    }

    @Test
    void testFailedStatusAssignment() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionStatus(TransactionStatus.FAILED);

        assertEquals(TransactionStatus.FAILED, transaction.getTransactionStatus());
    }

    @Test
    void testCustomerAndPetAssignment() {
        TransactionsEntity transaction = new TransactionsEntity();

        Customers customer = new Customers();
        customer.setCustomerId(101L);

        Pets pet = new Pets();
        pet.setPet_id(202L);

        transaction.setCustomer(customer);
        transaction.setPet(pet);

        assertEquals(101L, transaction.getCustomer().getCustomerId());
        assertEquals(202L, transaction.getPet().getPet_id());
    }

    @Test
    void testNullCustomer() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setCustomer(null);

        assertNull(transaction.getCustomer());
    }

    @Test
    void testNullPet() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setPet(null);

        assertNull(transaction.getPet());
    }

    @Test
    void testNullTransactionStatus() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionStatus(null);

        assertNull(transaction.getTransactionStatus());
    }

    @Test
    void testNegativeAmount() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setAmount(BigDecimal.valueOf(-500));

        assertEquals(BigDecimal.valueOf(-500), transaction.getAmount());
    }

    @Test
    void testNullTransactionDate() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionDate(null);

        assertNull(transaction.getTransactionDate());
    }

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidTransaction1() {
        TransactionsEntity t = createValidTransaction();
        assertTrue(validator.validate(t).isEmpty());
    }

    @Test
    void testValidTransaction2() {
        TransactionsEntity t = createValidTransaction();
        t.setAmount(new BigDecimal("1000.00"));
        assertTrue(validator.validate(t).isEmpty());
    }

    @Test
    void testValidTransaction3() {
        TransactionsEntity t = createValidTransaction();
        t.setTransactionStatus(TransactionStatus.PENDING);
        assertTrue(validator.validate(t).isEmpty());
    }

    @Test
    void testValidTransaction4() {
        TransactionsEntity t = createValidTransaction();
        t.setAmount(new BigDecimal("0.01"));
        assertTrue(validator.validate(t).isEmpty());
    }

    @Test
    void testValidTransaction5() {
        TransactionsEntity t = createValidTransaction();
        t.setTransactionDate(LocalDate.now());
        assertTrue(validator.validate(t).isEmpty());
    }

    @Test
    void testTransactionDateNull() {
        TransactionsEntity t = createValidTransaction();
        t.setTransactionDate(null);

        Set<ConstraintViolation<TransactionsEntity>> violations = validator.validate(t);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testAmountNull() {
        TransactionsEntity t = createValidTransaction();
        t.setAmount(null);

        Set<ConstraintViolation<TransactionsEntity>> violations = validator.validate(t);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testStatusNull() {
        TransactionsEntity t = createValidTransaction();
        t.setTransactionStatus(null);

        Set<ConstraintViolation<TransactionsEntity>> violations = validator.validate(t);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testAllFieldsNull() {
        TransactionsEntity t = new TransactionsEntity();

        Set<ConstraintViolation<TransactionsEntity>> violations = validator.validate(t);
        assertFalse(violations.isEmpty());
    }

    @Test
    void testMissingAmountOnly() {
        TransactionsEntity t = createValidTransaction();
        t.setAmount(null);

        Set<ConstraintViolation<TransactionsEntity>> violations = validator.validate(t);
        assertFalse(violations.isEmpty());
    }

    private TransactionsEntity createValidTransaction() {
        TransactionsEntity t = new TransactionsEntity();
        t.setTransactionDate(LocalDate.now());
        t.setAmount(new BigDecimal("500.00"));
        t.setTransactionStatus(TransactionStatus.SUCCESS);
        return t;
    }

}
