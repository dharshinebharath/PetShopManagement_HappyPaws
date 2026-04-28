package com.sprint.pet_shop.service;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.sprint.pet_shop.dto.requestDto.CustomerRequestDTO;

import com.sprint.pet_shop.dto.responseDto.CustomerResponseDTO;

import com.sprint.pet_shop.dto.responseDto.AddressesResponseDTO;

import com.sprint.pet_shop.dto.responseDto.ApiResponse;

import com.sprint.pet_shop.entity.Addresses;

import com.sprint.pet_shop.entity.Customers;

import com.sprint.pet_shop.exception.*;

import com.sprint.pet_shop.repository.AddressesRepository;

import com.sprint.pet_shop.repository.CustomersRepository;

import com.sprint.pet_shop.service.interfaces.CustomersInterface;

@Service

public class CustomersService implements CustomersInterface {

    @Autowired

    private CustomersRepository customersRepository;

    @Autowired

    private AddressesRepository addressesRepository;


    // CREATE

    @Override

    public ApiResponse<List<CustomerResponseDTO>> savecustomers(List<CustomerRequestDTO> dtos) {

        List<Customers> entities = new ArrayList<>();

        for (CustomerRequestDTO dto : dtos) {

            if (customersRepository.existsByEmail(dto.getEmail())) {

                throw new DuplicateResourceException("Email already exists: " + dto.getEmail());

            }

            Customers entity = new Customers();

            entity.setFirstName(dto.getFirstName());

            entity.setLastName(dto.getLastName());

            entity.setEmail(dto.getEmail());

            entity.setPhoneNumber(dto.getPhoneNumber());

            if (dto.getAddressId() != null) {

                Addresses address = addressesRepository.findById(dto.getAddressId())

                        .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

                entity.setAddress(address);

            }

            entities.add(entity);

        }

        List<CustomerResponseDTO> data = customersRepository.saveAll(entities)

                .stream()

                .map(this::toDto)

                .toList();

        return new ApiResponse<>("Customers created successfully", true, data);

    }

    // GET ALL


    @Override

    public ApiResponse<List<CustomerResponseDTO>> getcustomers() {

        List<CustomerResponseDTO> data = customersRepository.findAllSorted()

                .stream()

                .map(this::toDto)

                .toList();

        return new ApiResponse<>("Customers fetched successfully", true, data);

    }

    // GET BY ID

    @Override

    public ApiResponse<CustomerResponseDTO> getcustomerByID(long id) {

        Customers customer = customersRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return new ApiResponse<>("Customer found", true, toDto(customer));

    }

    // UPDATE (SIMPLE LIKE GROOMING)

    @Override

    public ApiResponse<CustomerResponseDTO> updatecustomer(long id, CustomerRequestDTO dto) {

        Customers existing = customersRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        if (customersRepository.existsByEmail(dto.getEmail())) {

            throw new DuplicateResourceException("Email already exists: " + dto.getEmail());

        }

        // ❌ Email should not be changed
        if (dto.getEmail() != null && !existing.getEmail().equalsIgnoreCase(dto.getEmail())) {
            throw new InvalidDataException("Email cannot be updated");
        }

        existing.setFirstName(dto.getFirstName());

        existing.setLastName(dto.getLastName());

        existing.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getAddressId() != null) {

            Addresses address = addressesRepository.findById(dto.getAddressId())

                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

            existing.setAddress(address);

        }

        Customers updated = customersRepository.save(existing);

        return new ApiResponse<>("Customer updated successfully", true, toDto(updated));

    }

    // DELETE

    @Override

    public ApiResponse<String> deletecustomer(long id) {

        Customers customer = customersRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customersRepository.delete(customer);

        return new ApiResponse<>("Customer deleted successfully", true, "Deleted ID: " + id);

    }

    // NO TRANSACTIONS

    @Override

    public ApiResponse<List<CustomerResponseDTO>> getCustomersWithNoTransactions() {

        List<CustomerResponseDTO> data = customersRepository.findCustomersWithNoTransactions()

                .stream()

                .map(this::toDto)

                .toList();

        return new ApiResponse<>("Customers without transactions", true, data);

    }

    // DTO MAPPING

    private CustomerResponseDTO toDto(Customers customer) {

        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setCustomerId(customer.getCustomerId());

        dto.setFirstName(customer.getFirstName());

        dto.setLastName(customer.getLastName());

        dto.setEmail(customer.getEmail());

        dto.setPhoneNumber(customer.getPhoneNumber());

        if (customer.getAddress() != null) {

            AddressesResponseDTO addressDTO = new AddressesResponseDTO();

            addressDTO.setAddressId(customer.getAddress().getAddressId());

            addressDTO.setStreet(customer.getAddress().getStreet());

            addressDTO.setCity(customer.getAddress().getCity());

            addressDTO.setState(customer.getAddress().getState());

            addressDTO.setZipCode(customer.getAddress().getZipCode());

            dto.setAddress(addressDTO);

        }

        return dto;

    }

}