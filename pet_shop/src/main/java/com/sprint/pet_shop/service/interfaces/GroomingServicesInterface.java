package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.entity.GroomingServices;

import jakarta.validation.Valid;

public interface GroomingServicesInterface {

	List<GroomingServices> saveAllGroomingServices(@Valid List<GroomingServices> groomingServices);

	List<GroomingServices> getAllGroomingServices();

	GroomingServices getGroomingServiceById(long id);

	void deleteGroomingServiceById(long id);

	GroomingServices updateGroomingService(long id, @Valid GroomingServices service);

}
