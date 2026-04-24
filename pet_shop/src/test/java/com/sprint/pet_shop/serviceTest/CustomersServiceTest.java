// This service contains the main business flow for customers service test.
package com.sprint.pet_shop.serviceTest;


import com.sprint.pet_shop.dto.requestDto.CustomerRequestDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.exception.DuplicateResourceException;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.service.CustomersService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private AddressesRepository addressesRepository;

    @InjectMocks
    private CustomersService customersService;

    private CustomerRequestDTO requestDTO;
    private Customers customer;
    private Addresses address;

    @BeforeEach
    void setUp() {

        requestDTO = new CustomerRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john@gmail.com");
        requestDTO.setPhoneNumber("9876543210");
        requestDTO.setAddressId(1L);

        address = new Addresses();
        address.setAddressId(1L);
        address.setCity("Chennai");

        customer = new Customers();
        customer.setCustomerId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@gmail.com");
        customer.setPhoneNumber("9876543210");
        customer.setAddress(address);
    }
    @Test
    void saveCustomers_success() {

        when(customersRepository.existsByEmail("john@gmail.com"))
                .thenReturn(false);

        when(addressesRepository.findById(1L))
                .thenReturn(Optional.of(address));

        when(customersRepository.saveAll(anyList()))
                .thenReturn(List.of(customer));

        var response = customersService.savecustomers(List.of(requestDTO));

        assertTrue(response.isSuccess());
        assertEquals("Customers created successfully", response.getMessage());
        assertEquals(1, response.getData().size());

        verify(customersRepository).saveAll(anyList());
    }
    @Test
    void saveCustomers_invalidFirstName_throwsException() {

        requestDTO.setFirstName(" ");

        assertThrows(InvalidDataException.class,
                () -> customersService.savecustomers(List.of(requestDTO)));

        verify(customersRepository, never()).saveAll(anyList());
    }
    @Test
    void saveCustomers_duplicateEmail_throwsException() {

        when(customersRepository.existsByEmail("john@gmail.com"))
                .thenReturn(true);

        assertThrows(DuplicateResourceException.class,
                () -> customersService.savecustomers(List.of(requestDTO)));

        verify(customersRepository, never()).saveAll(anyList());
    }
    @Test
    void saveCustomers_addressNotFound_throwsException() {

        when(customersRepository.existsByEmail("john@gmail.com"))
                .thenReturn(false);

        when(addressesRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> customersService.savecustomers(List.of(requestDTO)));

        verify(customersRepository, never()).saveAll(anyList());
    }
    @Test
    void getCustomerById_success() {

        when(customersRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        var response = customersService.getcustomerByID(1L);

        assertTrue(response.isSuccess());
        assertEquals("Customer found", response.getMessage());
        assertEquals("John", response.getData().getFirstName());

        verify(customersRepository).findById(1L);
    }
}
