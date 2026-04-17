package com.sprint.pet_shop.dto.requestDto;

import java.math.BigDecimal;
import java.util.List;

public class PetsRequestDTO {

    private String name;
    private String breed;
    private Integer age;
    private BigDecimal price;
    private String description;
    private String image_url;

    // CATEGORY (mandatory)
    private Long category_id;

    // RELATIONSHIP IDS
    private List<Long> groomingServiceIds;
    private List<Long> vaccinationIds;
    private List<Long> foodIds;
    private List<Long> employeeIds;
    private List<Long> supplierIds;

    // Getters and Setters

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

    public String getImageUrl() { return image_url; }
    public void setImageUrl(String image_url) { this.image_url = image_url; }

    public Long getCategoryId() { return category_id; }
    public void setCategoryId(Long category_id) { this.category_id = category_id; }

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