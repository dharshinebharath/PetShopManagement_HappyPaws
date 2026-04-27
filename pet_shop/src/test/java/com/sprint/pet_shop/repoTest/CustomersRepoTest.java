package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Customers;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.CustomersRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class CustomersRepoTest {

    @Autowired
    private CustomersRepository customersRepository;

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

    private Customers createCustomer(String firstName, String email) {
        Customers customer = new Customers();
        customer.setFirstName(firstName);
        customer.setLastName("Doe");
        customer.setEmail(email);
        customer.setPhoneNumber("1234567890");
        customer.setAddress(createAddress());
        return customersRepository.save(customer);
    }

    @Test
    void testCreateCustomer() {
        Customers customer = createCustomer("John", "john@example.com");
        assertNotNull(customer.getCustomerId());
    }

    @Test
    void testFindCustomerById() {
        Customers customer = createCustomer("Jane", "jane@example.com");
        Optional<Customers> found = customersRepository.findById(customer.getCustomerId());
        assertTrue(found.isPresent());
        assertEquals("Jane", found.get().getFirstName());
    }

    @Test
    void testUpdateCustomer() {
        Customers customer = createCustomer("Alice", "alice@example.com");
        customer.setLastName("Smith");
        Customers updated = customersRepository.save(customer);
        assertEquals("Smith", updated.getLastName());
    }

    @Test
    void testDeleteCustomer() {
        Customers customer = createCustomer("Bob", "bob@example.com");
        customersRepository.delete(customer);
        Optional<Customers> found = customersRepository.findById(customer.getCustomerId());
        assertFalse(found.isPresent());
    }

    @Test
    void testExistsByEmail() {
        createCustomer("Charlie", "charlie@example.com");
        assertTrue(customersRepository.existsByEmail("charlie@example.com"));
        assertFalse(customersRepository.existsByEmail("unknown@example.com"));
    }

    @Test
    void testGetAll() {
        createCustomer("Dave", "dave@example.com");
        List<Customers> all = customersRepository.getAll();
        assertFalse(all.isEmpty());
    }

    @Test
    void testFindCustomersWithNoTransactions() {
        createCustomer("Eve", "eve@example.com");
        List<Customers> noTrans = customersRepository.findCustomersWithNoTransactions();
        assertFalse(noTrans.isEmpty());
    }

    @Test
    void testFindAllSorted() {
        createCustomer("Frank", "frank@example.com");
        createCustomer("Grace", "grace@example.com");
        List<Customers> sorted = customersRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }
}
