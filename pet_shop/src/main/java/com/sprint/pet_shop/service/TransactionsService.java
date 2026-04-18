package com.sprint.pet_shop.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.TransactionsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.TransactionsResponseDTO;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.repository.TransactionsRepository;
import com.sprint.pet_shop.service.interfaces.TransactionsInterface;

@Service
public class TransactionsService implements TransactionsInterface{

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private PetsRepository petsRepository;

    // CREATE
    @Override
    public ApiResponse<TransactionsResponseDTO> save(TransactionsRequestDTO dto) {

        if (dto.getCustomerId() == null || dto.getPetId() == null) {
            throw new InvalidDataException("Customer ID and Pet ID cannot be null");
        }

        Customers customer = customersRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Pets pet = petsRepository.findById(dto.getPetId())
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found"));

        TransactionsEntity entity = new TransactionsEntity();
        entity.setTransactionDate(dto.getTransactionDate());
        entity.setAmount(dto.getAmount());
        entity.setTransactionStatus(
                com.sprint.pet_shop.entity.TransactionStatus.valueOf(dto.getTransactionStatus())
        );
        entity.setCustomer(customer);
        entity.setPet(pet);

        TransactionsEntity saved = transactionsRepository.save(entity);

        ApiResponse<TransactionsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Transaction saved successfully");
        response.setSuccess(true);
        response.setData(toDto(saved));

        return response;
    }

    // GET ALL
    @Override
    public ApiResponse<List<TransactionsResponseDTO>> getAll() {

        List<TransactionsResponseDTO> data = transactionsRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        ApiResponse<List<TransactionsResponseDTO>> response = new ApiResponse<>();
        response.setMessage("Fetched all transactions");
        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    // GET BY ID
    @Override
    public ApiResponse<TransactionsResponseDTO> getById(Long id) {

        TransactionsEntity entity = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        ApiResponse<TransactionsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Transaction fetched successfully");
        response.setSuccess(true);
        response.setData(toDto(entity));

        return response;
    }

    // UPDATE
    @Override
    public ApiResponse<TransactionsResponseDTO> update(Long id, TransactionsRequestDTO dto) {

        TransactionsEntity existing = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        if (dto.getCustomerId() == null || dto.getPetId() == null) {
            throw new InvalidDataException("Customer ID and Pet ID cannot be null");
        }

        existing.setTransactionDate(dto.getTransactionDate());
        existing.setAmount(dto.getAmount());
        existing.setTransactionStatus(
                com.sprint.pet_shop.entity.TransactionStatus.valueOf(dto.getTransactionStatus())
        );

        TransactionsEntity updated = transactionsRepository.save(existing);

        ApiResponse<TransactionsResponseDTO> response = new ApiResponse<>();
        response.setMessage("Transaction updated successfully");
        response.setSuccess(true);
        response.setData(toDto(updated));

        return response;
    }

    // DELETE
    @Override
    public ApiResponse<String> delete(Long id) {

        if (!transactionsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }

        transactionsRepository.deleteById(id);

        ApiResponse<String> response = new ApiResponse<>();
        response.setMessage("Deleted successfully");
        response.setSuccess(true);
        response.setData("Deleted ID: " + id);

        return response;
    }

    // ENTITY → DTO
    private TransactionsResponseDTO toDto(TransactionsEntity entity) {

        TransactionsResponseDTO dto = new TransactionsResponseDTO();

        dto.setTransactionId(entity.getTransactionId());
        dto.setTransactionDate(entity.getTransactionDate());
        dto.setAmount(entity.getAmount());
        dto.setCustomerId(entity.getCustomer().getCustomerId());
        dto.setPetId(entity.getPet().getPet_id());
        dto.setTransactionStatus(entity.getTransactionStatus().name());

        return dto;
    }
    
    @Override
    public ApiResponse<List<TransactionsResponseDTO>> getByDateRange(Date start, Date end) {

        List<TransactionsResponseDTO> data = transactionsRepository.findByDateRange(start, end)
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>("Transactions by date range", true, data);
    }
}