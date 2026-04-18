package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Addresses;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressesRepoTest {

    // 1️⃣ POSITIVE: Valid Address
    @Test
    void testValidAddress() {
        Addresses address = new Addresses();
        address.setAddressId(1L);
        address.setStreet("123 Main Street");
        address.setCity("Chennai");
        address.setState("Tamil Nadu");
        address.setZipCode("600001");

        assertEquals(1L, address.getAddressId());
        assertEquals("123 Main Street", address.getStreet());
        assertEquals("Chennai", address.getCity());
        assertEquals("Tamil Nadu", address.getState());
        assertEquals("600001", address.getZipCode());
    }

    // 2️⃣ EDGE: Null values allowed (no validation in entity)
    @Test
    void testNullValuesAllowed() {
        Addresses address = new Addresses();

        address.setStreet(null);
        address.setCity(null);
        address.setState(null);
        address.setZipCode(null);

        assertNull(address.getStreet());
        assertNull(address.getCity());
        assertNull(address.getState());
        assertNull(address.getZipCode());
    }

    // 3️⃣ EDGE: Empty strings accepted (since no @NotBlank)
    @Test
    void testEmptyValues() {
        Addresses address = new Addresses();

        address.setStreet("");
        address.setCity("");
        address.setState("");
        address.setZipCode("");

        assertEquals("", address.getStreet());
        assertEquals("", address.getCity());
        assertEquals("", address.getState());
        assertEquals("", address.getZipCode());
    }

    // 4️⃣ BOUNDARY: Max length simulation
    @Test
    void testMaxLengthValues() {
        Addresses address = new Addresses();

        String street = "A".repeat(255);
        String city = "B".repeat(100);
        String state = "C".repeat(50);
        String zip = "9".repeat(20);

        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zip);

        assertEquals(255, address.getStreet().length());
        assertEquals(100, address.getCity().length());
        assertEquals(50, address.getState().length());
        assertEquals(20, address.getZipCode().length());
    }

    // 5️⃣ NEGATIVE STYLE: Special characters handling
    @Test
    void testSpecialCharacterValues() {
        Addresses address = new Addresses();

        address.setStreet("@@@ ### !!!");
        address.setCity("Chennai@123");
        address.setState("TN$%^");
        address.setZipCode("6000@#");

        assertEquals("@@@ ### !!!", address.getStreet());
        assertEquals("Chennai@123", address.getCity());
        assertEquals("TN$%^", address.getState());
        assertEquals("6000@#", address.getZipCode());
    }
}