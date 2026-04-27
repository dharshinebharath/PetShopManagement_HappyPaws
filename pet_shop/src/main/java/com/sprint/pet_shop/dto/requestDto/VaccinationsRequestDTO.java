package com.sprint.pet_shop.dto.requestDto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for managing vaccination records.
 * Receives the name, description, and cost of a vaccination from the client request.
 */
public class VaccinationsRequestDTO {

	    // Validation
		@NotNull(message = "Vaccination name cannot be null")
		@Size(min = 2, max = 100, message = "Vaccination name must be between 2 and 100 characters")
	    private String name;

	    @NotBlank(message = "Description cannot be empty")
	    @Size(min = 2, max = 255, message = "Description must be between 2 and 255 characters")
	    private String description;

	    @Positive
		@Digits(integer = 8, fraction = 2, message = "Price must have max 8 digits and 2 decimal places")
	    private BigDecimal price;

		// Default value
	    private boolean available = true;

		// Getters and setters
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public boolean isAvailable() {
			return available;
		}
		public void setAvailable(boolean available) {
			this.available = available;
		}
	    
	    
}
