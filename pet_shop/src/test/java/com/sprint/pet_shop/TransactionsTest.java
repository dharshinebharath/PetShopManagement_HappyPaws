package com.sprint.pet_shop;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.*;

public class TransactionsTest {

    @Test
    void testSettersAndGetters() {
        TransactionsEntity transaction = new TransactionsEntity();

        Long id = 1L;
        Date date = Date.valueOf("2024-01-01");
        BigDecimal amount = BigDecimal.valueOf(5000.75);
        TransactionStatus status = TransactionStatus.Success;

        Customers customer = new Customers();
        customer.setCustomerId(10L);

        Pets pet = new Pets();
        pet.setPet_id(20L);

        transaction.setTransactionId(id);
        transaction.setTransactionDate(date);
        transaction.setAmount(amount);
        transaction.setTransactionStatus(status);
        transaction.setCustomer(customer);
        transaction.setPet(pet);

        assertEquals(id, transaction.getTransactionId());
        assertEquals(date, transaction.getTransactionDate());
        assertEquals(amount, transaction.getAmount());
        assertEquals(status, transaction.getTransactionStatus());
        assertEquals(10L, transaction.getCustomer().getCustomerId());
        assertEquals(20L, transaction.getPet().getPet_id());
    }

    @Test
    void testDefaultValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        assertNull(transaction.getTransactionId());
        assertNull(transaction.getTransactionDate());
        assertNull(transaction.getTransactionStatus());
        assertNull(transaction.getCustomer());
        assertNull(transaction.getPet());
        assertNull(transaction.getAmount());
    }

    @Test
    void testUpdateValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        Customers c1 = new Customers();
        c1.setCustomerId(1L);

        Customers c2 = new Customers();
        c2.setCustomerId(99L);

        transaction.setCustomer(c1);
        transaction.setCustomer(c2);

        assertEquals(99L, transaction.getCustomer().getCustomerId());
    }

    @Test
    void testMultipleObjects() {
        TransactionsEntity t1 = new TransactionsEntity();
        TransactionsEntity t2 = new TransactionsEntity();

        Customers c1 = new Customers();
        c1.setCustomerId(1L);

        Customers c2 = new Customers();
        c2.setCustomerId(2L);

        t1.setCustomer(c1);
        t2.setCustomer(c2);

        assertNotEquals(t1.getCustomer().getCustomerId(), t2.getCustomer().getCustomerId());
    }

    @Test
    void testEnumValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionStatus(TransactionStatus.Success);
        assertEquals(TransactionStatus.Success, transaction.getTransactionStatus());

        transaction.setTransactionStatus(TransactionStatus.Failed);
        assertEquals(TransactionStatus.Failed, transaction.getTransactionStatus());
    }

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

    @Test
    void testAmountValues() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setAmount(BigDecimal.valueOf(0.0));
        assertEquals(BigDecimal.valueOf(0.0), transaction.getAmount());

        transaction.setAmount(BigDecimal.valueOf(99999.99));
        assertEquals(BigDecimal.valueOf(99999.99), transaction.getAmount());
    }

    @Test
    void testNullAssignments() {
        TransactionsEntity transaction = new TransactionsEntity();

        transaction.setTransactionDate(null);
        transaction.setTransactionStatus(null);

        assertNull(transaction.getTransactionDate());
        assertNull(transaction.getTransactionStatus());
    }
}