package com.sprint.pet_shop.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "transactions") 
public class TransactionsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@NotNull(message = "Transaction date cannot be null")
	@Column(name = "transaction_date",nullable = false)
	private Date transactionDate; 
	
	@NotNull(message = "Amount cannot be null")
    @Column(nullable = false)
	private double amount;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pets pet;
	
	@NotNull(message = "Transaction status cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status",nullable = false)
	    private TransactionStatus transactionStatus;


	

	public Long getTransactionId() {
		return transactionId;
	}
	
	 public void setTransactionId(Long transactionId) {
		 this.transactionId = transactionId;
	 }
	 
	 public Date getTransactionDate() {
		 return transactionDate;
	 }
	 
	 public void setTransactionDate(Date transactionDate) {
		 this.transactionDate = transactionDate;
	 }
	 
	 public TransactionStatus getTransactionStatus() {
		 return transactionStatus;
	 }
	 
	 public void setTransactionStatus(TransactionStatus transactionStatus) {
		 this.transactionStatus = transactionStatus;
	 }
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Pets getPet() {
		return pet;
	}
	public void setPet(Pets pet) {
		this.pet = pet;
	}
	
	

}
