package com.sprint.pet_shop.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Represents a customer in our Pet Shop. 
 * This entity tracks the customer's personal details, contact information, 
 * their associated address, and any transactions they've made with us.
 */
@Entity
public class Customers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long customerId;
	
	@NotNull(message = "First name cannot be null")
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	private String firstName;
	
	@NotNull(message = "Last name cannot be null")
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	private String lastName;
	
	@Email(message = "Invalid email address")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	@Size(min = 2, max = 100, message = "Email must be between 2 and 100 characters")
	private String email;
	
	@Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits")
	@NotBlank(message = "Phone number cannot be empty")
	private String phoneNumber;
	
    @ManyToOne
    @JoinColumn(name = "address_id")
	@NotNull(message="Address cannot be null")
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
