package com.sprint.pet_shop.entity;

/**
 * Defines the possible states a transaction can be in.
 * SUCCESS indicates a completed payment, while FAILED indicates an issue.
 */
public enum TransactionStatus {
    SUCCESS,
    FAILED,
    PENDING;

    @com.fasterxml.jackson.annotation.JsonCreator
    public static TransactionStatus from(String value) {
        return TransactionStatus.valueOf(value.toUpperCase());
    }
}
