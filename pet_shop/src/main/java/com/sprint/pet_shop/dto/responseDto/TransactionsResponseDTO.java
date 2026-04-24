package com.sprint.pet_shop.dto.responseDto;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class TransactionsResponseDTO {

    private Long transactionId;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private Long customerId;
    private Long petId;
    private String transactionStatus;
    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }

    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }

    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }
}

