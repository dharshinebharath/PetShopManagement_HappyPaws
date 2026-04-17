package com.sprint.pet_shop;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.sprint.pet_shop.entity.Addresses;
public class AddressesTest {
    //Test 1: Normal values
    @Test
    void testSetAndGetValues() {
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
    // Test 2: Null values
    @Test
    void testNullValues() {
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
    // Test 3: Empty strings
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
    //Test 4: Max length values
    @Test
    void testMaxLengthValues() {
        Addresses address = new Addresses();
        String longStreet = "A".repeat(255);
        String longCity = "B".repeat(100);
        String longState = "C".repeat(50);
        String longZip = "1".repeat(20);
        address.setStreet(longStreet);
        address.setCity(longCity);
        address.setState(longState);
        address.setZipCode(longZip);
        assertEquals(255, address.getStreet().length());
        assertEquals(100, address.getCity().length());
        assertEquals(50, address.getState().length());
        assertEquals(20, address.getZipCode().length());
    }
    //Test 5: Invalid-like values
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
