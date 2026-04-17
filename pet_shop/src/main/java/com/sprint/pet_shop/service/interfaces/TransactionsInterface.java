package com.sprint.pet_shop.service.interfaces;

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
	
}
