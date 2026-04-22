package com.sprint.pet_shop.entity;



import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pets")

public class Pets {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "pet_id")
	    private Long pet_id;

	    @NotBlank(message = "Pet name cannot be empty")
	    @Column(length = 100)
	    private String name;

	    @NotBlank(message = "Breed cannot be empty")
	    @Column(length = 100)
	    private String breed;

	    @NotNull(message = "Age cannot be null")
	    private Integer age;

	    @NotNull(message = "Price cannot be null")
	    @Column(precision = 10, scale = 2)
	    private BigDecimal price;

	    @NotBlank(message = "Description cannot be empty")
	    @Column(columnDefinition = "TEXT",length = 255)
	    private String description;

	    @NotBlank(message = "Image URL cannot be empty")
	    private String image_url;
	    

//		@NotNull(message = "Category cannot be null")
//		@Column(nullable = false)
//	    private String category;


	    @NotNull(message = "Category cannot be null")
	    @ManyToOne
	    @JoinColumn(name = "category_id", nullable = false)
	    private PetCategories category;

	    // 🔗 Grooming
	    @ManyToMany
	    @JoinTable(
	        name = "pet_grooming_relationship",
	        joinColumns = @JoinColumn(name = "pet_id"),
	        inverseJoinColumns = @JoinColumn(name = "service_id")
	    )
	    private List<GroomingServices> groomingServices;

	    // 🔗 Vaccination
	    @ManyToMany
	    @JoinTable(
	        name = "pet_vaccination_relationship",
	        joinColumns = @JoinColumn(name = "pet_id"),
	        inverseJoinColumns = @JoinColumn(name = "vaccination_id")
	    )
	    
	    private List<Vaccinations> vaccinations;

	    // 🔗 Food
	    @ManyToMany
	    @JoinTable(
	        name = "pet_food_relationship",
	        joinColumns = @JoinColumn(name = "pet_id"),
	        inverseJoinColumns = @JoinColumn(name = "food_id")
	    )
	    private List<PetFood> foods;

	    // 🔗 Employees
	    @ManyToMany
	    @JoinTable(
	        name = "employee_pet_relationship",
	        joinColumns = @JoinColumn(name = "pet_id"),
	        inverseJoinColumns = @JoinColumn(name = "employee_id")
	    )
	    @JsonIgnore
	    private List<Employees> employees;

	    // 🔗 Suppliers
	    @ManyToMany
	    @JoinTable(
	        name = "pet_supplier_relationship",
	        joinColumns = @JoinColumn(name = "pet_id"),
	        inverseJoinColumns = @JoinColumn(name = "supplier_id")
	    )
	    private List<Supplier> suppliers;

	    // 🔗 Transactions
	    @OneToMany(mappedBy = "pet")
	    @JsonIgnore
	    private List<TransactionsEntity> transactions;

		public Long getPet_id() {
			return pet_id;
		}

		public void setPet_id(Long pet_id) {
			this.pet_id = pet_id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBreed() {
			return breed;
		}

		public void setBreed(String breed) {
			this.breed = breed;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImage_url() {
			return image_url;
		}

		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}

		public PetCategories getCategory() {
			return category;
		}

		public void setCategory(PetCategories category) {
			this.category = category;
		}

		public List<GroomingServices> getGroomingServices() {
			return groomingServices;
		}

		public void setGroomingServices(List<GroomingServices> groomingServices) {
			this.groomingServices = groomingServices;
		}

		public List<Vaccinations> getVaccinations() {
			return vaccinations;
		}

		public void setVaccinations(List<Vaccinations> vaccinations) {
			this.vaccinations = vaccinations;
		}

		public List<PetFood> getFoods() {
			return foods;
		}

		public void setFoods(List<PetFood> foods) {
			this.foods = foods;
		}

		public List<Employees> getEmployees() {
			return employees;
		}

		public void setEmployees(List<Employees> employees) {
			this.employees = employees;
		}

		public List<Supplier> getSuppliers() {
			return suppliers;
		}

		public void setSuppliers(List<Supplier> suppliers) {
			this.suppliers = suppliers;
		}

		public List<TransactionsEntity> getTransactions() {
			return transactions;
		}

		public void setTransactions(List<TransactionsEntity> transactions) {
			this.transactions = transactions;
		}
	    
	
	
	
	
}
