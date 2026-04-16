package com.sprint.pet_shop.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pet_categories")
public class PetCategories {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "category_id")
	    private Long category_id;

	    @NotBlank(message = "Category name cannot be empty")
	    @Column(length = 100)
	    private String name;
	    
	    @OneToMany(mappedBy = "category")
	    @JsonIgnore
	    private List<Pets> pets;
	    
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pets> getPets() {
		return pets;
	}
	public void setPets(List<Pets> pets) {
		this.pets = pets;
	}
	
	
	
}
