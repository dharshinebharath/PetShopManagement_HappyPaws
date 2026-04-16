package com.sprint.pet_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.TransactionStatus;
import com.sprint.pet_shop.entity.TransactionsEntity;

public class TransactionsTest {

    @Test
    void testSettersAndGetters() {
        TransactionsEntity transaction = new TransactionsEntity();

        Long id = 1L;
        Integer customerId = 10;
        Integer petId = 20;
        Date date = Date.valueOf("2024-01-01");
        double amount = 5000.75;
        TransactionStatus status = TransactionStatus.Success;

        transaction.setTransactionId(id);
        transaction.setCustomerId(customerId);
        transaction.setPetId(petId);
        transaction.setTransactionDate(date);
        transaction.setAmount(amount);
        transaction.setTransactionStatus(status);

        assertEquals(id, transaction.getTransactionId());
        assertEquals(customerId, transaction.getCustomerId());
        assertEquals(petId, transaction.getPetId());
        assertEquals(date, transaction.getTransactionDate());
        assertEquals(amount, transaction.getAmount());
        assertEquals(status, transaction.getTransactionStatus());
    }

    // ✅ Default values test
    @Test
    void testDefaultValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        assertNull(transaction.getTransactionId());
        assertNull(transaction.getCustomerId());
        assertNull(transaction.getPetId());
        assertNull(transaction.getTransactionDate());
        assertNull(transaction.getTransactionStatus());

        assertEquals(0.0, transaction.getAmount()); // primitive default
    }

    // ✅ Test updating values
    @Test
    void testUpdateValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setCustomerId(1);
        transaction.setCustomerId(99);

        assertEquals(99, transaction.getCustomerId());
    }

    // ✅ Test multiple objects independently
    @Test
    void testMultipleObjects() {
        TransactionsEntity t1 = new TransactionsEntity();
        TransactionsEntity t2 = new TransactionsEntity();

        t1.setCustomerId(1);
        t2.setCustomerId(2);

        assertNotEquals(t1.getCustomerId(), t2.getCustomerId());
    }

    // ✅ Enum test
    @Test
    void testEnumValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionStatus(TransactionStatus.Success);
        assertEquals(TransactionStatus.Success, transaction.getTransactionStatus());

        transaction.setTransactionStatus(TransactionStatus.Failed);
        assertEquals(TransactionStatus.Failed, transaction.getTransactionStatus());
    }

    // ✅ Date handling test
    @Test
    void testDateAssignment() {
        TransactionsEntity transaction = new TransactionsEntity();

        Date date1 = Date.valueOf("2023-05-10");
        Date date2 = Date.valueOf("2024-06-15");

        transaction.setTransactionDate(date1);
        assertEquals(date1, transaction.getTransactionDate());

        transaction.setTransactionDate(date2);
        assertEquals(date2, transaction.getTransactionDate());
    }

    // ✅ Amount edge cases
    @Test
    void testAmountValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setAmount(0.0);
        assertEquals(0.0, transaction.getAmount());

        transaction.setAmount(99999.99);
        assertEquals(99999.99, transaction.getAmount());

        transaction.setAmount(-100); // logically wrong but allowed in entity
        assertEquals(-100, transaction.getAmount());
    }

    // ✅ Null assignment test (important for wrapper types)
    @Test
    void testNullAssignments() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setCustomerId(null);
        transaction.setPetId(null);
        transaction.setTransactionDate(null);
        transaction.setTransactionStatus(null);

        assertNull(transaction.getCustomerId());
        assertNull(transaction.getPetId());
        assertNull(transaction.getTransactionDate());
        assertNull(transaction.getTransactionStatus());
    }
}