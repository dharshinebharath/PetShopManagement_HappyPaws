package com.sprint.pet_shop.dto.responseDto;

public class CustomerResponseDTO{
	private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressesResponseDTO address;
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public AddressesResponseDTO getAddress() {
        return address;
    }
    public void setAddress(AddressesResponseDTO address) {
        this.address = address;
    }
}
