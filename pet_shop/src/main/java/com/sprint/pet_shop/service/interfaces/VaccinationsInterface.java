package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.Vaccinations;

import jakarta.validation.Valid;

public interface VaccinationsInterface {

	List<Vaccinations> saveAllVaccinations(@Valid List<Vaccinations> vaccinations);

	List<Vaccinations> getAllVaccinations();

	Vaccinations getVaccinationsById(long id);

	void deleteVaccinationsById(long id);


	Vaccinations updateVaccinationById(long id, @Valid Vaccinations vaccination);

}
