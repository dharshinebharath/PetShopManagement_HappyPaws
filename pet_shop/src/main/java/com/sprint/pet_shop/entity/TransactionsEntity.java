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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "transactions") 
public class TransactionsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "transaction_id")
	private Long transactionId;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@NotNull(message = "Customer ID cannot be null")
	@JsonProperty("customerId")
	@Column(name = "customer_id",nullable = false)
	private Integer customerId;
	
	@NotNull(message = "Pet ID cannot be null")
	@JsonProperty("petId")
	@Column(name = "pet_id",nullable = false)
	private Integer petId;
	
	@NotNull(message = "Transaction date cannot be null")
	@Column(name = "transaction_date",nullable = false)
	private Date transactionDate; 
	
	@NotNull(message = "Amount cannot be null")
    @Column(nullable = false)
	private double amount;
	
	@NotNull(message = "Transaction status cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_status",nullable = false)
	    private TransactionStatus transactionStatus;

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
	}

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

}
