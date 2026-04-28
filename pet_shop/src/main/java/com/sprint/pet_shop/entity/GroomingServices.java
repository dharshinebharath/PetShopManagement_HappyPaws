package com.sprint.pet_shop.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.Size;
 

//   Represents the grooming services available at the Pet Shop.
//   This entity stores details like the service name, description, 
//   price, and whether the service is currently available to customers.
 
@Entity
@Table(name="grooming_services")
public class GroomingServices {

	// Grooming Service details
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_id")
	private Long serviceId;

	@NotNull(message = "Service name cannot be null")
	@Size(min = 2, max = 100, message = "Service name must be between 2 and 100 characters")
	private String name;
	
    @NotBlank(message = "Description cannot be empty")
	@Size(min = 2, max = 255, message = "Description must be between 2 and 255 characters")
	private String description;
	
	@NotNull
    @Digits(integer = 8, fraction = 2, message = "Price must have max 8 digits and 2 decimal places")
	private BigDecimal price;
	
    
	private boolean available = true;

    @ManyToMany(mappedBy = "groomingServices", cascade = CascadeType.ALL)
	private List<Pets> pets;

	// Getters and setters
	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
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
