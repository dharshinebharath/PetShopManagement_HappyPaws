package com.sprint.pet_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.pet_shop.entity.PetCategories;

//  Repository for managing the different categories of pets we have (e.g., Cats, Dogs, Birds).
public interface PetCategoriesRepository extends JpaRepository<PetCategories, Long> {

	 @Query("SELECT p FROM PetCategories p ORDER BY p.categoryId ASC")
	    List<PetCategories> findAllSorted();
}
