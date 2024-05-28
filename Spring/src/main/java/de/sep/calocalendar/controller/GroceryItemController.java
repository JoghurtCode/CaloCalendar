package de.sep.calocalendar.controller;

import de.sep.calocalendar.model.GroceryModel;
import de.sep.calocalendar.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groceryitems")
public class GroceryItemController {

    @Autowired
    private GroceryItemService groceryItemService;

    @GetMapping
    public ResponseEntity<List<GroceryModel>> getAllGroceryItems() {
        return ResponseEntity.of(groceryItemService.getAllGroceryItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryModel> getGroceryItemById(@PathVariable Long id) {
        return ResponseEntity.of(groceryItemService.getGroceryItemById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createGroceryItem(@RequestBody GroceryModel groceryModel) {
        return ResponseEntity.of(groceryItemService.createGroceryItem(groceryModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryModel> updateGroceryItem(@PathVariable Long id, @RequestBody GroceryModel groceryModel) {
        return ResponseEntity.of(groceryItemService.updateGroceryItem(id, groceryModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id) {
        groceryItemService.deleteGroceryItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
