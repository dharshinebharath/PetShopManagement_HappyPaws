package com.sprint.pet_shop.service;

import java.util.List;
import java.util.stream.Collectors;

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

    // 🔹 CREATE
    @Override
    public ApiResponse<List<CustomerResponseDTO>> savecustomers(List<CustomerRequestDTO> customers) {

        List<Customers> entities = customers.stream().map(dto -> {

            validate(dto);

            if (customersRepository.existsByEmail(dto.getEmail())) {
                throw new DuplicateResourceException("Customer already exists with email: " + dto.getEmail());
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

            return entity;

        }).collect(Collectors.toList());

        List<CustomerResponseDTO> data = customersRepository.saveAll(entities)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new ApiResponse<>("Customers created successfully", true, data);
    }

    // 🔹 GET ALL
    @Override
    public ApiResponse<List<CustomerResponseDTO>> getcustomers() {

        List<CustomerResponseDTO> data = customersRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new ApiResponse<>("Customers fetched successfully", true, data);
    }

    // 🔹 GET BY ID
    @Override
    public ApiResponse<CustomerResponseDTO> getcustomerByID(long id) {

        Customers customer = customersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return new ApiResponse<>("Customer found", true, mapToResponse(customer));
    }

    // 🔹 UPDATE
    @Override
    public ApiResponse<CustomerResponseDTO> updatecustomer(long id, CustomerRequestDTO dto) {

        Customers existing = customersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        validate(dto);

        if (customersRepository.existsByEmail(dto.getEmail())
                && !existing.getEmail().equals(dto.getEmail())) {
            throw new DuplicateResourceException("Email already used");
        }

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddressId() != null) {
            Addresses address = addressesRepository.findById(dto.getAddressId())
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

            existing.setAddress(address);
        }

        Customers updated = customersRepository.save(existing);

        return new ApiResponse<>("Customer updated successfully", true, mapToResponse(updated));
    }

    // 🔹 DELETE
    @Override
    public ApiResponse<String> deletecustomer(long id) {

        if (!customersRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found");
        }

        customersRepository.deleteById(id);

        return new ApiResponse<>("Customer deleted successfully", true, "Deleted ID: " + id);
    }

    // 🔥 VALIDATION METHOD
    private void validate(CustomerRequestDTO dto) {

        if (dto.getFirstName() == null || dto.getFirstName().trim().isEmpty()) {
            throw new InvalidDataException("First name cannot be empty");
        }

        if (dto.getLastName() == null || dto.getLastName().trim().isEmpty()) {
            throw new InvalidDataException("Last name cannot be empty");
        }

        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty()) {
            throw new InvalidDataException("Email cannot be empty");
        }

        if (dto.getPhoneNumber() == null || dto.getPhoneNumber().trim().isEmpty()) {
            throw new InvalidDataException("Phone number cannot be empty");
        }
    }

    // 🔥 ENTITY → RESPONSE
    private CustomerResponseDTO mapToResponse(Customers customer) {

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