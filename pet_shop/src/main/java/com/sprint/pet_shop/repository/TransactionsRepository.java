package com.sprint.pet_shop.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.TransactionStatus;
import com.sprint.pet_shop.entity.TransactionsEntity;

// Repository for accessing purchase and transaction histories.
// Allows us to filter transactions by customer, date ranges, or transaction status.
public interface TransactionsRepository extends JpaRepository<TransactionsEntity,Long>{
	
	List<TransactionsEntity> findByCustomerCustomerId(Long customerId);

	List<TransactionsEntity> findByTransactionStatus(TransactionStatus status);

	List<TransactionsEntity> findByTransactionDateBetween(LocalDate start, LocalDate end);

	@Query("SELECT t FROM TransactionsEntity t ORDER BY t.transactionId ASC")
	List<TransactionsEntity> findAllSorted();
}
