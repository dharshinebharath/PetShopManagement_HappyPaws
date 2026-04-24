// This service contains the main business flow for addresses service test.
package com.sprint.pet_shop.serviceTest;

import com.sprint.pet_shop.dto.requestDto.AddressesRequestDTO;
import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.service.AddressesService;

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
class AddressesServiceTest {

    @Mock
    private AddressesRepository addressesRepository;

    @InjectMocks
    private AddressesService addressesService;

    private AddressesRequestDTO requestDTO;
    private Addresses address;

    @BeforeEach
    void setUp() {

        requestDTO = new AddressesRequestDTO();
        requestDTO.setStreet("Anna Nagar");
        requestDTO.setCity("Chennai");
        requestDTO.setState("Tamil Nadu");
        requestDTO.setZipCode("600001");

        address = new Addresses();
        address.setAddressId(1L);
        address.setStreet("Anna Nagar");
        address.setCity("Chennai");
        address.setState("Tamil Nadu");
        address.setZipCode("600001");
    }
    @Test
    void saveAddresses_success() {

        when(addressesRepository.saveAll(anyList()))
                .thenReturn(List.of(address));

        var response = addressesService.saveaddresses(List.of(requestDTO));

        assertTrue(response.isSuccess());
        assertEquals("Addresses created successfully", response.getMessage());
        assertEquals(1, response.getData().size());

        verify(addressesRepository, times(1)).saveAll(anyList());
    }
    @Test
    void saveAddresses_emptyStreet_throwsException() {

        requestDTO.setStreet(" ");

        assertThrows(InvalidDataException.class,
                () -> addressesService.saveaddresses(List.of(requestDTO)));

        verify(addressesRepository, never()).saveAll(anyList());
    }
    @Test
    void saveAddresses_emptyCity_throwsException() {

        requestDTO.setCity("");

        assertThrows(InvalidDataException.class,
                () -> addressesService.saveaddresses(List.of(requestDTO)));

        verify(addressesRepository, never()).saveAll(anyList());
    }
    @Test
    void getAddressById_success() {

        when(addressesRepository.findById(1L))
                .thenReturn(Optional.of(address));

        var response = addressesService.getaddressesByID(1L);

        assertTrue(response.isSuccess());
        assertEquals("Address found", response.getMessage());
        assertEquals("Chennai", response.getData().getCity());

        verify(addressesRepository).findById(1L);
    }
    @Test
    void getAddressById_notFound_throwsException() {

        when(addressesRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> addressesService.getaddressesByID(99L));

        verify(addressesRepository).findById(99L);
    }
}
