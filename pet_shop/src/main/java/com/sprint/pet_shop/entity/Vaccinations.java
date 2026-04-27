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

//  Represents a vaccination or medical treatment available for pets.
//  This entity tracks the name, description, price, and availability of the vaccine.

@Entity
@Table(name="vaccinations")
public class Vaccinations {

	// Vaccination details
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vaccination_id")
	private Long vaccinationId;
	
	@NotNull(message = "Vaccination name cannot be null")
	@Size(min = 2, max = 100, message = "Vaccination name must be between 2 and 50 characters")
	private String name;
	
    @NotBlank(message = "Description cannot be empty")
	@Size(min = 2, max = 255, message = "Description must be between 2 and 255 characters")
	private String description;
	
	@Positive
	@Digits(integer = 8, fraction = 2, message = "Price must have max 8 digits and 2 decimal places")
	private BigDecimal price;
	
	private boolean available = true;

    @ManyToMany(mappedBy = "vaccinations")
    private List<Pets> pets;

	// Getters and setters
	public Long getVaccinationId() {
		return vaccinationId;
	}

	public void setVaccinationId(Long vaccinationId) {
		this.vaccinationId = vaccinationId;
	}

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

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

    
	
	
}
