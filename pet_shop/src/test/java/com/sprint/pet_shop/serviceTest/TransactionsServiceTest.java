package com.sprint.pet_shop.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sprint.pet_shop.dto.requestDto.TransactionsRequestDTO;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.TransactionStatus;
import com.sprint.pet_shop.entity.TransactionsEntity;
import com.sprint.pet_shop.exception.InvalidDataException;
import com.sprint.pet_shop.exception.ResourceNotFoundException;
import com.sprint.pet_shop.repository.CustomersRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.repository.TransactionsRepository;
import com.sprint.pet_shop.service.TransactionsService;

@ExtendWith(MockitoExtension.class)
class TransactionsServiceTest {

        @Mock
        private TransactionsRepository transactionsRepository;

        @Mock
        private CustomersRepository customersRepository;

        @Mock
        private PetsRepository petsRepository;

        @InjectMocks
        private TransactionsService transactionsService;

        private TransactionsRequestDTO requestDTO;
        private TransactionsEntity transaction;
        private Customers customer;
        private Pets pet;

        @BeforeEach
        void setUp() {

                customer = new Customers();
                customer.setCustomerId(1L);

                pet = new Pets();
                pet.setPet_id(1L);

                requestDTO = new TransactionsRequestDTO();
                requestDTO.setCustomerId(1L);
                requestDTO.setPetId(1L);
                requestDTO.setAmount(new BigDecimal("500"));
                requestDTO.setTransactionDate(LocalDate.now());
                requestDTO.setTransactionStatus(TransactionStatus.SUCCESS);

                transaction = new TransactionsEntity();
                transaction.setTransactionId(1L);
                transaction.setCustomer(customer);
                transaction.setPet(pet);
                transaction.setAmount(new BigDecimal("500"));
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }

        // SAVE SUCCESS
        @Test
        void saveTransaction_success() {

                when(customersRepository.findById(1L))
                                .thenReturn(Optional.of(customer));

                when(petsRepository.findById(1L))
                                .thenReturn(Optional.of(pet));

                when(transactionsRepository.save(any()))
                                .thenReturn(transaction);

                var response = transactionsService.save(requestDTO);

                assertTrue(response.isSuccess());
                assertEquals("Transaction saved successfully", response.getMessage());

                verify(transactionsRepository, times(1)).save(any());
        }

        // CUSTOMER NOT FOUND
        @Test
        void saveTransaction_customerNotFound() {

                when(customersRepository.findById(1L))
                                .thenReturn(Optional.empty());

                assertThrows(ResourceNotFoundException.class,
                                () -> transactionsService.save(requestDTO));

                verify(transactionsRepository, never()).save(any());
        }

        // PET NOT FOUND
        @Test
        void saveTransaction_petNotFound() {

                when(customersRepository.findById(1L))
                                .thenReturn(Optional.of(customer));

                when(petsRepository.findById(1L))
                                .thenReturn(Optional.empty());

                assertThrows(ResourceNotFoundException.class,
                                () -> transactionsService.save(requestDTO));

                verify(transactionsRepository, never()).save(any());
        }

        // INVALID DATA
        @Test
        void saveTransaction_invalidData() {

                requestDTO.setCustomerId(null);

                assertThrows(InvalidDataException.class,
                                () -> transactionsService.save(requestDTO));

                verify(transactionsRepository, never()).save(any());
        }

        // GET BY ID (SUCCESS)
        @Test
        void getTransactionById() {

                // SUCCESS
                when(transactionsRepository.findById(1L))
                                .thenReturn(Optional.of(transaction));

                var response = transactionsService.getById(1L);
                assertTrue(response.isSuccess());
                assertEquals("Transaction fetched successfully", response.getMessage());
                verify(transactionsRepository).findById(1L);
        }
}