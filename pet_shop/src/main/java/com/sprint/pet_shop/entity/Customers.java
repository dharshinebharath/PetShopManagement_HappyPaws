// This entity maps the persisted data for customers.
package com.sprint.pet_shop.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Customers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;
	
	@NotNull(message = "First name cannot be null")
	@NotBlank(message = "First name cannot be empty")
	@Column(length=50)
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
	@NotBlank(message = "Last name cannot be empty")
	@Column(length=50)
	private String lastName;
	
	@Column(length=100)
	private String email;
	
	@Column(length=20)
	private String phoneNumber;
	
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses address;

    @OneToMany(mappedBy = "customer")
    private List<TransactionsEntity> transactions;

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

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}

	public List<TransactionsEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionsEntity> transactions) {
		this.transactions = transactions;
	}
	
	

}
