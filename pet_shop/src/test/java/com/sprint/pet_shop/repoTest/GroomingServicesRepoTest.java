package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.GroomingServices;
import com.sprint.pet_shop.repository.GroomingServicesRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class GroomingServicesRepoTest {

    @Autowired
    private GroomingServicesRepository groomingServicesRepository;

    @Autowired
    private EntityManager entityManager;

    private GroomingServices createGroomingService(String name, BigDecimal price) {
        GroomingServices service = new GroomingServices();
        service.setName(name);
        service.setDescription("Description for " + name);
        service.setPrice(price);
        service.setAvailable(true);
        return groomingServicesRepository.save(service);
    }

    @Test
    void testCreateGroomingService() {
        GroomingServices service = createGroomingService("Basic Wash", new BigDecimal("50.00"));
        assertNotNull(service.getServiceId());
    }

    @Test
    void testFindGroomingServiceById() {
        GroomingServices service = createGroomingService("Premium Wash", new BigDecimal("100.00"));
        Optional<GroomingServices> found = groomingServicesRepository.findById(service.getServiceId());
        assertTrue(found.isPresent());
        assertEquals("Premium Wash", found.get().getName());
    }

    @Test
    void testUpdateGroomingService() {
        GroomingServices service = createGroomingService("Haircut", new BigDecimal("75.00"));
        service.setPrice(new BigDecimal("80.00"));
        GroomingServices updated = groomingServicesRepository.save(service);
        assertEquals(new BigDecimal("80.00"), updated.getPrice());
    }

    @Test
    void testDeleteGroomingService() {
        GroomingServices service = createGroomingService("Nail Clipping", new BigDecimal("20.00"));
        groomingServicesRepository.delete(service);
        Optional<GroomingServices> found = groomingServicesRepository.findById(service.getServiceId());
        assertFalse(found.isPresent());
    }

    @Test
    void testExistsByName() {
        createGroomingService("Ear Cleaning", new BigDecimal("30.00"));
        assertTrue(groomingServicesRepository.existsByName("Ear Cleaning"));
        assertFalse(groomingServicesRepository.existsByName("Unknown Service"));
    }

    @Test
    void testFindServicesByPriceRange() {
        createGroomingService("Service A", new BigDecimal("40.00"));
        createGroomingService("Service B", new BigDecimal("150.00"));
        List<GroomingServices> found = groomingServicesRepository.findServicesByPriceRange(new BigDecimal("30.00"), new BigDecimal("50.00"));
        assertFalse(found.isEmpty());
        assertTrue(found.stream().allMatch(s -> s.getPrice().compareTo(new BigDecimal("50.00")) <= 0));
    }

    @Test
    void testFindAllSorted() {
        createGroomingService("Service C", new BigDecimal("10.00"));
        createGroomingService("Service D", new BigDecimal("20.00"));
        List<GroomingServices> sorted = groomingServicesRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }
}
