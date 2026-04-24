package com.sprint.pet_shop.entity;

public enum TransactionStatus {
    SUCCESS,
    FAILED,
    PENDING;

    @com.fasterxml.jackson.annotation.JsonCreator
    public static TransactionStatus from(String value) {
        return TransactionStatus.valueOf(value.toUpperCase());
    }
}
