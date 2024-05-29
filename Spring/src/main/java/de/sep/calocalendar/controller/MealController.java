package de.sep.calocalendar.controller;

import de.sep.calocalendar.model.MealModel;
import de.sep.calocalendar.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @GetMapping
    public ResponseEntity<List<MealModel>> getAllMeals() {
        return ResponseEntity.of(mealService.getAllMeals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealModel> getMealById(@PathVariable Long id) {
        return ResponseEntity.of(mealService.getMealById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createMeal(@RequestBody MealModel mealModel) {
        return ResponseEntity.of(mealService.createMeal(mealModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealModel> updateMeal(@PathVariable Long id, @RequestBody MealModel mealModel) {
        return ResponseEntity.of(mealService.updateMeal(id, mealModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
