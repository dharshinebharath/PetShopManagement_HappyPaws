package com.sprint.pet_shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)

public enum TransactionStatus {
	 Success,
	 Failed,
	
}
