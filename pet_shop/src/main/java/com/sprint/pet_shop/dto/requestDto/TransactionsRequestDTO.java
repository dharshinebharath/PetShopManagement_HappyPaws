package com.sprint.pet_shop.dto.requestDto;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public class TransactionsRequestDTO {

    @NotNull
    private Date transactionDate;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long customerId;

    @NotNull
    private Long petId;

    @NotNull
    private String transactionStatus;

    // getters & setters
    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }

    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }
}