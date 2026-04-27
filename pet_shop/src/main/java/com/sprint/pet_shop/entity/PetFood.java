package com.sprint.pet_shop.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Represents the different types of pet food we sell.
 * This entity tracks inventory details like the food name, brand, 
 * type, current stock quantity, and price.
 */
@Entity
@Table(name="pet_food")
public class PetFood {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="food_id")
	private Long foodId;
	
	@NotNull(message = "Food name cannot be null")
	@Size(min = 2, max = 255, message = "Food name must be between 2 and 255 characters")
	private String name;
	
    @NotBlank(message = "Brand cannot be empty")
	@Size(min = 2, max = 100, message = "Brand must be between 2 and 100 characters")
	private String brand;
	
    @NotBlank(message = "Type cannot be empty")
	@Size(min = 2, max = 50, message = "Type must be between 2 and 50 characters")
	private String type;
	
	@Positive
	private Integer quantity;
	
	@Positive
@Digits(integer = 8, fraction = 2, message = "Price must have max 8 digits and 2 decimal places")
	private BigDecimal price;
    
    @ManyToMany(mappedBy = "foods")
    private List<Pets> pets;

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

	
	
}
