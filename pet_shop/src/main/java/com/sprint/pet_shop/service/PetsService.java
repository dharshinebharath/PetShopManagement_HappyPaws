package com.sprint.pet_shop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.PetsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetFoodResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;
import com.sprint.pet_shop.dto.responseDto.VaccinationsResponseDTO;
import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.entity.Vaccinations;
import com.sprint.pet_shop.exception.DuplicateResourceException;
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
							.toList());
		}

		if (pet.getFoods() != null) {
			dto.setFoodIds(
					pet.getFoods().stream()
							.map(PetFood::getFoodId)
							.toList());
		}

		if (pet.getVaccinations() != null) {
			dto.setVaccinationIds(
					pet.getVaccinations().stream()
							.map(Vaccinations::getVaccinationId)
							.toList());
		}

		if (pet.getEmployees() != null) {
			dto.setEmployeeIds(
					pet.getEmployees().stream()
							.map(Employees::getEmployeeId)
							.toList());
		}

		if (pet.getSuppliers() != null) {
			dto.setSupplierIds(
					pet.getSuppliers().stream()
							.map(Supplier::getSupplierId)
							.toList());
		}

		return dto;
	}

	@Override
	public ApiResponse<List<PetsResponseDTO>> getAllPets() {

		List<PetsResponseDTO> data = petsRepository.findAllSorted()
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

		List<PetsResponseDTO> responseList = petsRepository.saveAll(entities)
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

	@Override
	public ApiResponse<List<PetsResponseDTO>> getPetsByEmployee(Long employeeId) {

		List<PetsResponseDTO> data = petsRepository.findPetsByEmployeeId(employeeId)
				.stream()
				.map(this::toDto)
				.toList();

		return new ApiResponse<>("Pets handled by employee", true, data);

	}

	@Override
	public ApiResponse<List<PetsResponseDTO>> getPetsByCategory(Long categoryId) {

		if (categoryId == null || categoryId <= 0) {
			throw new InvalidDataException("Invalid category id");
		}

		List<PetsResponseDTO> data = petsRepository
				.findByCategory_CategoryId(categoryId)
				.stream()
				.sorted(Comparator.comparing(Pets::getPet_id))
				.map(this::toDto)
				.toList();

		return new ApiResponse<>("Pets by category", true, data);
	}

	@Override
	public ApiResponse<List<PetsResponseDTO>> getPetsByBreed(String breed) {

		String normalizedBreed = breed == null ? "" : breed.trim();
		if (normalizedBreed.isEmpty()) {
			throw new InvalidDataException("Breed cannot be empty");
		}

		List<PetsResponseDTO> data = petsRepository
				.findByBreedContainingIgnoreCase(normalizedBreed)
				.stream()
				.sorted(Comparator.comparing(Pets::getPet_id))
				.map(this::toDto)
				.toList();

		return new ApiResponse<>("Pets by breed", true, data);
	}

	@Override
	public ApiResponse<List<PetsResponseDTO>> getPetsByPriceRange(BigDecimal min, BigDecimal max) {

		if (min == null || max == null) {
			throw new InvalidDataException("Min and max price are required");
		}

		if (min.compareTo(max) > 0) {
			throw new InvalidDataException("Min price cannot be greater than max price");
		}

		List<PetsResponseDTO> data = petsRepository
				.findByPriceBetween(min, max)
				.stream()
				.sorted(Comparator.comparing(Pets::getPet_id))
				.map(this::toDto)
				.toList();

		return new ApiResponse<>("Pets by price range", true, data);
	}

	@Override
	public ApiResponse<String> addGroomingServiceToPet(Long petId, Long serviceId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		GroomingServices service = groomingRepo.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service not found"));

		// Initialize list if null
		if (pet.getGroomingServices() == null) {
			pet.setGroomingServices(new ArrayList<>());
		}

		// Avoid duplicate mapping
		if (pet.getGroomingServices().contains(service)) {
			throw new DuplicateResourceException("Service already mapped to pet");
		}

		pet.getGroomingServices().add(service);

		petsRepository.save(pet);

		return new ApiResponse<>("Service added to pet", true, null);
	}

	@Override
	public ApiResponse<List<GroomingServicesResponseDTO>> getGroomingServicesByPet(Long petId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		List<GroomingServicesResponseDTO> data = pet.getGroomingServices()
				.stream()
				.sorted(Comparator.comparing(GroomingServices::getServiceId)) // ✅ SORT

				.map(service -> {
					GroomingServicesResponseDTO dto = new GroomingServicesResponseDTO();
					dto.setServiceId(service.getServiceId());
					dto.setName(service.getName());
					dto.setDescription(service.getDescription());
					dto.setPrice(service.getPrice());
					dto.setAvailable(service.isAvailable());
					return dto;
				})
				.toList();

		return new ApiResponse<>("Services fetched for pet", true, data);
	}

	@Override
	public ApiResponse<String> removeGroomingServiceFromPet(Long petId, Long serviceId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		GroomingServices service = groomingRepo.findById(serviceId)
				.orElseThrow(() -> new ResourceNotFoundException("Service not found"));

		if (pet.getGroomingServices() == null ||
				!pet.getGroomingServices().contains(service)) {
			throw new ResourceNotFoundException("Service not mapped to this pet");
		}

		pet.getGroomingServices().remove(service);

		petsRepository.save(pet);

		return new ApiResponse<>("Service removed from pet", true, null);
	}

	@Override
	public ApiResponse<String> addVaccinationToPet(Long petId, Long vaccinationId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		Vaccinations vaccination = vaccinationRepo.findById(vaccinationId)
				.orElseThrow(() -> new ResourceNotFoundException("Vaccination not found"));

		if (pet.getVaccinations() == null) {
			pet.setVaccinations(new ArrayList<>());
		}

		if (pet.getVaccinations().contains(vaccination)) {
			throw new DuplicateResourceException("Vaccination already added");
		}

		pet.getVaccinations().add(vaccination);

		petsRepository.save(pet);

		return new ApiResponse<>("Vaccination added to pet", true, null);
	}

	@Override
public ApiResponse getVaccinationsByPet(Long petId) {

    Pets pet = petsRepository.findById(petId)
            .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

    List<VaccinationsResponseDTO> data = pet.getVaccinations()
            .stream()
			.sorted(Comparator.comparing(Vaccinations::getVaccinationId)) // ✅ SORT HERE

            .map(vaccination -> {
                VaccinationsResponseDTO dto = new VaccinationsResponseDTO();

                dto.setVaccinationId(vaccination.getVaccinationId());
                dto.setName(vaccination.getName());
                dto.setDescription(vaccination.getDescription());
				dto.setPrice(vaccination.getPrice());
				dto.setAvailable(vaccination.isAvailable());
             

                return dto;
            })
            .toList();

    return new ApiResponse<>("Vaccinations fetched", true, data);
}
	@Override
	public ApiResponse<String> removeVaccinationFromPet(Long petId, Long vaccinationId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		Vaccinations vaccination = vaccinationRepo.findById(vaccinationId)
				.orElseThrow(() -> new ResourceNotFoundException("Vaccination not found"));

		if (pet.getVaccinations() == null ||
				!pet.getVaccinations().contains(vaccination)) {
			throw new ResourceNotFoundException("Vaccination not linked to this pet");
		}

		pet.getVaccinations().remove(vaccination);

		petsRepository.save(pet);

		return new ApiResponse<>("Vaccination removed", true, null);
	}

	@Override
	public ApiResponse<String> addFoodToPet(Long petId, Long foodId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		PetFood food = foodRepo.findById(foodId)
				.orElseThrow(() -> new ResourceNotFoundException("Food not found"));

		if (pet.getFoods() == null) {
			pet.setFoods(new ArrayList<>());
		}

		if (pet.getFoods().contains(food)) {
			throw new DuplicateResourceException("Food already added");
		}

		pet.getFoods().add(food);

		petsRepository.save(pet);

		return new ApiResponse<>("Food added to pet", true, null);
	}

	@Override
	public ApiResponse<List<PetFoodResponseDTO>> getFoodByPet(Long petId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		List<PetFoodResponseDTO> data = pet.getFoods()
				.stream()
				.sorted(Comparator.comparing(PetFood::getFoodId)) // ✅ SORT HERE

				.map(food -> {
					PetFoodResponseDTO dto = new PetFoodResponseDTO();

					dto.setFoodId(food.getFoodId());
					dto.setName(food.getName());
					dto.setBrand(food.getBrand());
					dto.setType(food.getType());
					dto.setQuantity(food.getQuantity());
					dto.setPrice(food.getPrice());

					return dto;
				})
				.toList();

		return new ApiResponse<>("Food fetched", true, data);
	}

	@Override
	public ApiResponse<String> removeFoodFromPet(Long petId, Long foodId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		PetFood food = foodRepo.findById(foodId)
				.orElseThrow(() -> new ResourceNotFoundException("Food not found"));

		if (pet.getFoods() == null ||
				!pet.getFoods().contains(food)) {
			throw new ResourceNotFoundException("Food not linked to this pet");
		}

		pet.getFoods().remove(food);

		petsRepository.save(pet);

		return new ApiResponse<>("Food removed", true, null);
	}

	@Override
	public ApiResponse<String> addSupplierToPet(Long petId, Long supplierId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		Supplier supplier = supplierRepo.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

		// Initialize list if null
		if (pet.getSuppliers() == null) {
			pet.setSuppliers(new ArrayList<>());
		}

		// Avoid duplicate mapping
		if (pet.getSuppliers().contains(supplier)) {
			throw new DuplicateResourceException("Supplier already mapped to this pet");
		}

		pet.getSuppliers().add(supplier);

		petsRepository.save(pet);

		return new ApiResponse<>("Supplier added to pet", true, null);
	}

	@Override
	public ApiResponse<List<SupplierResponseDTO>> getSuppliersByPet(Long petId) {

		Pets pet = petsRepository.findById(petId)
				.orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

		List<SupplierResponseDTO> data = pet.getSuppliers()
				.stream()
				.sorted(Comparator.comparing(Supplier::getSupplierId)) // ✅ SORT

				.map(supplier -> {
					SupplierResponseDTO dto = new SupplierResponseDTO();
					dto.setSupplierId(supplier.getSupplierId());
					dto.setName(supplier.getName());
					dto.setContactPerson(supplier.getContactPerson());
					dto.setPhoneNumber(supplier.getPhoneNumber());
					dto.setEmail(supplier.getEmail());

					dto.setAddressId(
							supplier.getAddress() != null
									? supplier.getAddress().getAddressId()
									: null);

					return dto;
				})
				.toList();

		return new ApiResponse<>("Suppliers fetched for pet", true, data);
	}

}
