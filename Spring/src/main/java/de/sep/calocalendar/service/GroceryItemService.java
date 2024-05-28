package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.GroceryItem;
import de.sep.calocalendar.model.GroceryModel;
import de.sep.calocalendar.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public Optional<List<GroceryModel>> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryItemRepository.findAll();
        List<GroceryModel> groceryModels = groceryItems.stream()
                .map(this::toModel)
                .toList();
        return Optional.of(groceryModels);
    }

    public Optional<GroceryModel> getGroceryItemById(Long id) {
        GroceryItem groceryItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find GroceryItem with id " + id));
        GroceryModel groceryModel = toModel(groceryItem);
        return Optional.of(groceryModel);
    }

    public Optional<Long> createGroceryItem(GroceryModel groceryModel) {
        if (groceryModel.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new GroceryItem");
        }
        GroceryItem groceryItem = toEntity(groceryModel);
        groceryItem = groceryItemRepository.save(groceryItem);
        return Optional.of(groceryItem.getId());
    }

    public Optional<GroceryModel> updateGroceryItem(Long id, GroceryModel groceryModel) {
        GroceryItem existingGroceryItem = groceryItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find GroceryItem with id " + id));

        updateEntityFromModel(groceryModel, existingGroceryItem);
        GroceryItem updatedGroceryItem = groceryItemRepository.save(existingGroceryItem);
        GroceryModel updatedModel = toModel(updatedGroceryItem);
        return Optional.of(updatedModel);
    }

    public void deleteGroceryItem(Long id) {
        if (groceryItemRepository.existsById(id)) {
            groceryItemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Could not find GroceryItem by id: " + id);
        }
    }

    private GroceryModel toModel(GroceryItem groceryItem) {
        GroceryModel model = new GroceryModel();
        model.setId(groceryItem.getId());
        model.setName(groceryItem.getName());
        model.setCals(groceryItem.getCals());
        return model;
    }

    private GroceryItem toEntity(GroceryModel model) {
        GroceryItem entity = new GroceryItem();
        entity.setName(model.getName());
        entity.setCals(model.getCals());
        return entity;
    }

    private void updateEntityFromModel(GroceryModel model, GroceryItem entity) {
        entity.setName(model.getName());
        entity.setCals(model.getCals());
    }
}
