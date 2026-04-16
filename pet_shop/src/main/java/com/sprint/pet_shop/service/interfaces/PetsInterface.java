package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.Pets;

public interface PetsInterface {

	List<Pets> getAllPets();

	List<Pets> addAllPets(List<Pets> pets);

	Pets getPetById(long id);

	Pets updatePets(long id, Pets pets);

	void deletePets(long id);

}
