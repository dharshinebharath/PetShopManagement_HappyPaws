package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.entity.Pets;
import com.sprint.pet_shop.entity.Supplier;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.PetCategoriesRepository;
import com.sprint.pet_shop.repository.PetsRepository;
import com.sprint.pet_shop.repository.SupplierRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class SupplierRepoTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private PetCategoriesRepository petCategoriesRepository;

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

    private Supplier createSupplier(String name, String email) {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        supplier.setContactPerson("Contact Person");
        supplier.setPhoneNumber("1234567890");
        supplier.setEmail(email);
        supplier.setAddress(createAddress());
        return supplierRepository.save(supplier);
    }

    @Test
    void testCreateSupplier() {
        Supplier supplier = createSupplier("Supplier A", "suppA@example.com");
        assertNotNull(supplier.getSupplierId());
    }

    @Test
    void testFindSupplierById() {
        Supplier supplier = createSupplier("Supplier B", "suppB@example.com");
        Optional<Supplier> found = supplierRepository.findById(supplier.getSupplierId());
        assertTrue(found.isPresent());
        assertEquals("Supplier B", found.get().getName());
    }

    @Test
    void testUpdateSupplier() {
        Supplier supplier = createSupplier("Supplier C", "suppC@example.com");
        supplier.setContactPerson("New Contact");
        Supplier updated = supplierRepository.save(supplier);
        assertEquals("New Contact", updated.getContactPerson());
    }

    @Test
    void testDeleteSupplier() {
        Supplier supplier = createSupplier("Supplier D", "suppD@example.com");
        supplierRepository.delete(supplier);
        Optional<Supplier> found = supplierRepository.findById(supplier.getSupplierId());
        assertFalse(found.isPresent());
    }

    @Test
    void testExistsByEmail() {
        createSupplier("Supplier E", "suppE@example.com");
        assertTrue(supplierRepository.existsByEmail("suppE@example.com"));
        assertFalse(supplierRepository.existsByEmail("unknown@example.com"));
    }

    @Test
    void testFindAllSorted() {
        createSupplier("Supplier F", "suppF@example.com");
        createSupplier("Supplier G", "suppG@example.com");
        List<Supplier> sorted = supplierRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }

    @Test
    void testFindSuppliersByPetName() {
        Supplier supplier = createSupplier("Supplier H", "suppH@example.com");

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
        
        List<Supplier> suppliers = new ArrayList<>();
        suppliers.add(supplier);
        pet.setSuppliers(suppliers);
        
        petsRepository.save(pet);

        List<Supplier> found = supplierRepository.findSuppliersByPetName("Rex");
        assertEquals(1, found.size());
    }
}
