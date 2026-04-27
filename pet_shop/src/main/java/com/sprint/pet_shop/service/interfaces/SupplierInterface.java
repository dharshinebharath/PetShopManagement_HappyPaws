package com.sprint.pet_shop.service.interfaces;

import java.util.List;

import com.sprint.pet_shop.dto.requestDto.SupplierRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.SupplierResponseDTO;

/**
 * Defines the service layer operations for managing our Suppliers.
 * Essential for linking suppliers to the products and pets they provide us.
 */
public interface SupplierInterface {

    ApiResponse<List<SupplierResponseDTO>> saveAll(List<SupplierRequestDTO> suppliers);

    ApiResponse<List<SupplierResponseDTO>> getAll();

    ApiResponse<SupplierResponseDTO> getSupplierById(long supplierId);

    ApiResponse<String> deleteSupplier(long supplierId);

    ApiResponse<SupplierResponseDTO> updateSupplier(Long id, SupplierRequestDTO supplier);
    
    ApiResponse<List<SupplierResponseDTO>> getSuppliersByPet(String petName);

}
