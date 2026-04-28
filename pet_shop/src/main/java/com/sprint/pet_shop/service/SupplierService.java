package com.sprint.pet_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.SupplierRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.SupplierRepository;
import com.sprint.pet_shop.service.interfaces.SupplierInterface;

// Implementation of the SupplierInterface.
// Responsible for managing the vendor records and associating their physical addresses 
// during the creation process.
@Service
public class SupplierService implements SupplierInterface {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private AddressesRepository addressesRepository;
    private SupplierResponseDTO toDto(Supplier entity) {

        SupplierResponseDTO dto = new SupplierResponseDTO();

        dto.setSupplierId(entity.getSupplierId());
        dto.setName(entity.getName());
        dto.setContactPerson(entity.getContactPerson());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail()); 
        dto.setAddressId(
        		entity.getAddress() != null ? entity.getAddress().getAddressId() : null        	);

        return dto;
    }

    	// Implementation of adding supplier
    @Override
    public ApiResponse<List<SupplierResponseDTO>> saveAll(List<SupplierRequestDTO> suppliers) {

        if (suppliers == null || suppliers.isEmpty()) {
            throw new InvalidDataException("Supplier list cannot be empty");
        }

        List<Supplier> entities = suppliers.stream().map(dto -> {

            if (dto.getName() == null || dto.getName().isBlank()) {
                throw new InvalidDataException("Supplier name cannot be empty or null");
            }
            if (dto.getContactPerson() == null || dto.getContactPerson().isBlank()) {
                throw new InvalidDataException("Contact person cannot be empty or null");
            }
            if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().matches("[0-9]{10}")) {
                throw new InvalidDataException("Phone number must be 10 digits");
            }
            if (dto.getEmail() != null) {
                if (dto.getEmail().isBlank()) {
                    throw new InvalidDataException("Email cannot be blank");
                }
                if (!dto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new InvalidDataException("Invalid email format");
                }
            }
            if (dto.getAddressId() == null) {
                throw new InvalidDataException("Address ID is required");
            }

            if (dto.getEmail() != null &&
                    supplierRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateResourceException("Supplier with email " + dto.getEmail() + " already exists");
            }

            Supplier s = new Supplier();
            s.setName(dto.getName());
            s.setContactPerson(dto.getContactPerson());
            s.setPhoneNumber(dto.getPhoneNumber());
            s.setEmail(dto.getEmail());
            
            if (dto.getAddressId() != null) {

                Addresses address = addressesRepository.findById(dto.getAddressId())
                        .orElseThrow(() -> new ResourceNotFoundException("Address with id " + dto.getAddressId() + " not found"));

                s.setAddress(address);
            }


            return s;

        }).toList();

        List<Supplier> saved = supplierRepository.saveAll(entities);

        List<SupplierResponseDTO> responseList =
                saved.stream().map(this::toDto).toList();

        ApiResponse<List<SupplierResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Suppliers saved successfully");
        response.setSuccess(true);
        response.setData(responseList);

        return response;
    }
    
    // Implementation of getting all suppliers
    @Override
    public ApiResponse<List<SupplierResponseDTO>> getAll() {

        List<SupplierResponseDTO> data =
                supplierRepository.findAllSorted()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<SupplierResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all suppliers");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }
    
    // Implementation of getting supplier by ID
    @Override
    public ApiResponse<SupplierResponseDTO> getSupplierById(long supplierId) {

        Supplier entity = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier not found with id: " + supplierId));

        ApiResponse<SupplierResponseDTO> response = new ApiResponse<>();
        response.setMessage("Supplier fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }
    
    // Implementation of deleting supplier
    @Override
    public ApiResponse<String> deleteSupplier(long supplierId) {

        Supplier existing = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier not found with id: " + supplierId));

        supplierRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Supplier deleted with id: " + supplierId);

        return response;
    }
    // Implementation of updating supplier
    @Override
    public ApiResponse<SupplierResponseDTO> updateSupplier(Long id, SupplierRequestDTO dto) {

        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Supplier not found with id: " + id));

        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new InvalidDataException("Supplier name cannot be empty or null");
        }
        if (dto.getContactPerson() == null || dto.getContactPerson().isBlank()) {
            throw new InvalidDataException("Contact person cannot be empty or null");
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().matches("[0-9]{10}")) {
            throw new InvalidDataException("Phone number must be 10 digits");
        }
        if (dto.getEmail() != null) {
            if (dto.getEmail().isBlank()) {
                throw new InvalidDataException("Email cannot be blank");
            }
            if (!dto.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                throw new InvalidDataException("Invalid email format");
            }
        }
        if (dto.getAddressId() == null) {
            throw new InvalidDataException("Address ID is required");
        }

        if (dto.getEmail() != null &&
        	    supplierRepository.existsByEmail(dto.getEmail()) &&
        	    !existing.getEmail().equalsIgnoreCase(dto.getEmail())) {
        	    throw new DuplicateResourceException("Supplier with email " + dto.getEmail() + " already exists");
        	}

        if (dto.getName() != null) {
            existing.setName(dto.getName());
        }
        if (dto.getContactPerson() != null) {
            existing.setContactPerson(dto.getContactPerson());
        }
        if (dto.getPhoneNumber() != null) {
            existing.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getEmail() != null) {
            existing.setEmail(dto.getEmail());
        }
        
        if (dto.getAddressId() != null) {

            Addresses address = addressesRepository.findById(dto.getAddressId())
                    .orElseThrow(() -> new ResourceNotFoundException("Address with id " + dto.getAddressId() + " not found"));

            existing.setAddress(address);
        }


        Supplier updated = supplierRepository.save(existing);

        ApiResponse<SupplierResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }
    
    // Implementation of getting supplier by pet name
    @Override
    public ApiResponse<List<SupplierResponseDTO>> getSuppliersByPet(String petName) {

        List<SupplierResponseDTO> data = supplierRepository.findSuppliersByPetName(petName)
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>("Suppliers for pet", true, data);
    }
    
    
}

