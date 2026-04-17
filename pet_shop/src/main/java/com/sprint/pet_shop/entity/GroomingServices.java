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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(name="grooming_services")
public class GroomingServices {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="service_id")
	private Long serviceId;

    @NotBlank(message = "Service name cannot be empty")
	@Column(length=100)
	private String name;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
    @NotNull(message = "Price cannot be null")
	@Column(precision =10, scale=2)
	private BigDecimal price;
	
    @NotNull(message = "Availability cannot be null")
	private boolean available=true;

    @ManyToMany(mappedBy = "groomingServices")
    private List<Pets> pets;

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
