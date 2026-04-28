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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a specific category or classification of pets (e.g., Dog, Cat, Bird).
 * This helps us organize our pets inventory into logical groupings.
 */
@Entity
@Table(name = "pet_categories")
public class PetCategories {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "category_id")
	    private Long categoryId;

	    @NotNull(message = "Category name cannot be null")
		@Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
	    private String name;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "category")
	    private List<Pets> pets;

		public Long getCategory_id() {
			return categoryId;
		}

		public void setCategory_id(Long category_id) {
			this.categoryId = category_id;
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
