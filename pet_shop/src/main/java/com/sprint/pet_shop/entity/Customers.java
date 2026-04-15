package com.sprint.pet_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Customers {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long customerId;
@Column(nullable=false,length=50)
private String firstName;
@Column(nullable=false,length=50)
private String lastName;
@Column(length=100)
private String email;
@Column(length=20)
private String phoneNumber;
@ManyToOne
@JoinColumn(name="address_id")
private Addresses addresses;
public long getCustomerId() {
	return customerId;
}
public void setCustomerId(long customerId) {
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
public Addresses getAddress() {
	return addresses;
}
public void setAddress(Addresses addresses) {
	this.addresses =addresses;
}

}
