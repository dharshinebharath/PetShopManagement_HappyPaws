package com.sprint.pet_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.entity.Vaccinations;
import com.sprint.pet_shop.service.VaccinationsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vaccinations")
public class VaccinationsController {

	@Autowired 
	private VaccinationsService vaccinationService;
	
	@PostMapping
	public List<Vaccinations> saveAllVaccinations(@Valid @RequestBody List<Vaccinations> vaccinations)
	{
		return vaccinationService.saveAllVaccinations(vaccinations);
	}
	
	@GetMapping
	public List<Vaccinations>  getAllVaccinations()
	{
		return vaccinationService.getAllVaccinations();
	}
	
	@GetMapping("/{vaccinationId}")
	public Vaccinations getVaccinationsById(@PathVariable long vaccinationId)
	{
		return vaccinationService.getVaccinationsById(vaccinationId);
	}
	
	@DeleteMapping("/{vaccinationId}")
	public String deleteVaccinationsById(@PathVariable long vaccinationId)
	{
		vaccinationService.deleteVaccinationsById(vaccinationId);
		return "Vaccination Deleted Successfully";
	}
	
	@PutMapping("/{vaccinationId}")
	public Vaccinations updateVaccinationsById(@PathVariable long vaccinationId, @Valid @RequestBody Vaccinations vaccination)
	{
		return vaccinationService.updateVaccinationById(vaccinationId, vaccination);
	}
}
