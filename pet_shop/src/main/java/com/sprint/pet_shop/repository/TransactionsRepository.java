package com.sprint.pet_shop.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.pet_shop.entity.TransactionStatus;
import com.sprint.pet_shop.entity.TransactionsEntity;

public interface TransactionsRepository extends JpaRepository<TransactionsEntity,Long>{
	
	

	List<TransactionsEntity> findByCustomerCustomerId(Long customerId);

	List<TransactionsEntity> findByTransactionStatus(TransactionStatus status);

	List<TransactionsEntity> findByTransactionDateBetween(LocalDate start, LocalDate end);
}
