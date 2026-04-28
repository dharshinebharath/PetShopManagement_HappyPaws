package com.sprint.pet_shop.dto.responseDto;


/**
 * Data Transfer Object for Pet Categories.
 * Provides a clean list of available categories (e.g., Dogs, Cats) to the client.
 */
public class PetCategoriesResponseDTO {

	private Long category_id;
    private String name;

    

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

   
}
