package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.PetFood;
import com.sprint.pet_shop.repository.PetFoodRepository;
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
class PetFoodRepoTest {

    @Autowired
    private PetFoodRepository petFoodRepository;

    @Autowired
    private EntityManager entityManager;

    private PetFood createPetFood(String name, String brand) {
        PetFood food = new PetFood();
        food.setName(name);
        food.setBrand(brand);
        food.setType("Dry");
        food.setQuantity(100);
        food.setPrice(new BigDecimal("25.00"));
        return petFoodRepository.save(food);
    }

    @Test
    void testCreatePetFood() {
        PetFood food = createPetFood("Premium Kibble", "BrandX");
        assertNotNull(food.getFoodId());
    }

    @Test
    void testFindPetFoodById() {
        PetFood food = createPetFood("Wet Food", "BrandY");
        Optional<PetFood> found = petFoodRepository.findById(food.getFoodId());
        assertTrue(found.isPresent());
        assertEquals("Wet Food", found.get().getName());
    }

    @Test
    void testUpdatePetFood() {
        PetFood food = createPetFood("Puppy Chow", "BrandZ");
        food.setPrice(new BigDecimal("30.00"));
        PetFood updated = petFoodRepository.save(food);
        assertEquals(new BigDecimal("30.00"), updated.getPrice());
    }

    @Test
    void testDeletePetFood() {
        PetFood food = createPetFood("Senior Diet", "BrandA");
        petFoodRepository.delete(food);
        Optional<PetFood> found = petFoodRepository.findById(food.getFoodId());
        assertFalse(found.isPresent());
    }

    @Test
    void testGetAllPetFood() {
        createPetFood("Food 1", "Brand1");
        List<PetFood> all = petFoodRepository.getAllPetFood();
        assertFalse(all.isEmpty());
    }

    @Test
    void testFindAllSorted() {
        createPetFood("Food 2", "Brand2");
        createPetFood("Food 3", "Brand3");
        List<PetFood> sorted = petFoodRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }

    @Test
    void testExistsByNameAndBrand() {
        createPetFood("Specific Food", "Specific Brand");
        assertTrue(petFoodRepository.existsByNameAndBrand("Specific Food", "Specific Brand"));
        assertFalse(petFoodRepository.existsByNameAndBrand("Unknown Food", "Specific Brand"));
    }
}
