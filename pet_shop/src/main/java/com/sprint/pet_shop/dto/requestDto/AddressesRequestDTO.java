package com.sprint.pet_shop.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

	/**
	 * Data Transfer Object for capturing address details from incoming requests.
	 * We use this to validate and transport the physical address information 
	 * before saving it to the database.
	 */
public class AddressesRequestDTO{
	 @NotBlank(message = "Street cannot be empty")
	    private String street;
		@Size(min = 2, max = 255, message = "Street must be between 2 and 255 characters")
	    @NotBlank(message = "City cannot be empty")
		@Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
	    private String city;
		
	    @NotBlank(message = "State cannot be empty")
		@Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
	    private String state;
		@NotBlank(message = "Zip code cannot be empty")
		@Pattern(regexp = "[0-9]{5}", message = "Zip code must be 5 digits")
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
