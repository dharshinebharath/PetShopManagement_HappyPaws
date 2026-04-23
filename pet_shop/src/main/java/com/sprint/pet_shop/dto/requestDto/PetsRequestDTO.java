package com.sprint.pet_shop.dto.requestDto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PetsRequestDTO {

	 @NotBlank(message = "Pet name cannot be empty")
	    private String name;

	    @NotBlank(message = "Breed cannot be empty")
	    private String breed;

	    @NotNull(message = "Age cannot be null")
	    private Integer age;

	    @NotNull(message = "Price cannot be null")
	    private BigDecimal price;

	    @NotBlank(message = "Description cannot be empty")
	    private String description;

	    @NotBlank(message = "Image URL cannot be empty")
	    private String image_url;

	    @NotNull(message = "Category ID cannot be null")
	    private Long category_id;

	    // Relationships (IDs only)
	    private List<Long> groomingServiceIds;
	    private List<Long> vaccinationIds;
	    private List<Long> foodIds;
	    private List<Long> employeeIds;
	    private List<Long> supplierIds;

	    // Getters & Setters

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public String getBreed() { return breed; }
	    public void setBreed(String breed) { this.breed = breed; }

	    public Integer getAge() { return age; }
	    public void setAge(Integer age) { this.age = age; }

	    public BigDecimal getPrice() { return price; }
	    public void setPrice(BigDecimal price) { this.price = price; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public String getImage_url() {
			return image_url;
		}
		public void setImage_url(String image_url) {
			this.image_url = image_url;
		}
		public Long getCategory_id() {
			return category_id;
		}
		public void setCategory_id(Long category_id) {
			this.category_id = category_id;
		}
		public List<Long> getGroomingServiceIds() { return groomingServiceIds; }
	    public void setGroomingServiceIds(List<Long> groomingServiceIds) { this.groomingServiceIds = groomingServiceIds; }

	    public List<Long> getVaccinationIds() { return vaccinationIds; }
	    public void setVaccinationIds(List<Long> vaccinationIds) { this.vaccinationIds = vaccinationIds; }

	    public List<Long> getFoodIds() { return foodIds; }
	    public void setFoodIds(List<Long> foodIds) { this.foodIds = foodIds; }

	    public List<Long> getEmployeeIds() { return employeeIds; }
	    public void setEmployeeIds(List<Long> employeeIds) { this.employeeIds = employeeIds; }

	    public List<Long> getSupplierIds() { return supplierIds; }
	    public void setSupplierIds(List<Long> supplierIds) { this.supplierIds = supplierIds; }
	}
