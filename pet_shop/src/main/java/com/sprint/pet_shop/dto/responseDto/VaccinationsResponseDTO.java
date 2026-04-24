package com.sprint.pet_shop.dto.responseDto;

import java.math.BigDecimal;

public class VaccinationsResponseDTO {

    private Long vaccinationId;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
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
    
}
