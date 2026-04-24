package com.sprint.pet_shop.dto.responseDto;


import java.math.BigDecimal;

public class GroomingServicesResponseDTO {

	 private Long serviceId;
	    private String name;
	    private String description;
	    private BigDecimal price;
	    private boolean available;

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

}
