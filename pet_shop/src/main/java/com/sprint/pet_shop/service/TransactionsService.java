// This service contains the main business flow for transactions service.
package com.sprint.pet_shop.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.TransactionsRequestDTO;
import com.sprint.pet_shop.dto.responseDto.ApiResponse;
import com.sprint.pet_shop.dto.responseDto.TransactionsResponseDTO;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.TransactionStatus;
import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.repository.TransactionsRepository;
import com.sprint.pet_shop.service.interfaces.TransactionsInterface;

@Service
public class TransactionsService implements TransactionsInterface {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private PetsRepository petsRepository;
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

        entity.setTransactionStatus(dto.getTransactionStatus());

        entity.setCustomer(customer);
        entity.setPet(pet);

        TransactionsEntity saved = transactionsRepository.save(entity);

        return new ApiResponse<>("Transaction saved successfully", true, toDto(saved));
    }
    @Override
    public ApiResponse<List<TransactionsResponseDTO>> getAll() {

        List<TransactionsResponseDTO> data = transactionsRepository.findAllSorted()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return new ApiResponse<>("Fetched all transactions", true, data);
    }
    @Override
    public ApiResponse<TransactionsResponseDTO> getById(Long id) {

        TransactionsEntity entity = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        return new ApiResponse<>("Transaction fetched successfully", true, toDto(entity));
    }
    @Override
    public ApiResponse<TransactionsResponseDTO> update(Long id, TransactionsRequestDTO dto) {

        TransactionsEntity existing = transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        existing.setTransactionDate(dto.getTransactionDate());
        existing.setAmount(dto.getAmount());

        existing.setTransactionStatus(dto.getTransactionStatus());

        TransactionsEntity updated = transactionsRepository.save(existing);

        return new ApiResponse<>("Transaction updated successfully", true, toDto(updated));
    }
    @Override
    public ApiResponse<String> delete(Long id) {

        if (!transactionsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Transaction not found with id: " + id);
        }

        transactionsRepository.deleteById(id);

        return new ApiResponse<>("Deleted successfully", true, "Deleted ID: " + id);
    }
    @Override
    public ApiResponse<List<TransactionsResponseDTO>> getByDateRange(LocalDate start, LocalDate end) {

        List<TransactionsResponseDTO> data = transactionsRepository.findByTransactionDateBetween(start, end)
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>("Transactions by date range", true, data);
    }
    @Override
    public ApiResponse<List<TransactionsResponseDTO>> getByCustomer(Long customerId) {

        if (!customersRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer not found");
        }

        List<TransactionsResponseDTO> data =
                transactionsRepository.findByCustomerCustomerId(customerId)
                        .stream()
                        .map(this::toDto)
                        .toList();

        return new ApiResponse<>("Transactions for customer", true, data);
    }
    @Override
    public ApiResponse<List<TransactionsResponseDTO>> getByStatus(String status) {

        List<TransactionsResponseDTO> data =
                transactionsRepository.findByTransactionStatus(
                        TransactionStatus.valueOf(status.toUpperCase())
                )
                .stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>("Transactions by status", true, data);
    }
}

