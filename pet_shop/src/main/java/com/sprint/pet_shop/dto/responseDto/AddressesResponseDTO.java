package com.sprint.pet_shop.dto.responseDto;

/**
 * Data Transfer Object for sending address details back to the client.
 * This ensures we only expose necessary information when a user requests address data.
 */
public class AddressesResponseDTO {
	private Long addressId;
    private String street;
    private String city;
    private String state;
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
