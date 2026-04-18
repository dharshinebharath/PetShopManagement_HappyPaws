package com.sprint.pet_shop.repoTest;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.sprint.pet_shop.entity.*;

public class TransactionsRepoTest {
	@Test
	void testValidTransactionCreation() {
	    TransactionsEntity transaction = new TransactionsEntity();

	    transaction.setTransactionDate(Date.valueOf("2024-03-01"));
	    transaction.setAmount(BigDecimal.valueOf(1500));
	    transaction.setTransactionStatus(TransactionStatus.Success);

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

	    transaction.setTransactionStatus(TransactionStatus.Success);

	    assertEquals(TransactionStatus.Success, transaction.getTransactionStatus());
	}

	@Test
	void testFailedStatusAssignment() {
	    TransactionsEntity transaction = new TransactionsEntity();

	    transaction.setTransactionStatus(TransactionStatus.Failed);

	    assertEquals(TransactionStatus.Failed, transaction.getTransactionStatus());
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

}