package com.sprint.pet_shop.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public class AddressesRequestDTO{
	 @NotBlank(message = "Street cannot be empty")
	    private String street;
	    @NotBlank(message = "City cannot be empty")
	    private String city;
	    @NotBlank(message = "State cannot be empty")
	    private String state;
	    @NotBlank(message = "Zip code cannot be empty")
	    private String zipCode;
	    public String getStreet() {
	        return street;
	    }
	    public void setStreet(String street) {
	        this.street = street;
	    }
	    public String getCity() {
	        return city;
	    }
	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }
	    public String getZipCode() {
	        return zipCode;
	    }
	    public void setZipCode(String zipCode) {
	        this.zipCode = zipCode;
	    }
}
