package com.sprint.pet_shop.repoTest;

import com.sprint.pet_shop.entity.PetCategories;
import com.sprint.pet_shop.repository.PetCategoriesRepository;
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
class PetCategoriesRepoTest {

    @Autowired
    private PetCategoriesRepository petCategoriesRepository;

    @Autowired
    private EntityManager entityManager;

    private PetCategories createPetCategory(String name) {
        PetCategories category = new PetCategories();
        category.setName(name);
        return petCategoriesRepository.save(category);
    }

    @Test
    void testCreatePetCategory() {
        PetCategories category = createPetCategory("Dogs");
        assertNotNull(category.getCategory_id());
    }

    @Test
    void testFindPetCategoryById() {
        PetCategories category = createPetCategory("Cats");
        Optional<PetCategories> found = petCategoriesRepository.findById(category.getCategory_id());
        assertTrue(found.isPresent());
        assertEquals("Cats", found.get().getName());
    }

    @Test
    void testUpdatePetCategory() {
        PetCategories category = createPetCategory("Birds");
        category.setName("Parrots");
        PetCategories updated = petCategoriesRepository.save(category);
        assertEquals("Parrots", updated.getName());
    }

    @Test
    void testDeletePetCategory() {
        PetCategories category = createPetCategory("Fish");
        petCategoriesRepository.delete(category);
        Optional<PetCategories> found = petCategoriesRepository.findById(category.getCategory_id());
        assertFalse(found.isPresent());
    }

    @Test
    void testFindAllSorted() {
        createPetCategory("Reptiles");
        createPetCategory("Rodents");
        List<PetCategories> sorted = petCategoriesRepository.findAllSorted();
        assertTrue(sorted.size() >= 2);
    }
}
