package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.entity.TransactionStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TransactionsRepoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // =========================
    // ✅ POSITIVE TEST CASES (5)
    // =========================

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
        t.setTransactionStatus(TransactionStatus.Pending);
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
        t.setTransactionDate(new Date(System.currentTimeMillis()));
        assertTrue(validator.validate(t).isEmpty());
    }

    // =========================
    // ❌ NEGATIVE TEST CASES (5)
    // =========================

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

    // =========================
    // 🔧 Helper Method
    // =========================
    private TransactionsEntity createValidTransaction() {
        TransactionsEntity t = new TransactionsEntity();
        t.setTransactionDate(new Date(System.currentTimeMillis()));
        t.setAmount(new BigDecimal("500.00"));
        t.setTransactionStatus(TransactionStatus.Success);
        return t;
    }
}