package com.sprint.pet_shop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sprint.pet_shop.dto.requestDto.VaccinationsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.VaccinationsResponseDTO;
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
	
	 // 🔹 ENTITY → DTO
    private VaccinationsResponseDTO toDto(Vaccinations entity) {
        VaccinationsResponseDTO dto = new VaccinationsResponseDTO();

        dto.setVaccinationId(entity.getVaccinationId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setAvailable(entity.isAvailable());

        return dto;
    }

	@Override
	  public ApiResponse<List<VaccinationsResponseDTO>> saveAllVaccinations(List<VaccinationsRequestDTO> dtos) {

        List<Vaccinations> entities = new ArrayList<>();

        for (VaccinationsRequestDTO dto : dtos) {

            if (dto.getPrice().doubleValue() < 0) {
                throw new InvalidDataException("Invalid price: " + dto.getName());
            }

            if (vaccinationsRepository.existsByName(dto.getName())) {
                throw new DuplicateResourceException("Already exists: " + dto.getName());
            }

            Vaccinations entity = new Vaccinations();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setPrice(dto.getPrice());
            entity.setAvailable(dto.isAvailable());

            entities.add(entity);
        }

        List<VaccinationsResponseDTO> data =
                vaccinationsRepository.saveAll(entities)
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<VaccinationsResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Vaccinations saved successfully");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }
	
	@Override	
	  public ApiResponse<List<VaccinationsResponseDTO>> getAllVaccinations() {

        List<VaccinationsResponseDTO> data =
                vaccinationsRepository.findAll()
                        .stream()
                        .map(this::toDto)
                        .toList();

        ApiResponse<List<VaccinationsResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all vaccinations");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }
	
	@Override
	public ApiResponse<VaccinationsResponseDTO> getVaccinationsById(long id) {

        Vaccinations entity = vaccinationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        ApiResponse<VaccinationsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Vaccination fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }
	
	@Override	
	 public ApiResponse<String> deleteVaccinationsById(long id) {

        Vaccinations existing = vaccinationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        vaccinationsRepository.delete(existing);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Deleted vaccination id: " + id);

        return response;
    }
	
	@Override
	 public ApiResponse<VaccinationsResponseDTO> updateVaccinationById(long id, VaccinationsRequestDTO dto) {

        Vaccinations existing = vaccinationsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));

        if (dto.getPrice().doubleValue() < 0) {
            throw new InvalidDataException("Price must be positive");
        }

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setAvailable(dto.isAvailable());

        Vaccinations updated = vaccinationsRepository.save(existing);

        ApiResponse<VaccinationsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
	}

	 @Override
	    public ApiResponse<List<VaccinationsResponseDTO>> getVaccinationsByPrice(BigDecimal min, BigDecimal max) {

	        List<VaccinationsResponseDTO> data = vaccinationsRepository.findByPriceRange(min, max)
	                .stream()
	                .map(this::toDto)
	                .toList();

	        return new ApiResponse<>("Vaccinations by price range", true, data);
	    }
        
	
}
