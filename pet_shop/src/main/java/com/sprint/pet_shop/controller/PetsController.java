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
import com.sprint.pet_shop.dto.responseDto.VaccinationsResponseDTO;
import com.sprint.pet_shop.service.EmployeesService;
import com.sprint.pet_shop.service.PetsService;

import jakarta.validation.Valid;

/*
 REST Controller for managing pets.
 This is a highly active controller that handles pet profiles, including assigning 
 vaccinations, linking to suppliers, and associating foods and grooming services.
 */
@RestController
// Base URL for all endpoints in this controller.
@RequestMapping("/api/v1/pets")
public class PetsController {

	  @Autowired
	    private PetsService petsService;
	  
	  @Autowired
	  private EmployeesService employeesService;

	    // POST /api/v1/pets - Add a list of pets.
	    @PostMapping
	    public ResponseEntity<ApiResponse<List<PetsResponseDTO>>> addAllPets(
	            @Valid @RequestBody List<PetsRequestDTO> dtos) {

	        ApiResponse<List<PetsResponseDTO>> response =
	                petsService.addAllPets(dtos);

	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }

	    // GET /api/v1/pets - Get all pets.
	    @GetMapping
	    public ResponseEntity<ApiResponse<List<PetsResponseDTO>>> getAllPets() {

	        ApiResponse<List<PetsResponseDTO>> response =
	                petsService.getAllPets();

	        return ResponseEntity.ok(response);
	    }

	    // GET /api/v1/pets/{id} - Get pet by ID.
	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<PetsResponseDTO>> getPetById(
	            @PathVariable long id) {

	        ApiResponse<PetsResponseDTO> response =
	                petsService.getPetById(id);

	        return ResponseEntity.ok(response);
	    }

	    // PUT /api/v1/pets/{id} - Update pet by ID.
	    @PutMapping("/{id}")
	    public ResponseEntity<ApiResponse<PetsResponseDTO>> updatePet(
	            @PathVariable long id,
	            @Valid @RequestBody PetsRequestDTO dto) {

	        ApiResponse<PetsResponseDTO> response =
	                petsService.updatePets(id, dto);

	        return ResponseEntity.ok(response);
	    }

	    // DELETE /api/v1/pets/{id} - Delete pet by ID.
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<String>> deletePet(
	            @PathVariable long id) {

	        ApiResponse<String> response =
	                petsService.deletePets(id);

	        return ResponseEntity.ok(response);
	    }
	    
		// GET /api/v1/pets/employee/{empId} - Get pets by employee ID.
	    @GetMapping("/employee/{empId}")
	    public ApiResponse<List<PetsResponseDTO>> getPetsByEmployeeId(@PathVariable Long empId) {
	        return petsService.getPetsByEmployee(empId);
	    }
	    
		// GET /api/v1/pets/category/{categoryId} - Get pets by category ID.
	    @GetMapping("/category/{categoryId}")
	    public ApiResponse<List<PetsResponseDTO>> getByCategory(@PathVariable Long categoryId) {
	        return petsService.getPetsByCategory(categoryId);
	    }

		// GET /api/v1/pets/breed/{breed} - Get pets by breed.
	    @GetMapping("/breed/{breed}")
	    public ApiResponse<List<PetsResponseDTO>> getByBreed(@PathVariable String breed) {
	        return petsService.getPetsByBreed(breed);
	    }

		// GET /api/v1/pets/price - Get pets by price range.
	    @GetMapping("/price")
	    public ApiResponse<List<PetsResponseDTO>> getByPrice(
	            @RequestParam BigDecimal min,
	            @RequestParam BigDecimal max) {

	        return petsService.getPetsByPriceRange(min, max);
	    }

		// POST /api/v1/pets/{petId}/grooming-services/{serviceId} - Add grooming service to pet.
	    @PostMapping("/{petId}/grooming-services/{serviceId}")
	    public ApiResponse<String> addService(
	            @PathVariable Long petId,
	            @PathVariable Long serviceId) {

	        return petsService.addGroomingServiceToPet(petId, serviceId);
	    }

