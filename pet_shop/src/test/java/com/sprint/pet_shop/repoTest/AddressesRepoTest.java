package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.repository.AddressesRepository;
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
class AddressesRepoTest {

    @Autowired
    private AddressesRepository addressesRepository;

    private Addresses createAddress(String street, String city) {
        Addresses address = new Addresses();
        address.setStreet(street);
        address.setCity(city);
        address.setState("State");
        address.setZipCode("12345");
        return addressesRepository.save(address);
    }

    @Test
    void testCreateAddress() {
        Addresses address = createAddress("123 Main St", "City");
        assertNotNull(address.getAddressId());
    }

    @Test
    void testFindAddressById() {
        Addresses address = createAddress("456 Elm St", "Town");
        Optional<Addresses> found = addressesRepository.findById(address.getAddressId());
        assertTrue(found.isPresent());
        assertEquals("456 Elm St", found.get().getStreet());
    }

    @Test
    void testUpdateAddress() {
        Addresses address = createAddress("789 Oak St", "Village");
        address.setCity("New Village");
        Addresses updated = addressesRepository.save(address);
        assertEquals("New Village", updated.getCity());
    }

    @Test
    void testDeleteAddress() {
        Addresses address = createAddress("101 Pine St", "Hamlet");
        addressesRepository.delete(address);
        Optional<Addresses> found = addressesRepository.findById(address.getAddressId());
        assertFalse(found.isPresent());
    }

    @Test
    void testGetAddressesByCity() {
        createAddress("111 Cedar St", "TargetCity");
        createAddress("222 Birch St", "TargetCity");
        List<Addresses> found = addressesRepository.getAddressesByCity("TargetCity");
        assertEquals(2, found.size());
    }

}
