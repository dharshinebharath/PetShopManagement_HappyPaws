package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.TransactionsEntity;

public interface TransactionsInterface {

	TransactionsEntity saveTransactionsEntity(TransactionsEntity transactionsEntity);

	List<TransactionsEntity> TransactionsEntityAll(List<TransactionsEntity> transactionsEntity);

	List<TransactionsEntity> getAllTransactions();

	TransactionsEntity getTransactionById(Long id);

	TransactionsEntity updateTransaction(Long id, TransactionsEntity newData);

	String deleteTransaction(Long id);
	

}
