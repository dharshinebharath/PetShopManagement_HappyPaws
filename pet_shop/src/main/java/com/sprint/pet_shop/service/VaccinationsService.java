package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.entity.Vaccinations;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.VaccinationsRepository;
import com.sprint.pet_shop.service.interfaces.VaccinationsInterface;

@Service
public class VaccinationsService implements VaccinationsInterface {

	@Autowired
	private VaccinationsRepository vaccinationsRepository;
	
	@Override
	public List<Vaccinations> saveAllVaccinations(List<Vaccinations> vaccinations) {
		for (Vaccinations v : vaccinations) {

		    if (v.getPrice().doubleValue() < 0) {
		        throw new InvalidDataException(
		            "Invalid price for vaccination: " + v.getName());
		    }
		    
		    if (vaccinationsRepository.existsByName(v.getName())) {
		        throw new DuplicateResourceException(
		            "Vaccination already exists: " + v.getName());
		    }
		}
		return vaccinationsRepository.saveAll(vaccinations);
	}
	
	@Override	
	public List<Vaccinations> getAllVaccinations()
	{
		return vaccinationsRepository.findAll();
	}
	
	@Override
	public Vaccinations getVaccinationsById(long id)
	{
		return vaccinationsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vaccination Not Found with id:"+id));
	}
	
	@Override	
	public void deleteVaccinationsById(long id)
	{
		Vaccinations existing=vaccinationsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vaccination Not Found with id:"+id));
		vaccinationsRepository.delete(existing);
	}
	
	@Override
	public Vaccinations updateVaccinationById(long id, Vaccinations vaccination) {
		Vaccinations existing=vaccinationsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Vaccination Not Found with id:"+id));
		
		if (vaccination.getPrice().doubleValue() < 0) {
		    throw new InvalidDataException("Price must be positive");
		}
		existing.setName(vaccination.getName());
		existing.setDescription(vaccination.getDescription());
		existing.setPrice(vaccination.getPrice());
		existing.setAvailable(vaccination.isAvailable());
		
		return vaccinationsRepository.save(existing);
	}



	
}
