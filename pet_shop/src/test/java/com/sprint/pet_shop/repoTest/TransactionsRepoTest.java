package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.*;
import com.sprint.pet_shop.repository.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class TransactionsRepoTest {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private PetCategoriesRepository petCategoriesRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @Autowired
    private EntityManager entityManager;

    private Addresses createAddress() {
        Addresses address = new Addresses();
        address.setStreet("123 Main St");
        address.setCity("City");
        address.setState("State");
        address.setZipCode("12345");
        return addressesRepository.save(address);
    }

    private Customers createCustomer() {
        Customers customer = new Customers();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.t@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress(createAddress());
        return customersRepository.save(customer);
    }

    private Pets createPet() {
        PetCategories category = new PetCategories();
        category.setName("Dogs");
        petCategoriesRepository.save(category);

        Pets pet = new Pets();
        pet.setName("Rex");
        pet.setBreed("German Shepherd");
        pet.setAge(2);
        pet.setPrice(new BigDecimal("500.00"));
        pet.setDescription("Desc");
        pet.setImage_url("url");
        pet.setCategory(category);
        return petsRepository.save(pet);
    }

    private TransactionsEntity createTransaction(Customers customer, Pets pet, TransactionStatus status) {
        TransactionsEntity transaction = new TransactionsEntity();
        transaction.setTransactionDate(LocalDate.now());
        transaction.setAmount(new BigDecimal("500.00"));
        transaction.setCustomer(customer);
        transaction.setPet(pet);
        transaction.setTransactionStatus(status);
        return transactionsRepository.save(transaction);
    }

    @Test
    void testCreateTransaction() {
        TransactionsEntity transaction = createTransaction(createCustomer(), createPet(), TransactionStatus.SUCCESS);
        assertNotNull(transaction.getTransactionId());
    }

    @Test
    void testFindTransactionById() {
        TransactionsEntity transaction = createTransaction(createCustomer(), createPet(), TransactionStatus.SUCCESS);
        Optional<TransactionsEntity> found = transactionsRepository.findById(transaction.getTransactionId());
        assertTrue(found.isPresent());
        assertEquals(TransactionStatus.SUCCESS, found.get().getTransactionStatus());
    }

    @Test
    void testUpdateTransaction() {
        TransactionsEntity transaction = createTransaction(createCustomer(), createPet(), TransactionStatus.PENDING);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        TransactionsEntity updated = transactionsRepository.save(transaction);
        assertEquals(TransactionStatus.SUCCESS, updated.getTransactionStatus());
    }

    @Test
    void testDeleteTransaction() {
        TransactionsEntity transaction = createTransaction(createCustomer(), createPet(), TransactionStatus.FAILED);
        transactionsRepository.delete(transaction);
        Optional<TransactionsEntity> found = transactionsRepository.findById(transaction.getTransactionId());
        assertFalse(found.isPresent());
    }

    @Test
    void testFindByCustomerCustomerId() {
        Customers customer = createCustomer();
        createTransaction(customer, createPet(), TransactionStatus.SUCCESS);
        List<TransactionsEntity> found = transactionsRepository.findByCustomerCustomerId(customer.getCustomerId());
        assertFalse(found.isEmpty());
    }

    @Test
    void testFindByTransactionStatus() {
        createTransaction(createCustomer(), createPet(), TransactionStatus.FAILED);
        List<TransactionsEntity> found = transactionsRepository.findByTransactionStatus(TransactionStatus.FAILED);
        assertFalse(found.isEmpty());
    }

    @Test
    void testFindByTransactionDateBetween() {
        createTransaction(createCustomer(), createPet(), TransactionStatus.SUCCESS);
        List<TransactionsEntity> found = transactionsRepository.findByTransactionDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        assertFalse(found.isEmpty());
    }

    @Test
    void testFindAllSorted() {
        Customers customer = createCustomer();
        Pets pet = createPet();
        createTransaction(customer, pet, TransactionStatus.SUCCESS);
        createTransaction(customer, pet, TransactionStatus.PENDING);
        List<TransactionsEntity> sorted = transactionsRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }
}
