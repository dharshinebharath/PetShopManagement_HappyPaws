package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.entity.Vaccinations;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.EmployeesRepository;
import com.sprint.pet_shop.repository.GroomingServicesRepository;
import com.sprint.pet_shop.repository.PetCategoriesRepository;
import com.sprint.pet_shop.repository.PetFoodRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.repository.SupplierRepository;
import com.sprint.pet_shop.repository.VaccinationsRepository;
import com.sprint.pet_shop.service.interfaces.PetsInterface;

@Service
public class PetsService implements PetsInterface {
	
	@Autowired
	private PetsRepository petsRepository;
	
	 @Autowired
	    private PetCategoriesRepository categoryRepository;
	 @Autowired
	 private GroomingServicesRepository groomingRepo;

	 @Autowired
	 private PetFoodRepository foodRepo;

	 @Autowired
	 private VaccinationsRepository vaccinationRepo;

	 @Autowired
	 private EmployeesRepository employeeRepo;

	 @Autowired
	 private SupplierRepository supplierRepo;
	@Override
	public List<Pets> getAllPets()
	{
		return petsRepository.findAll();
	}
	
	@Override
	public List<Pets> addAllPets(List<Pets> pets)
	{
		 for (Pets pet : pets) {

	            if (pet.getName() == null || pet.getName().isBlank()) {
	                throw new InvalidDataException("Pet name cannot be empty");
	            }

	            if (pet.getPrice() == null || pet.getPrice().doubleValue() < 0) {
	                throw new InvalidDataException("Price must be positive");
	            }

	            if (pet.getAge() == null || pet.getAge() < 0) {
	                throw new InvalidDataException("Age cannot be negative");
	            }
	            if (pet.getCategory() == null || pet.getCategory().getCategory_id() == null) {
	                throw new InvalidDataException("Category ID cannot be null");
	            }

	            // CATEGORY

	            Long categoryId = pet.getCategory().getCategory_id();

	            PetCategories category = categoryRepository.findById(categoryId)
	                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));

	            pet.setCategory(category);
	            
	            // ---------------- GROOMING ----------------
	            if (pet.getGroomingServices() != null) {
	                List<Long> ids = pet.getGroomingServices()
	                        .stream()
	                        .map(GroomingServices::getServiceId)
	                        .toList();

	                pet.setGroomingServices(groomingRepo.findAllById(ids));
	            }

	            // ---------------- FOOD ----------------
	            if (pet.getFoods() != null) {
	                List<Long> ids = pet.getFoods()
	                        .stream()
	                        .map(PetFood::getFoodId)
	                        .toList();

	                pet.setFoods(foodRepo.findAllById(ids));
	            }

	            // ---------------- VACCINATIONS ----------------
	            if (pet.getVaccinations() != null) {
	                List<Long> ids = pet.getVaccinations()
	                        .stream()
	                        .map(Vaccinations::getVaccinationId)
	                        .toList();

	                pet.setVaccinations(vaccinationRepo.findAllById(ids));
	            }

	            // ---------------- EMPLOYEES ----------------
	            if (pet.getEmployees() != null) {
	                List<Long> ids = pet.getEmployees()
	                        .stream()
	                        .map(Employees::getEmployeeId)
	                        .toList();

	                pet.setEmployees(employeeRepo.findAllById(ids));
	            }

	            // ---------------- SUPPLIERS ----------------
	            if (pet.getSuppliers() != null) {
	                List<Long> ids = pet.getSuppliers()
	                        .stream()
	                        .map(Supplier::getSupplierId)
	                        .toList();

	                pet.setSuppliers(supplierRepo.findAllById(ids));
	            }
	            
	        }
		return petsRepository.saveAll(pets);
	}
	
	@Override
	public Pets getPetById(long id)
	{
		return petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }
	
	@Override
	public Pets updatePets(long id, Pets pets) {

		 Pets existing = petsRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));

	        if (pets.getName() == null || pets.getName().isBlank()) {
	            throw new InvalidDataException("Pet name cannot be empty");
	        }

	        if (pets.getPrice() == null || pets.getPrice().doubleValue() < 0) {
	            throw new InvalidDataException("Price must be positive");
	        }

	        if (pets.getAge() == null || pets.getAge() < 0) {
	            throw new InvalidDataException("Age cannot be negative");
	        }
	    existing.setName(pets.getName());
	    existing.setBreed(pets.getBreed());
	    existing.setAge(pets.getAge());
	    existing.setDescription(pets.getDescription());
	    existing.setPrice(pets.getPrice());
	    existing.setImage_url(pets.getImage_url());

	    return petsRepository.save(existing);
	}
	
	@Override
	public void deletePets(long id)
	{
		Pets existing = petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));

        petsRepository.delete(existing);
	}
}
