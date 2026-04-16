package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.service.TransactionsService;

@RestController
@RequestMapping("/api/v1/transactions")

public class TransactionsController {
	@Autowired
	private TransactionsService transactionsService;
	
	
	public TransactionsEntity saveTransactionsEntity(@RequestBody TransactionsEntity transactionsEntity) {
		return transactionsService.saveTransactionsEntity(transactionsEntity);
	}
	@PostMapping
	public List<TransactionsEntity> saveAll(@RequestBody List<TransactionsEntity> transactionsEntity){
		return transactionsService.TransactionsEntityAll(transactionsEntity);
	}
	//getall
	 @GetMapping
	    public List<TransactionsEntity> getAll() {
	        return transactionsService.getAllTransactions();
	    }
	 //get by id
	 @GetMapping("/{id}")
	    public TransactionsEntity getById(@PathVariable Long id) {
	        return transactionsService.getTransactionById(id);
	    }
	 @PutMapping("/{id}")
	 public TransactionsEntity updateTransaction(@PathVariable Long id,
	                                             @RequestBody TransactionsEntity transactionsEntity) {
	     return transactionsService.updateTransaction(id, transactionsEntity);
	 }

	    // DELETE
	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        return transactionsService.deleteTransaction(id);
	    }
	 

}
