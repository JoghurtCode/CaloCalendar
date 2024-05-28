package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.GroceryItem;
import de.sep.calocalendar.mapper.GroceryItemMapper;
import de.sep.calocalendar.mapper.GroceryItemMapperImpl;
import de.sep.calocalendar.model.GroceryItemModel;
import de.sep.calocalendar.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private GroceryItemMapper mapper = new GroceryItemMapperImpl();

    public Optional<List<GroceryItemModel>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        List<GroceryItemModel> groceryModels = groceryItems.stream()
                .map(mapper::toModel)
                .toList();
        return Optional.of(groceryModels);
    }

    public Optional<GroceryItemModel> getGroceryItemById(Long id) {
        GroceryItem groceryItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find GroceryItem with id " + id));
        GroceryItemModel groceryModel = mapper.toModel(groceryItem);
        return Optional.of(groceryModel);
    }

    public Optional<Long> createGroceryItem(GroceryItemModel groceryModel) {
        if (groceryModel.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new GroceryItem");
        }
        GroceryItem groceryItem = mapper.toEntity(groceryModel);
        groceryItem = groceryItemRepository.save(groceryItem);
        return Optional.of(groceryItem.getId());
    }

    public Optional<GroceryItemModel> updateGroceryItem(Long id, GroceryItemModel groceryModel) {
        GroceryItem existingGroceryItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find GroceryItem with id " + id));

        updateEntityFromModel(groceryModel, existingGroceryItem);
        GroceryItem updatedGroceryItem = groceryItemRepository.save(existingGroceryItem);
        GroceryItemModel updatedModel = mapper.toModel(updatedGroceryItem);
        return Optional.of(updatedModel);
    }

    public void deleteGroceryItem(Long id) {
        if (groceryItemRepository.existsById(id)) {
            groceryItemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Could not find GroceryItem by id: " + id);
        }
    }

    private void updateEntityFromModel(GroceryItemModel model, GroceryItem entity) {
        entity.setName(model.getName());
        entity.setCalories(model.getCalories());
    }
}
