package de.sep.calocalendar.controller;

import de.sep.calocalendar.model.Meal;
import de.sep.calocalendar.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealRepository mealRepository;

    @GetMapping
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @GetMapping("/{id}")
    public Meal getMealById(@PathVariable Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Meal createMeal(@RequestBody Meal meal) {
        return mealRepository.save(meal);
    }

    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable Long id, @RequestBody Meal mealDetails) {
        Meal meal = mealRepository.findById(id).orElse(null);
        if (meal != null) {
            meal.setMealName(mealDetails.getMealName());
            meal.setUserProfile(mealDetails.getUserProfile());
            meal.setGroceryItems(mealDetails.getGroceryItems());
            return mealRepository.save(meal);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteMeal(@PathVariable Long id) {
        mealRepository.deleteById(id);
    }
}
