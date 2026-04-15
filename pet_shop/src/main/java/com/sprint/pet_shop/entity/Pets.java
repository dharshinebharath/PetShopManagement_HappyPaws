package com.sprint.pet_shop.entity;



import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id")
	private long pet_id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, length = 50)
	private String breed;
	private int age;
	@Column(precision = 10, scale = 2)
    private BigDecimal price;
//	private long category_id;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(name = "image_url", length = 255)
	private String image_url;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private PetCategories category;
	public Long getPet_id() {
		return pet_id;
	}
	public void setPet_id(long pet_id) {
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	public long getCategory_id() {
//		return category_id;
//	}
//	public void setCategory_id(long category_id) {
//		this.category_id = category_id;
//	}
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}
