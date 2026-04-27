package com.sprint.pet_shop.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents a purchase or service transaction made by a customer.
 * This entity links the customer, the pet involved, the date of transaction, 
 * the monetary amount, and the current status (e.g., SUCCESS or FAILED).
 */
@Entity
@Table(name = "transactions")
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @NotNull(message = "Transaction date cannot be null")
    private LocalDate transactionDate;

    @NotNull(message = "Amount cannot be null")
    @Digits(integer = 8, fraction = 2, message = "Price must have max 8 digits and 2 decimal places")
	@Positive
    private BigDecimal amount;

    @ManyToOne
    @NotNull(message = "Customer ID cannot be null")
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne
    @NotNull(message = "Pet ID cannot be null")
    @JoinColumn(name = "pet_id")
    private Pets pet;

    @NotNull(message = "Transaction status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status", nullable = false)
    private TransactionStatus transactionStatus;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate date) {
        this.transactionDate = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Pets getPet() {
        return pet;
    }

    public void setPet(Pets pet) {
        this.pet = pet;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}

