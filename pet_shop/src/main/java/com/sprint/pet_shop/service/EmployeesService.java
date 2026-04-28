package com.sprint.pet_shop.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.EmployeesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.EmployeesResponseDTO;
import com.sprint.pet_shop.dto.responseDto.PetsResponseDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.EmployeesRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.service.interfaces.EmployeesInterface;

/**
 * Implementation of the EmployeesInterface.
 * Manages our staff records. Contains logic to safely remove an employee and automatically 
 * reassign or unlink the pets they were responsible for.
 */
@Service
public class EmployeesService implements EmployeesInterface {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private AddressesRepository addressesRepository;
    
    @Autowired
    private PetsRepository petsRepository;
    
    // converting employees to dto
    private EmployeesResponseDTO toDto(Employees entity) {

        EmployeesResponseDTO dto = new EmployeesResponseDTO();

        dto.setEmployeeId(entity.getEmployeeId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPosition(entity.getPosition());
        dto.setHireDate(entity.getHireDate());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());

        dto.setAddressId(
        	    entity.getAddress() != null ? entity.getAddress().getAddressId() : null
        	);
        return dto;
    }
    // saving employees into the database
    @Override
    public ApiResponse<List<EmployeesResponseDTO>> saveAll(List<EmployeesRequestDTO> employees) {

        if (employees == null || employees.isEmpty()) {
            throw new InvalidDataException("Employee list cannot be empty");
        }

        List<Employees> entities = employees.stream().map(dto -> {

            Employees emp = new Employees();

            if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
                throw new InvalidDataException("First name cannot be empty or null");
            }
            if (dto.getLastName() == null || dto.getLastName().isBlank()) {
                throw new InvalidDataException("Last name cannot be empty or null");
            }
            if (dto.getPosition() == null || dto.getPosition().isBlank()) {
                throw new InvalidDataException("Position cannot be empty or null");
            }
            if (dto.getHireDate() == null) {
                throw new InvalidDataException("Hire date is required");
            }
            if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank()) {
                throw new InvalidDataException("Phone number cannot be empty or null");
            }
            if (!dto.getPhoneNumber().matches("[0-9]{10}")) {
                throw new InvalidDataException("Phone number must be 10 digits");
            }
            if (dto.getEmail() == null || dto.getEmail().isBlank()) {
                throw new InvalidDataException("Email cannot be empty or null");
            }
            if (!dto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                throw new InvalidDataException("Invalid email format");
            }
            if (dto.getAddressId() == null) {
                throw new InvalidDataException("Address ID is required");
            }

            if (employeesRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateResourceException("Email already exists: " + dto.getEmail());
            }

            emp.setFirstName(dto.getFirstName());
            emp.setLastName(dto.getLastName());
            emp.setPosition(dto.getPosition());
            emp.setHireDate(dto.getHireDate());
            emp.setPhoneNumber(dto.getPhoneNumber());
            emp.setEmail(dto.getEmail());

            if (dto.getAddressId() != null) {

                Addresses address = addressesRepository.findById(dto.getAddressId())
                        .orElseThrow(() -> new ResourceNotFoundException("Address with id " + dto.getAddressId() + " not found"));

                emp.setAddress(address);
            }

            return emp;

        }).toList();

        List<Employees> saved = employeesRepository.saveAll(entities);

        List<EmployeesResponseDTO> responseList =
                saved.stream().map(this::toDto).toList();

        ApiResponse<List<EmployeesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Employees saved successfully");
        response.setSuccess(true);
        response.setData(responseList);

        return response;
    }
    // get all employees 
    @Override
    public ApiResponse<List<EmployeesResponseDTO>> getAll() {

        List<EmployeesResponseDTO> data =
        		employeesRepository.findAllByOrderByEmployeeIdAsc()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<EmployeesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all employees");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }
    // get employees by id 
    @Override
    public ApiResponse<EmployeesResponseDTO> getEmployeesById(long employeesId) {

        Employees entity = employeesRepository.findById(employeesId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + employeesId));

        ApiResponse<EmployeesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Employee fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }
    // deleting employees 
    @Override
    public ApiResponse<String> deleteEmployee(long employeeId) {

        Employees existing = employeesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + employeeId));

        if (existing.getPets() != null) {
            existing.getPets().forEach(pet -> pet.getEmployees().remove(existing));
            existing.getPets().clear();
        }

        employeesRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Employee deleted successfully with id: " + employeeId);

        return response;
    }
    // updating employees 
    @Override
    public ApiResponse<EmployeesResponseDTO> updateEmployee(Long id, EmployeesRequestDTO dto) {

        Employees existing = employeesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));

        if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
            throw new InvalidDataException("First name cannot be empty or null");
        }
        if (dto.getLastName() == null || dto.getLastName().isBlank()) {
            throw new InvalidDataException("Last name cannot be empty or null");
        }
        if (dto.getPosition() == null || dto.getPosition().isBlank()) {
            throw new InvalidDataException("Position cannot be empty or null");
        }
        if (dto.getHireDate() == null) {
            throw new InvalidDataException("Hire date is required");
        }
        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().isBlank()) {
            throw new InvalidDataException("Phone number cannot be empty or null");
        }
        if (!dto.getPhoneNumber().matches("[0-9]{10}")) {
            throw new InvalidDataException("Phone number must be 10 digits");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new InvalidDataException("Email cannot be empty or null");
        }
        if (!dto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidDataException("Invalid email format");
        }
        if (dto.getAddressId() == null) {
            throw new InvalidDataException("Address ID is required");
        }

        if (!existing.getEmail().equalsIgnoreCase(dto.getEmail())) {
            if (employeesRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateResourceException("Email already exists: " + dto.getEmail());
            }
        }

        if (dto.getFirstName() != null) {
            existing.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            existing.setLastName(dto.getLastName());
        }
        if (dto.getPosition() != null) {
            existing.setPosition(dto.getPosition());
        }
        if (dto.getHireDate() != null) {
            existing.setHireDate(dto.getHireDate());
        }
        if (dto.getPhoneNumber() != null) {
            existing.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }
        if (dto.getAddressId() != null) {
            Addresses address = addressesRepository.findById(dto.getAddressId())
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + dto.getAddressId()));

            existing.setAddress(address);
        }

        Employees updated = employeesRepository.save(existing);

        ApiResponse<EmployeesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }
    // get employees by position
	    @Override
	    public ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPosition(String position) {
	
	        List<EmployeesResponseDTO> data =
	                employeesRepository.findEmployeesByPosition(position)
	                        .stream()
	                        .map(this::toDto)
	                        .toList();
	
	        return new ApiResponse<>("Employees fetched by position", true, data);
	    }
        // assign pet to employee
	    @Override
        public ApiResponse<String> assignPetToEmployee(Long employeeId, Long petId) {

            Employees emp = employeesRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

            Pets pet = petsRepository.findById(petId)
                    .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));

            if (!emp.getPets().contains(pet)) {
                emp.getPets().add(pet);
            }

            if (!pet.getEmployees().contains(emp)) {
                pet.getEmployees().add(emp);
            }

            employeesRepository.save(emp);
            petsRepository.save(pet);

            return new ApiResponse<>("Pet assigned to employee", true, null);
        }
	    // get pets by employee
	    @Override
	    public ApiResponse<List<PetsResponseDTO>> getPetsByEmployee(Long employeeId) {

	        Employees emp = employeesRepository.findById(employeeId)
	                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

	        List<PetsResponseDTO> data = emp.getPets()
	                .stream()
                    .sorted(Comparator.comparing(Pets::getPet_id))
	                .map(pet -> {
	                    PetsResponseDTO dto = new PetsResponseDTO();
	                    dto.setPet_id(pet.getPet_id());
	                    dto.setName(pet.getName());
	                    return dto;
	                })
	                .toList();

	        return new ApiResponse<>("Pets of employee", true, data);
	    }
	    // get employees by pet
	    @Override
	    public ApiResponse<List<EmployeesResponseDTO>> getEmployeesByPet(Long petId) {

	        Pets pet = petsRepository.findById(petId)
	                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));

	        List<EmployeesResponseDTO> data = pet.getEmployees()
	                .stream()
	                .map(this::toDto)
	                .toList();

	        return new ApiResponse<>("Employees handling pet", true, data);
	    }
	    // remove pet from employee
	    @Override
        public ApiResponse<String> removePetFromEmployee(Long employeeId, Long petId) {

            Employees emp = employeesRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

            Pets pet = petsRepository.findById(petId)
                    .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + petId));

            emp.getPets().remove(pet);
            pet.getEmployees().remove(emp);

            employeesRepository.save(emp);
            petsRepository.save(pet);

            return new ApiResponse<>("Pet removed from employee", true, null);
        }
        // get employees hired after date
        @Override
	    public ApiResponse<List<EmployeesResponseDTO>> getEmployeesHiredAfter(LocalDate date) {

	        List<EmployeesResponseDTO> data =
	                employeesRepository.findByHireDateAfter(date)
	                        .stream()
	                        .map(this::toDto)
	                        .toList();

	        return new ApiResponse<>("Employees hired after date", true, data);
	    }
    }

