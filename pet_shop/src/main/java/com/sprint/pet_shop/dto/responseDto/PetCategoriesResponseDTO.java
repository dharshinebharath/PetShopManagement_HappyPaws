// This DTO shapes the request or response data for pet categories response dto.
package com.sprint.pet_shop.dto.responseDto;

import java.util.List;

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
