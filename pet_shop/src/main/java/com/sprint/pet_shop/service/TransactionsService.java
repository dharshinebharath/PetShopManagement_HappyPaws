package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.TransactionsRepository;
import com.sprint.pet_shop.service.interfaces.TransactionsInterface;

@Service
public class TransactionsService implements TransactionsInterface{
	@Autowired 
	private TransactionsRepository transactionsRepository;
	//save
	@Override
	public TransactionsEntity saveTransactionsEntity(TransactionsEntity entity) {

	    if (entity.getCustomerId() == null || entity.getPetId() == null) {
	        throw new InvalidDataException("Customer ID and Pet ID cannot be null");
	    }

	    return transactionsRepository.save(entity);
	}
	//saveall
	@Override
	public List<TransactionsEntity> TransactionsEntityAll(List<TransactionsEntity> transactionsEntity){
		return transactionsRepository.saveAll(transactionsEntity);
	}

    // GET ALL
	@Override
    public List<TransactionsEntity> getAllTransactions() {
        return transactionsRepository.findAll();
    }
    //get by id
	@Override
    public TransactionsEntity getTransactionById(Long id) {
		return transactionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }
	@Override
    public TransactionsEntity updateTransaction(Long id, TransactionsEntity newData) {

        TransactionsEntity existing = transactionsRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        
        if (newData.getCustomerId() == null || newData.getPetId() == null) {
            throw new InvalidDataException("Customer ID and Pet ID cannot be null");
        }


        existing.setCustomerId(newData.getCustomerId());
        existing.setPetId(newData.getPetId());
        existing.setTransactionDate(newData.getTransactionDate());
        existing.setAmount(newData.getAmount());
        existing.setTransactionStatus(newData.getTransactionStatus());
        

        return transactionsRepository.save(existing);
    }

    // DELETE
	@Override
	public String deleteTransaction(Long id) {

	    if (!transactionsRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Transaction not found with id: " + id);
	    }

	    transactionsRepository.deleteById(id);
	    return "Deleted successfully";
	}
    
}


