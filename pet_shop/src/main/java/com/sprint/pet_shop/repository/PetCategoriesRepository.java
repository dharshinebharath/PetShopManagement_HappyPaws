// This repository handles database access for pet categories repository.
package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;

public interface PetCategoriesRepository extends JpaRepository<PetCategories, Long> {

	 @Query("SELECT p FROM PetCategories p ORDER BY p.categoryId ASC")
	    List<PetCategories> findAllSorted();
}
