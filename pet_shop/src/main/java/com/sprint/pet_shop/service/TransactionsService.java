package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.repository.TransactionsRepository;

@Service
public class TransactionsService {
	@Autowired 
	private TransactionsRepository transactionsRepository;
	public TransactionsEntity saveTransactionsEntity(TransactionsEntity transactionsEntity) {
		return transactionsRepository.save(transactionsEntity);
	}
	public List<TransactionsEntity> TransactionsEntityAll(List<TransactionsEntity> transactionsEntity){
		return transactionsRepository.saveAll(transactionsEntity);
	}

    // GET ALL
    public List<TransactionsEntity> getAllTransactions() {
        return transactionsRepository.findAll();
    }
    //get by id
    public TransactionsEntity getTransactionById(Long id) {
        return transactionsRepository.findById(id).orElse(null);
    }
    public TransactionsEntity updateTransaction(Long id, TransactionsEntity newData) {

        TransactionsEntity existing = transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));

        existing.setCustomerId(newData.getCustomerId());
        existing.setPetId(newData.getPetId());
        existing.setTransactionDate(newData.getTransactionDate());
        existing.setAmount(newData.getAmount());
        existing.setTransactionStatus(newData.getTransactionStatus());

        return transactionsRepository.save(existing);
    }

    // DELETE
    public String deleteTransaction(Long id) {
        if (transactionsRepository.existsById(id)) {
            transactionsRepository.deleteById(id);
            return "Deleted successfully";
        }
        return "Transaction not found";
    }
    


   
}


