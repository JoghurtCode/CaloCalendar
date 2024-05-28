package de.sep.calocalendar.controller;

//import de.sep.calocalendar.model.GroceryItem;
import de.sep.calocalendar.repository.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groceryitems")
public class GroceryItemController {

    @Autowired
    private GroceryItemRepository groceryItemRepository;
/*
    @GetMapping
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public GroceryItem getGroceryItemById(@PathVariable Long id) {
        return groceryItemRepository.findById(id).orElse(null);
    }

    @PostMapping
    public GroceryItem createGroceryItem(@RequestBody GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @PutMapping("/{id}")
    public GroceryItem updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItemDetails) {
        GroceryItem groceryItem = groceryItemRepository.findById(id).orElse(null);
        if (groceryItem != null) {
            groceryItem.setItemName(groceryItemDetails.getItemName());
            groceryItem.setCalories(groceryItemDetails.getCalories());
            return groceryItemRepository.save(groceryItem);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteGroceryItem(@PathVariable Long id) {
        groceryItemRepository.deleteById(id);
    }*/
}