		// GET /api/v1/pets/{petId}/grooming-services - Get grooming services by pet ID.
	    @GetMapping("/{petId}/grooming-services")
	    public ApiResponse<List<GroomingServicesResponseDTO>> getServices(
	            @PathVariable Long petId) {

	        return petsService.getGroomingServicesByPet(petId);
	    }

		// DELETE /api/v1/pets/{petId}/grooming-services/{serviceId} - Remove grooming service from pet.
	    @DeleteMapping("/{petId}/grooming-services/{serviceId}")
	    public ApiResponse<String> removeService(
	            @PathVariable Long petId,
	            @PathVariable Long serviceId) {

	        return petsService.removeGroomingServiceFromPet(petId, serviceId);
	    }
	    
		// POST /api/v1/pets/{petId}/vaccinations/{vaccinationId} - Add vaccination to pet.
	    @PostMapping("/{petId}/vaccinations/{vaccinationId}")
	    public ApiResponse<String> addVaccination(
	            @PathVariable Long petId,
	            @PathVariable Long vaccinationId) {
	        return petsService.addVaccinationToPet(petId, vaccinationId);
	    }

		// GET /api/v1/pets/{petId}/vaccinations - Get vaccinations by pet ID.
	    @GetMapping("/{petId}/vaccinations")
	    public ApiResponse<List<VaccinationsResponseDTO>> getVaccinations(@PathVariable Long petId) {
	        return petsService.getVaccinationsByPet(petId);
	    }

		// DELETE /api/v1/pets/{petId}/vaccinations/{vaccinationId} - Remove vaccination from pet.
	    @DeleteMapping("/{petId}/vaccinations/{vaccinationId}")
	    public ApiResponse<String> removeVaccination(
	            @PathVariable Long petId,
	            @PathVariable Long vaccinationId) {
	        return petsService.removeVaccinationFromPet(petId, vaccinationId);
	    }
	    
		// POST /api/v1/pets/{petId}/suppliers/{supplierId} - Add supplier to pet.
	    @PostMapping("/{petId}/suppliers/{supplierId}")
	    public ApiResponse<String> addSupplier(
	            @PathVariable Long petId,
	            @PathVariable Long supplierId) {

	        return petsService.addSupplierToPet(petId, supplierId);
	    }

		// GET /api/v1/pets/{petId}/suppliers - Get suppliers by pet ID.
	    @GetMapping("/{petId}/suppliers")
	    public ApiResponse<List<SupplierResponseDTO>> getSuppliers(
	            @PathVariable Long petId) {

	        return petsService.getSuppliersByPet(petId);
	    }

		// POST /api/v1/pets/{petId}/food/{foodId} - Add food to pet.
	    @PostMapping("/{petId}/food/{foodId}")
	    public ApiResponse<String> addFood(
	            @PathVariable Long petId,
	            @PathVariable Long foodId) {

	        return petsService.addFoodToPet(petId, foodId);
	    }

		// GET /api/v1/pets/{petId}/food - Get food by pet ID.
	    @GetMapping("/{petId}/food")
	    public ApiResponse<List<PetFoodResponseDTO>> getFood(@PathVariable Long petId) {
	        return petsService.getFoodByPet(petId);
	    }

		// DELETE /api/v1/pets/{petId}/food/{foodId} - Remove food from pet.
	    public ApiResponse<String> removeFood(
	            @PathVariable Long petId,
	            @PathVariable Long foodId) {

	        return petsService.removeFoodFromPet(petId, foodId);
	    }
	    
		// GET /api/v1/pets/{petId}/employees - Get employees by pet ID.
	    @GetMapping("/{petId}/employees")
	    public ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPet(
	            @PathVariable Long petId) {

			return employeesService.getEmployeesByPet(petId);
	    }
		
	    
	    
}

