package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Addresses;
import com.sprint.pet_shop.entity.Employees;
import com.sprint.pet_shop.repository.AddressesRepository;
import com.sprint.pet_shop.repository.EmployeesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class EmployeesRepoTest {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private AddressesRepository addressesRepository;


    private Addresses createAddress() {
        Addresses address = new Addresses();
        address.setStreet("123 Main St");
        address.setCity("City");
        address.setState("State");
        address.setZipCode("12345");
        return addressesRepository.save(address);
    }

    private Employees createEmployee(String firstName, String email, String position) {
        Employees employee = new Employees();
        employee.setFirstName(firstName);
        employee.setLastName("Smith");
        employee.setPosition(position);
        employee.setHireDate(LocalDate.now());
        employee.setPhoneNumber("1234567890");
        employee.setEmail(email);
        employee.setAddress(createAddress());
        return employeesRepository.save(employee);
    }

    @Test
    void testCreateEmployee() {
        Employees emp = createEmployee("John", "john.emp@example.com", "Manager");
        assertNotNull(emp.getEmployeeId());
    }

    @Test
    void testFindEmployeeById() {
        Employees emp = createEmployee("Jane", "jane.emp@example.com", "Clerk");
        Optional<Employees> found = employeesRepository.findById(emp.getEmployeeId());
        assertTrue(found.isPresent());
        assertEquals("Jane", found.get().getFirstName());
    }

    @Test
    void testUpdateEmployee() {
        Employees emp = createEmployee("Alice", "alice.emp@example.com", "Vet");
        emp.setPosition("Senior Vet");
        Employees updated = employeesRepository.save(emp);
        assertEquals("Senior Vet", updated.getPosition());
    }

    @Test
    void testDeleteEmployee() {
        Employees emp = createEmployee("Bob", "bob.emp@example.com", "Cleaner");
        employeesRepository.delete(emp);
        Optional<Employees> found = employeesRepository.findById(emp.getEmployeeId());
        assertFalse(found.isPresent());
    }

    @Test
    void testFindEmployeesByPosition() {
        createEmployee("Charlie", "charlie.emp@example.com", "Guard");
        List<Employees> found = employeesRepository.findEmployeesByPosition("Guard");
        assertFalse(found.isEmpty());
    }

    @Test
    void testExistsByEmail() {
        createEmployee("Dave", "dave.emp@example.com", "Driver");
        assertTrue(employeesRepository.existsByEmail("dave.emp@example.com"));
        assertFalse(employeesRepository.existsByEmail("unknown@example.com"));
    }

    @Test
    void testFindByHireDateAfter() {
        createEmployee("Eve", "eve.emp@example.com", "Cashier");
        List<Employees> found = employeesRepository.findByHireDateAfter(LocalDate.now().minusDays(1));
        assertFalse(found.isEmpty());
    }

    @Test
    void testFindAllByOrderByEmployeeIdAsc() {
        createEmployee("Frank", "frank.emp@example.com", "Staff");
        createEmployee("Grace", "grace.emp@example.com", "Staff");
        List<Employees> sorted = employeesRepository.findAllByOrderByEmployeeIdAsc();
        assertTrue(sorted.size() >= 2);
    }
}
