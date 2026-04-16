package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.GroomingServicesRepository;
import com.sprint.pet_shop.service.interfaces.GroomingServicesInterface;

import jakarta.validation.Valid;

@Service
public class GroomingServicesService implements GroomingServicesInterface {

	@Autowired
	private GroomingServicesRepository groomingServicesRepository;
	
	public List<GroomingServices> saveAllGroomingServices(List<GroomingServices> groomingServices)
	{
		for (GroomingServices s : groomingServices) {

		    if (s.getPrice().doubleValue() < 0) {
		        throw new InvalidDataException(
		            "Invalid price for service: " + s.getName());
		    }
		    
		    if (groomingServicesRepository.existsByName(s.getName())) {
		        throw new DuplicateResourceException(
		            "Service already exists: " + s.getName());
		    }
		}
		return groomingServicesRepository.saveAll(groomingServices);
	}

	@Override
	public List<GroomingServices> getAllGroomingServices() {
		return groomingServicesRepository.findAll();
	}

	@Override
	public GroomingServices getGroomingServiceById(long id) {
		return groomingServicesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Grooming Service Not Found with id: "+id)); 
	}
	
	@Override
	public void deleteGroomingServiceById(long id)
	{
		GroomingServices existing=groomingServicesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Grooming Services Not Found with id:"+id));
		groomingServicesRepository.delete(existing);
	}

	@Override
	public GroomingServices updateGroomingService(long id, @Valid GroomingServices service) {
		GroomingServices existing = groomingServicesRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Grooming Services Not Found with id:"+id));

		if (service.getPrice().doubleValue() < 0) {
		    throw new InvalidDataException("Price must be positive");
		}
	    existing.setName(service.getName());
	    existing.setDescription(service.getDescription());
	    existing.setPrice(service.getPrice());
	    existing.setAvailable(service.getAvailable());

	    return groomingServicesRepository.save(existing);
	}
}
