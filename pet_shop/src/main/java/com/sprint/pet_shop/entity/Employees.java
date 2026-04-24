package com.sprint.pet_shop.entity;



import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "employees") 

public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(length = 50, name = "first_name")
    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Column(length = 50, name = "last_name")
    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Column(length = 50)
    @NotNull(message = "Position cannot be null")
    @NotBlank(message = "Position cannot be empty")
    private String position;


	@Column(length = 50, name = "hire_date")
    @NotNull(message = "Hire date cannot be null")
    private LocalDate hireDate;

    @Column(length = 20, name = "phone_number")
    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @Column(length = 100)
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @ManyToMany(mappedBy = "employees")
    private List<Pets> pets;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses address;

	public Addresses getAddress() {
		return address;
	}

	public void setAddress(Addresses address) {
		this.address = address;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
   
	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pets> getPets() {
		return pets;
	}

	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}

    
    
}
