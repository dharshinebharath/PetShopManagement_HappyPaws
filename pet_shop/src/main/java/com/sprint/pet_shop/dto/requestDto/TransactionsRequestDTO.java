package com.sprint.pet_shop.dto.requestDto;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint.pet_shop.entity.TransactionStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TransactionsRequestDTO {

    @NotNull(message = "Transaction date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd") // ✅ ensures correct JSON format
    private Date transactionDate;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    @NotNull(message = "Pet ID cannot be null")
    private Long petId;

    @NotNull(message = "Transaction status cannot be null")
    private TransactionStatus transactionStatus;
    // ================= GETTERS & SETTERS =================

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

}