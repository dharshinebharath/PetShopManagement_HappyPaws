package com.sprint.pet_shop.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for incoming pet category data.
 * Simple wrapper used to receive new categories like "Dogs" or "Birds" 
 * from the API clients.
 */
public class PetCategoriesRequestDTO {

    @NotNull(message = "Category name cannot be null")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
