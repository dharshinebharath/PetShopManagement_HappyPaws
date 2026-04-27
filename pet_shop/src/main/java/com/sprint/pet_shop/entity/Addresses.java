package com.sprint.pet_shop.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Addresses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long addressId;
	
	@NotBlank(message = "Street cannot be empty")
	@Size(min = 2, max = 255, message = "Street must be between 2 and 255 characters")
	private String street;
	
	@NotBlank(message = "City cannot be empty")
	@Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
	private String city;

	@NotBlank(message = "State cannot be empty")
	@Size(min = 2, max = 50, message = "State must be between 2 and 50 characters")
	private String state;
	
	@Pattern(regexp = "[0-9]{5}", message = "Zip code must be 5 digits")
	private String zipCode;

	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
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
