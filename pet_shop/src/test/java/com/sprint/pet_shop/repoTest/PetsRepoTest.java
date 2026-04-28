package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.EmployeesRepository;
import com.sprint.pet_shop.repository.PetCategoriesRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class PetsRepoTest {

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private PetCategoriesRepository petCategoriesRepository;

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private AddressesRepository addressesRepository;

  

    private PetCategories createCategory() {
        PetCategories category = new PetCategories();
        category.setName("Dogs");
        return petCategoriesRepository.save(category);
    }

    private Addresses createAddress() {
        Addresses address = new Addresses();
        address.setStreet("123 Main St");
        address.setCity("City");
        address.setState("State");
        address.setZipCode("12345");
        return addressesRepository.save(address);
    }

    private Employees createEmployee() {
        Employees employee = new Employees();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Vet");
        employee.setHireDate(LocalDate.now());
        employee.setPhoneNumber("1234567890");
        employee.setEmail("john.vet@example.com");
        employee.setAddress(createAddress());
        return employeesRepository.save(employee);
    }

    private Pets createPet(String name, String breed, BigDecimal price, PetCategories category) {
        Pets pet = new Pets();
        pet.setName(name);
        pet.setBreed(breed);
        pet.setAge(2);
        pet.setPrice(price);
        pet.setDescription("A cute pet");
        pet.setImage_url("http://example.com/pet.jpg");
        pet.setCategory(category);
        return petsRepository.save(pet);
    }

    @Test
    void testCreatePet() {
        Pets pet = createPet("Buddy", "Golden Retriever", new BigDecimal("500.00"), createCategory());
        assertNotNull(pet.getPet_id());
    }

    @Test
    void testFindPetById() {
        Pets pet = createPet("Bella", "Labrador", new BigDecimal("600.00"), createCategory());
        Optional<Pets> found = petsRepository.findById(pet.getPet_id());
        assertTrue(found.isPresent());
        assertEquals("Bella", found.get().getName());
    }

    @Test
    void testUpdatePet() {
        Pets pet = createPet("Charlie", "Poodle", new BigDecimal("400.00"), createCategory());
        pet.setAge(3);
        Pets updated = petsRepository.save(pet);
        assertEquals(3, updated.getAge());
    }

    @Test
    void testDeletePet() {
        Pets pet = createPet("Max", "Bulldog", new BigDecimal("700.00"), createCategory());
        petsRepository.delete(pet);
        Optional<Pets> found = petsRepository.findById(pet.getPet_id());
        assertFalse(found.isPresent());
    }

    @Test
    void testFindPetsByEmployeeId() {
        PetCategories category = createCategory();
        Pets pet = createPet("Luna", "Husky", new BigDecimal("800.00"), category);
        Employees employee = createEmployee();
        
        List<Employees> emps = new ArrayList<>();
        emps.add(employee);
        pet.setEmployees(emps);
        petsRepository.save(pet);

        List<Pets> found = petsRepository.findPetsByEmployeeId(employee.getEmployeeId());
        assertEquals(1, found.size());
    }

    @Test
    void testFindByCategory_CategoryId() {
        PetCategories category = createCategory();
        createPet("Rocky", "Boxer", new BigDecimal("450.00"), category);
        List<Pets> found = petsRepository.findByCategory_CategoryId(category.getCategory_id());
        assertFalse(found.isEmpty());
    }

    @Test
    void testFindByBreedContainingIgnoreCase() {
        PetCategories category = createCategory();
        createPet("Daisy", "Beagle", new BigDecimal("300.00"), category);
        List<Pets> found = petsRepository.findByBreedContainingIgnoreCase("beag");
        assertFalse(found.isEmpty());
    }

    @Test
    void testFindAllSorted() {
        PetCategories category = createCategory();
        createPet("Pet A", "Breed A", new BigDecimal("100.00"), category);
        createPet("Pet B", "Breed B", new BigDecimal("200.00"), category);
        List<Pets> sorted = petsRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }

    @Test
    void testFindByPriceBetween() {
        PetCategories category = createCategory();
        createPet("Expensive", "Breed", new BigDecimal("1000.00"), category);
        createPet("Cheap", "Breed", new BigDecimal("50.00"), category);
        List<Pets> found = petsRepository.findByPriceBetween(new BigDecimal("10.00"), new BigDecimal("100.00"));
        assertEquals(1, found.size());
    }
}
