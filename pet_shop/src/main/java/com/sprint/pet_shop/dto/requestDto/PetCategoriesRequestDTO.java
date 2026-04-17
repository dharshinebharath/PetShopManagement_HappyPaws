package com.sprint.pet_shop.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public class PetCategoriesRequestDTO {

	@NotBlank(message = "Category name cannot be empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
