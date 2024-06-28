package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.GroceryItem;
import de.sep.calocalendar.mapper.GroceryItemMapper;
import de.sep.calocalendar.model.GroceryItemModel;
import de.sep.calocalendar.repository.GroceryItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GroceryItemServiceTest {

    @Mock
    private GroceryItemRepository groceryItemRepository;

    @Mock
    private GroceryItemMapper groceryItemMapper;

    @InjectMocks
    private GroceryItemService groceryItemService;

    private GroceryItem groceryItem;
    private GroceryItemModel groceryItemModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        groceryItem = new GroceryItem();
        groceryItem.setId(1L);
        groceryItem.setName("Apple");
        groceryItem.setCalories(95);

        groceryItemModel = new GroceryItemModel();
        groceryItemModel.setId(1L);
        groceryItemModel.setName("Apple");
        groceryItemModel.setCalories(95);
    }

    @Test
    void testGetAllGroceryItems() {
        when(groceryItemRepository.findAll()).thenReturn(Arrays.asList(groceryItem));
        when(groceryItemMapper.toModel(any(GroceryItem.class))).thenReturn(groceryItemModel);

        Optional<List<GroceryItemModel>> groceryItemModels = groceryItemService.getAllGroceryItems();

        assertThat(groceryItemModels).isPresent();
        assertThat(groceryItemModels.get()).hasSize(1);
        verify(groceryItemRepository, times(1)).findAll();
    }

    @Test
    void testGetGroceryItemById() {
        when(groceryItemRepository.findById(1L)).thenReturn(Optional.of(groceryItem));
        when(groceryItemMapper.toModel(any(GroceryItem.class))).thenReturn(groceryItemModel);

        Optional<GroceryItemModel> fetchedGroceryItemModel = groceryItemService.getGroceryItemById(1L);

        assertThat(fetchedGroceryItemModel).isPresent();
        assertThat(fetchedGroceryItemModel.get().getName()).isEqualTo("Apple");
        verify(groceryItemRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateGroceryItem() {
        groceryItemModel.setId(null);
        when(groceryItemRepository.save(any(GroceryItem.class))).thenReturn(groceryItem);
        when(groceryItemMapper.toEntity(any(GroceryItemModel.class))).thenReturn(groceryItem);

        Optional<Long> groceryItemId = groceryItemService.createGroceryItem(groceryItemModel);

        assertThat(groceryItemId).isPresent();
        assertThat(groceryItemId.get()).isEqualTo(groceryItem.getId());
        verify(groceryItemRepository, times(1)).save(any(GroceryItem.class));
    }

    @Test
    void testUpdateGroceryItem() {
        when(groceryItemRepository.findById(1L)).thenReturn(Optional.of(groceryItem));
        when(groceryItemRepository.save(any(GroceryItem.class))).thenReturn(groceryItem);
        when(groceryItemMapper.toEntity(any(GroceryItemModel.class))).thenReturn(groceryItem);
        when(groceryItemMapper.toModel(any(GroceryItem.class))).thenReturn(groceryItemModel);

        Optional<GroceryItemModel> updatedGroceryItemModel = groceryItemService.updateGroceryItem(1L, groceryItemModel);

        assertThat(updatedGroceryItemModel).isPresent();
        assertThat(updatedGroceryItemModel.get().getName()).isEqualTo("Apple");
        verify(groceryItemRepository, times(1)).findById(1L);
        verify(groceryItemRepository, times(1)).save(any(GroceryItem.class));
    }

    @Test
    void testDeleteGroceryItem() {
        when(groceryItemRepository.existsById(1L)).thenReturn(true);

        groceryItemService.deleteGroceryItem(1L);

        verify(groceryItemRepository, times(1)).deleteById(1L);
    }
}
