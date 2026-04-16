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
	@Column(name = "customer_id")
	private Integer customerId; 

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
	@JsonProperty("petId")
	@Column(name = "pet_id")
	
	private Integer petId;

	@Column(name = "transaction_date")
	private Date transactionDate; 
	private double amount;
	 @Enumerated(EnumType.STRING)
	    @Column(name = "transaction_status")
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

}
