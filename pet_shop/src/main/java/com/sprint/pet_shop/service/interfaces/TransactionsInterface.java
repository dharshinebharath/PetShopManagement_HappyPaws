// This service contains the main business flow for transactions interface.
package com.sprint.pet_shop.service.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.sprint.pet_shop.dto.requestDto.TransactionsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.TransactionsResponseDTO;
import com.sprint.pet_shop.entity.TransactionsEntity;

public interface TransactionsInterface {

	ApiResponse<TransactionsResponseDTO> save(TransactionsRequestDTO dto);

    ApiResponse<List<TransactionsResponseDTO>> getAll();

    ApiResponse<TransactionsResponseDTO> getById(Long id);

    ApiResponse<TransactionsResponseDTO> update(Long id, TransactionsRequestDTO dto);

    ApiResponse<String> delete(Long id);

	ApiResponse<List<TransactionsResponseDTO>> getByCustomer(Long customerId);

	ApiResponse<List<TransactionsResponseDTO>> getByStatus(String status);

	ApiResponse<List<TransactionsResponseDTO>> getByDateRange(LocalDate start, LocalDate end);

	
}

