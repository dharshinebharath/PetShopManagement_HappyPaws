package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.Vaccinations;
import com.sprint.pet_shop.repository.VaccinationsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class VaccinationsRepoTest {

    @Autowired
    private VaccinationsRepository vaccinationsRepository;

    private Vaccinations createVaccination(String name, BigDecimal price) {
        Vaccinations vaccination = new Vaccinations();
        vaccination.setName(name);
        vaccination.setDescription("Description for " + name);
        vaccination.setPrice(price);
        vaccination.setAvailable(true);
        return vaccinationsRepository.save(vaccination);
    }

    @Test
    void testCreateVaccination() {
        Vaccinations vaccination = createVaccination("Rabies", new BigDecimal("50.00"));
        assertNotNull(vaccination.getVaccinationId());
    }

    @Test
    void testFindVaccinationById() {
        Vaccinations vaccination = createVaccination("Parvovirus", new BigDecimal("60.00"));
        Optional<Vaccinations> found = vaccinationsRepository.findById(vaccination.getVaccinationId());
        assertTrue(found.isPresent());
        assertEquals("Parvovirus", found.get().getName());
    }

    @Test
    void testUpdateVaccination() {
        Vaccinations vaccination = createVaccination("Distemper", new BigDecimal("40.00"));
        vaccination.setPrice(new BigDecimal("45.00"));
        Vaccinations updated = vaccinationsRepository.save(vaccination);
        assertEquals(new BigDecimal("45.00"), updated.getPrice());
    }

    @Test
    void testDeleteVaccination() {
        Vaccinations vaccination = createVaccination("Hepatitis", new BigDecimal("35.00"));
        vaccinationsRepository.delete(vaccination);
        Optional<Vaccinations> found = vaccinationsRepository.findById(vaccination.getVaccinationId());
        assertFalse(found.isPresent());
    }

    @Test
    void testExistsByName() {
        createVaccination("Lyme Disease", new BigDecimal("55.00"));
        assertTrue(vaccinationsRepository.existsByName("Lyme Disease"));
        assertFalse(vaccinationsRepository.existsByName("Unknown Vaccine"));
    }

    @Test
    void testFindByPriceRange() {
        createVaccination("Vaccine A", new BigDecimal("20.00"));
        createVaccination("Vaccine B", new BigDecimal("100.00"));
        List<Vaccinations> found = vaccinationsRepository.findByPriceRange(new BigDecimal("10.00"),
                new BigDecimal("30.00"));
        assertTrue(
                found.stream().allMatch(
                        s -> s.getPrice().compareTo(new BigDecimal("10.00")) >= 0 &&
                                s.getPrice().compareTo(new BigDecimal("30.00")) <= 0));

    }

    @Test
    void testFindAllSorted() {
        createVaccination("Vaccine C", new BigDecimal("25.00"));
        createVaccination("Vaccine D", new BigDecimal("30.00"));
        List<Vaccinations> sorted = vaccinationsRepository.findAllSorted();
        List<Long> actualIds = sorted.stream()
                .map(Vaccinations::getVaccinationId)
                .toList();

        List<Long> expectedIds = new ArrayList<Long>(actualIds);
        Collections.sort(expectedIds);
        assertEquals(expectedIds, actualIds);
    }
}
