package com.sprint.pet_shop.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetFoodResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.service.EmployeesService;
import com.sprint.pet_shop.service.PetsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pets")
public class PetsController {

	  @Autowired
	    private PetsService petsService;
	  
	  @Autowired
	  private EmployeesService employeesService;

	    @PostMapping
	    public ResponseEntity<ApiResponse<List<PetsResponseDTO>>> addAllPets(
	            @Valid @RequestBody List<PetsRequestDTO> dtos) {

	        ApiResponse<List<PetsResponseDTO>> response =
	                petsService.addAllPets(dtos);

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }

	    @GetMapping
	    public ResponseEntity<ApiResponse<List<PetsResponseDTO>>> getAllPets() {

	        ApiResponse<List<PetsResponseDTO>> response =
	                petsService.getAllPets();

	        return ResponseEntity.ok(response);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<PetsResponseDTO>> getPetById(
	            @PathVariable long id) {

	        ApiResponse<PetsResponseDTO> response =
	                petsService.getPetById(id);

	        return ResponseEntity.ok(response);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ApiResponse<PetsResponseDTO>> updatePet(
	            @PathVariable long id,
	            @Valid @RequestBody PetsRequestDTO dto) {

	        ApiResponse<PetsResponseDTO> response =
	                petsService.updatePets(id, dto);

	        return ResponseEntity.ok(response);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<String>> deletePet(
	            @PathVariable long id) {

	        ApiResponse<String> response =
	                petsService.deletePets(id);

	        return ResponseEntity.ok(response);
	    }
	    
	    @GetMapping("/employee/{empId}")
	    public ApiResponse<List<PetsResponseDTO>> getPetsByEmployeeId(@PathVariable Long empId) {
	        return petsService.getPetsByEmployee(empId);
	    }
	    
	    @GetMapping("/category/{categoryId}")
	    public ApiResponse<List<PetsResponseDTO>> getByCategory(@PathVariable Long categoryId) {
	        return petsService.getPetsByCategory(categoryId);
	    }

	    @GetMapping("/breed/{breed}")
	    public ApiResponse<List<PetsResponseDTO>> getByBreed(@PathVariable String breed) {
	        return petsService.getPetsByBreed(breed);
	    }

	    @GetMapping("/price")
	    public ApiResponse<List<PetsResponseDTO>> getByPrice(
	            @RequestParam BigDecimal min,
	            @RequestParam BigDecimal max) {

	        return petsService.getPetsByPriceRange(min, max);
	    }
	    @PostMapping("/{petId}/grooming-services/{serviceId}")
	    public ApiResponse<String> addService(
	            @PathVariable Long petId,
	            @PathVariable Long serviceId) {

	        return petsService.addGroomingServiceToPet(petId, serviceId);
	    }

	    @GetMapping("/{petId}/grooming-services")
	    public ApiResponse<List<GroomingServicesResponseDTO>> getServices(
	            @PathVariable Long petId) {

	        return petsService.getGroomingServicesByPet(petId);
	    }

	    @DeleteMapping("/{petId}/grooming-services/{serviceId}")
	    public ApiResponse<String> removeService(
	            @PathVariable Long petId,
	            @PathVariable Long serviceId) {

	        return petsService.removeGroomingServiceFromPet(petId, serviceId);
	    }
	    
	    @PostMapping("/{petId}/vaccinations/{vaccinationId}")
	    public ApiResponse<String> addVaccination(
	            @PathVariable Long petId,
	            @PathVariable Long vaccinationId) {
	        return petsService.addVaccinationToPet(petId, vaccinationId);
	    }

	    @GetMapping("/{petId}/vaccinations")
	    public ApiResponse<List<Long>> getVaccinations(@PathVariable Long petId) {
	        return petsService.getVaccinationsByPet(petId);
	    }

	    @DeleteMapping("/{petId}/vaccinations/{vaccinationId}")
	    public ApiResponse<String> removeVaccination(
	            @PathVariable Long petId,
	            @PathVariable Long vaccinationId) {
	        return petsService.removeVaccinationFromPet(petId, vaccinationId);
	    }
	    
	    @PostMapping("/{petId}/suppliers/{supplierId}")
	    public ApiResponse<String> addSupplier(
	            @PathVariable Long petId,
	            @PathVariable Long supplierId) {

	        return petsService.addSupplierToPet(petId, supplierId);
	    }

	    @GetMapping("/{petId}/suppliers")
	    public ApiResponse<List<SupplierResponseDTO>> getSuppliers(
	            @PathVariable Long petId) {

	        return petsService.getSuppliersByPet(petId);
	    }
	    // ADD FOOD
	    @PostMapping("/{petId}/food/{foodId}")
	    public ApiResponse<String> addFood(
	            @PathVariable Long petId,
	            @PathVariable Long foodId) {

	        return petsService.addFoodToPet(petId, foodId);
	    }

	    // GET FOOD
	    @GetMapping("/{petId}/food")
	    public ApiResponse<List<PetFoodResponseDTO>> getFood(@PathVariable Long petId) {
	        return petsService.getFoodByPet(petId);
	    }

	    // REMOVE FOOD
	    @DeleteMapping("/{petId}/food/{foodId}")
	    public ApiResponse<String> removeFood(
	            @PathVariable Long petId,
	            @PathVariable Long foodId) {

	        return petsService.removeFoodFromPet(petId, foodId);
	    }
	    
	    @GetMapping("/{petId}/employees")
	    public ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPet(
	            @PathVariable Long petId) {

			return employeesService.getEmployeesByPet(petId);
	    }
		
	    
	    
}
