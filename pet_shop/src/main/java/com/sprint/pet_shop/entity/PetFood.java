package com.sprint.pet_shop.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="pet_food")
public class PetFood {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="food_id")
    private Long foodId;

    @NotBlank(message = "Food name cannot be empty")
    @Column(length=255)
    private String name;

    @NotBlank(message = "Brand cannot be empty")
    @Column(length=100)
    private String brand;

    @NotBlank(message = "Type cannot be empty")
    @Column(length=50)
    private String type;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Price cannot be null")
    @Column(precision=10, scale=2)
    private BigDecimal price;

    @ManyToMany(mappedBy = "foods", cascade = CascadeType.ALL)
    private List<Pets> pets;

    // Getters and Setters
    public Long getFoodId() { return foodId; }
    public void setFoodId(Long foodId) { this.foodId = foodId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public List<Pets> getPets() { return pets; }
    public void setPets(List<Pets> pets) { this.pets = pets; }
}
