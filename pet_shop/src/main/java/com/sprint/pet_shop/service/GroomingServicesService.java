package com.sprint.pet_shop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.GroomingServicesRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.GroomingServicesResponseDTO;
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

    private GroomingServicesResponseDTO toDto(GroomingServices entity) {
        GroomingServicesResponseDTO dto = new GroomingServicesResponseDTO();
        dto.setServiceId(entity.getServiceId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setAvailable(entity.isAvailable());
        return dto;
    }

    @Override
    public ApiResponse<List<GroomingServicesResponseDTO>> saveAllGroomingServices(List<GroomingServicesRequestDTO> dtos) {

        List<GroomingServices> entities = new ArrayList<>();

        for (GroomingServicesRequestDTO dto : dtos) {

            if (dto.getPrice().doubleValue() < 0) {
                throw new InvalidDataException("Invalid price for service: " + dto.getName());
            }

            if (groomingServicesRepository.existsByName(dto.getName())) {
                throw new DuplicateResourceException("Service already exists: " + dto.getName());
            }

            GroomingServices entity = new GroomingServices();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setAvailable(dto.isAvailable());

            entities.add(entity);
        }

        List<GroomingServices> saved = groomingServicesRepository.saveAll(entities);

        List<GroomingServicesResponseDTO> responseList =
                saved.stream().map(this::toDto).toList();

        ApiResponse<List<GroomingServicesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Grooming services saved successfully");
        response.setSuccess(true);
        response.setData(responseList);

        return response;
    }

    @Override
    public ApiResponse<List<GroomingServicesResponseDTO>> getAllGroomingServices() {

        List<GroomingServicesResponseDTO> data =
                groomingServicesRepository.findAll()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<GroomingServicesResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all grooming services");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    @Override
    public ApiResponse<GroomingServicesResponseDTO> getGroomingServiceById(long id) {

        GroomingServices entity = groomingServicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        ApiResponse<GroomingServicesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Service fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }

    @Override
    public ApiResponse<String> deleteGroomingServiceById(long id) {

        GroomingServices existing = groomingServicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Grooming Services Not Found with id: " + id));

        groomingServicesRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Deleted service id: " + id);

        return response;
    }

    @Override
    public ApiResponse<GroomingServicesResponseDTO> updateGroomingService(long id, GroomingServicesRequestDTO dto) {

        GroomingServices existing = groomingServicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        if (dto.getPrice().doubleValue() < 0) {
            throw new InvalidDataException("Price must be positive");
        }

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setAvailable(dto.isAvailable());

        GroomingServices updated = groomingServicesRepository.save(existing);



        ApiResponse<GroomingServicesResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }


	public GroomingServices updateGroomingService(long id, @Valid GroomingServices service) {
		GroomingServices existing = groomingServicesRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Grooming Services Not Found with id:"+id));

		if (service.getPrice().doubleValue() < 0) {
		    throw new InvalidDataException("Price must be positive");
		}
	    existing.setName(service.getName());
	    existing.setDescription(service.getDescription());
	    existing.setPrice(service.getPrice());
	    existing.setAvailable(service.isAvailable());

	    return groomingServicesRepository.save(existing);
	}
	@Override
	public ApiResponse<List<GroomingServicesResponseDTO>> getServicesByPriceRange(BigDecimal min, BigDecimal max) {

	    List<GroomingServicesResponseDTO> data =
	            groomingServicesRepository.findServicesByPriceRange(min, max)
	                    .stream()
	                    .map(this::toDto)
	                    .toList();

	    return new ApiResponse<>("Services fetched by price range", true, data);
	}
}

