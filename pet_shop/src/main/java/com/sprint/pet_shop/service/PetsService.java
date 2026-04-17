package com.sprint.pet_shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
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
	 private PetsResponseDTO toDto(Pets pet) {

	        PetsResponseDTO dto = new PetsResponseDTO();

	        dto.setPet_id(pet.getPet_id());
	        dto.setName(pet.getName());
	        dto.setBreed(pet.getBreed());
	        dto.setAge(pet.getAge());
	        dto.setPrice(pet.getPrice());
	        dto.setDescription(pet.getDescription());
	        dto.setImage_url(pet.getImage_url());

	        dto.setCategory_id(pet.getCategory().getCategory_id());
	        dto.setCategoryName(pet.getCategory().getName());

	        // Relationships → IDs
	        if (pet.getGroomingServices() != null) {
	            dto.setGroomingServiceIds(
	                pet.getGroomingServices().stream()
	                        .map(GroomingServices::getServiceId)
	                        .toList()
	            );
	        }

	        if (pet.getFoods() != null) {
	            dto.setFoodIds(
	                pet.getFoods().stream()
	                        .map(PetFood::getFoodId)
	                        .toList()
	            );
	        }

	        if (pet.getVaccinations() != null) {
	            dto.setVaccinationIds(
	                pet.getVaccinations().stream()
	                        .map(Vaccinations::getVaccinationId)
	                        .toList()
	            );
	        }

	        if (pet.getEmployees() != null) {
	            dto.setEmployeeIds(
	                pet.getEmployees().stream()
	                        .map(Employees::getEmployeeId)
	                        .toList()
	            );
	        }

	        if (pet.getSuppliers() != null) {
	            dto.setSupplierIds(
	                pet.getSuppliers().stream()
	                        .map(Supplier::getSupplierId)
	                        .toList()
	            );
	        }

	        return dto;
	    }
	@Override
	public ApiResponse<List<PetsResponseDTO>> getAllPets() {

        List<PetsResponseDTO> data =
                petsRepository.findAll()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<PetsResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all pets");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }
	
	@Override
	 public ApiResponse<List<PetsResponseDTO>> addAllPets(List<PetsRequestDTO> dtos) {

        List<Pets> entities = new ArrayList<>();

        for (PetsRequestDTO dto : dtos) {

            if (dto.getName() == null || dto.getName().isBlank()) {
                throw new InvalidDataException("Pet name cannot be empty");
            }

            if (dto.getPrice() == null || dto.getPrice().doubleValue() < 0) {
                throw new InvalidDataException("Price must be positive");
            }

            if (dto.getAge() == null || dto.getAge() < 0) {
                throw new InvalidDataException("Age cannot be negative");
            }

            // CATEGORY
            PetCategories category = categoryRepository.findById(dto.getCategory_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

            Pets pet = new Pets();
            pet.setName(dto.getName());
            pet.setBreed(dto.getBreed());
            pet.setAge(dto.getAge());
            pet.setPrice(dto.getPrice());
            pet.setDescription(dto.getDescription());
            pet.setImage_url(dto.getImage_url());
            pet.setCategory(category);

            // ---------------- GROOMING ----------------
            if (dto.getGroomingServiceIds() != null) {
                pet.setGroomingServices(
                        groomingRepo.findAllById(dto.getGroomingServiceIds()));
            }

            // ---------------- FOOD ----------------
            if (dto.getFoodIds() != null) {
                pet.setFoods(
                        foodRepo.findAllById(dto.getFoodIds()));
            }

            // ---------------- VACCINATIONS ----------------
            if (dto.getVaccinationIds() != null) {
                pet.setVaccinations(
                        vaccinationRepo.findAllById(dto.getVaccinationIds()));
            }

            // ---------------- EMPLOYEES ----------------
            if (dto.getEmployeeIds() != null) {
                pet.setEmployees(
                        employeeRepo.findAllById(dto.getEmployeeIds()));
            }

            // ---------------- SUPPLIERS ----------------
            if (dto.getSupplierIds() != null) {
                pet.setSuppliers(
                        supplierRepo.findAllById(dto.getSupplierIds()));
            }

            entities.add(pet);
        }

        List<PetsResponseDTO> responseList =
                petsRepository.saveAll(entities)
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<PetsResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Pets saved successfully");
        response.setSuccess(true);
        response.setData(responseList);

        return response;
    }

	
	@Override
	 public ApiResponse<PetsResponseDTO> getPetById(long id) {

        Pets pet = petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

        ApiResponse<PetsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Pet fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(pet));

        return response;
    }

  
	
	@Override
	 public ApiResponse<PetsResponseDTO> updatePets(long id, PetsRequestDTO dto) {

        Pets existing = petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

        if (dto.getPrice().doubleValue() < 0) {
            throw new InvalidDataException("Price must be positive");
        }

        existing.setName(dto.getName());
        existing.setBreed(dto.getBreed());
        existing.setAge(dto.getAge());
        existing.setPrice(dto.getPrice());
        existing.setDescription(dto.getDescription());
        existing.setImage_url(dto.getImage_url());

     // CATEGORY
        if (dto.getCategory_id() != null) {
            PetCategories category = categoryRepository.findById(dto.getCategory_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
            existing.setCategory(category);
        }

        // GROOMING
        if (dto.getGroomingServiceIds() != null) {
            existing.setGroomingServices(
                    groomingRepo.findAllById(dto.getGroomingServiceIds()));
        }

        // FOOD
        if (dto.getFoodIds() != null) {
            existing.setFoods(
                    foodRepo.findAllById(dto.getFoodIds()));
        }

        // VACCINATION
        if (dto.getVaccinationIds() != null) {
            existing.setVaccinations(
                    vaccinationRepo.findAllById(dto.getVaccinationIds()));
        }

        // EMPLOYEE
        if (dto.getEmployeeIds() != null) {
            existing.setEmployees(
                    employeeRepo.findAllById(dto.getEmployeeIds()));
        }

        // SUPPLIER
        if (dto.getSupplierIds() != null) {
            existing.setSuppliers(
                    supplierRepo.findAllById(dto.getSupplierIds()));
        }
        Pets updated = petsRepository.save(existing);

        ApiResponse<PetsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }
	
	@Override
	public ApiResponse<String> deletePets(long id) {

        Pets existing = petsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

        petsRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Deleted pet id: " + id);

        return response;
    }
}
