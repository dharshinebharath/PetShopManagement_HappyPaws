package com.sprint.pet_shop.dto.responseDto;

import java.util.List;

public class PetCategoriesResponseDTO {

	private Long category_id;
    private String name;

    // List of pets under this category
    private List<Long> pet_id;

    public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public List<Long> getPet_id() {
		return pet_id;
	}

	public void setPet_id(List<Long> pet_id) {
		this.pet_id = pet_id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
}