package com.sprint.pet_shop.dto.responseDto;

import java.math.BigDecimal;
import java.util.List;

public class PetsResponseDTO {

	private Long pet_id;
    private String name;
    private String breed;
    private Integer age;
    private BigDecimal price;
    private String description;
    private String image_url;

    // Category info
    private Long category_id;
    private String categoryName;

    // Relationships (IDs only)
    private List<Long> groomingServiceIds;
    private List<Long> vaccinationIds;
    private List<Long> foodIds;
    private List<Long> employeeIds;
    private List<Long> supplierIds;

    // Getters & Setters

    public String getName() { return name; }
    public Long getPet_id() {
		return pet_id;
	}
	public void setPet_id(Long pet_id) {
		this.pet_id = pet_id;
	}
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
	public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

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
