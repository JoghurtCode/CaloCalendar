package de.sep.calocalendar.repository;

import de.sep.calocalendar.entities.GroceryItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class GroceryItemRepositoryTest {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    private GroceryItem groceryItem;

    @BeforeEach
    public void setUp() {
        groceryItem = new GroceryItem();
        groceryItem.setName("TestGrocery");
        groceryItem.setWeight(100);
        groceryItem.setCalories(50);
    }

    @Test
    public void testCreateGroceryItem() {
        GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
        assertThat(savedGroceryItem).isNotNull();
        assertThat(savedGroceryItem.getId()).isNotNull();
    }

    @Test
    public void testFindGroceryItemById() {
        GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
        Optional<GroceryItem> fetchedGroceryItem = groceryItemRepository.findById(savedGroceryItem.getId());
        assertThat(fetchedGroceryItem).isPresent();
        assertThat(fetchedGroceryItem.get().getName()).isEqualTo(groceryItem.getName());
    }

    @Test
    public void testFindAllGroceryItems() {
        groceryItemRepository.save(groceryItem);

        GroceryItem anotherGroceryItem = new GroceryItem();
        anotherGroceryItem.setName("TestGrocery2");
        anotherGroceryItem.setWeight(150);
        anotherGroceryItem.setCalories(75);

        groceryItemRepository.save(anotherGroceryItem);

        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        assertThat(groceryItems).hasSize(2);
    }

    @Test
    public void testUpdateGroceryItem() {
        GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
        savedGroceryItem.setName("UpdatedGrocery");
        GroceryItem updatedGroceryItem = groceryItemRepository.save(savedGroceryItem);
        assertThat(updatedGroceryItem.getName()).isEqualTo("UpdatedGrocery");
    }

    @Test
    public void testDeleteGroceryItem() {
        GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
        groceryItemRepository.deleteById(savedGroceryItem.getId());
        Optional<GroceryItem> deletedGroceryItem = groceryItemRepository.findById(savedGroceryItem.getId());
        assertThat(deletedGroceryItem).isNotPresent();
    }
}
