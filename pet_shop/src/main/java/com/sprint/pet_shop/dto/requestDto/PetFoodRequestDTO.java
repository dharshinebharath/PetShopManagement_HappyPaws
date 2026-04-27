package com.sprint.pet_shop.dto.requestDto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object carrying pet food inventory details.
 * Contains information like brand, type, quantity, and price from an incoming request.
 */
public class PetFoodRequestDTO {

	@Size(min = 2, max = 255, message = "Food name must be between 2 and 255 characters")
	@NotNull(message = "Food name cannot be null")
    private String name;
    @NotBlank(message = "Brand cannot be empty")
	@Size(min = 2, max = 100, message = "Brand name must be between 2 and 100 characters")
    private String brand;
    @NotBlank(message = "Type cannot be empty")
	@Size(min = 2, max = 50, message = "Type name must be between 2 and 50 characters")
    private String type;
	@Positive
    private int quantity;
	@Digits(integer = 8, fraction = 2, message = "Price must have max 8 digits and 2 decimal places")
	@Positive
    private BigDecimal price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
    
}
