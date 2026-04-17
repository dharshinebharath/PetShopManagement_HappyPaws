package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
<<<<<<< HEAD
=======
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.repository.PetsRepository;
>>>>>>> 618339db897ec2d74e949f7e9303cc84c5044c36
import com.sprint.pet_shop.repository.TransactionsRepository;
import com.sprint.pet_shop.service.interfaces.TransactionsInterface;

@Service
public class TransactionsService implements TransactionsInterface{
	@Autowired 
	private TransactionsRepository transactionsRepository;
<<<<<<< HEAD
=======
	
	@Autowired 
	private CustomersRepository customersRepository;
	@Autowired 
	private PetsRepository petsRepository;
>>>>>>> 618339db897ec2d74e949f7e9303cc84c5044c36
	//save
	@Override
	public TransactionsEntity saveTransactionsEntity(TransactionsEntity entity) {

	    if (entity.getCustomerId() == null || entity.getPetId() == null) {
	        throw new InvalidDataException("Customer ID and Pet ID cannot be null");
	    }


		if (entity.getCustomer() == null || entity.getCustomer().getCustomerId() == null) {
	        throw new InvalidDataException("Customer ID cannot be null");
	    }

	    if (entity.getPet() == null || entity.getPet().getPet_id() == null) {
	        throw new InvalidDataException("Pet ID cannot be null");
	    }

	    // 🔥 FETCH MANAGED ENTITY (IMPORTANT)
	    Customers customer = customersRepository.findById(entity.getCustomer().getCustomerId())
	            .orElseThrow(() -> new RuntimeException("Customer not found"));

	        Pets pet = petsRepository.findById(entity.getPet().getPet_id())
	            .orElseThrow(() -> new RuntimeException("Pet not found"));

	        entity.setCustomer(customer);
	        entity.setPet(pet);


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

        if (newData.getCustomer().getCustomerId() == null || newData.getPet().getPet_id() == null) {

            throw new InvalidDataException("Customer ID and Pet ID cannot be null");
        }


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


