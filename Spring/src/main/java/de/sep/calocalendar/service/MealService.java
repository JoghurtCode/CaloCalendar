package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.Meal;
import de.sep.calocalendar.model.MealModel;
import de.sep.calocalendar.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public Optional<List<MealModel>> getAllMeals() {
        List<Meal> meals = mealRepository.findAll();
        List<MealModel> mealModels = meals.stream()
                .map(this::toModel)
                .toList();
        return Optional.of(mealModels);
    }

    public Optional<MealModel> getMealById(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find Meal with id " + id));
        MealModel mealModel = toModel(meal);
        return Optional.of(mealModel);
    }

    public Optional<Long> createMeal(MealModel mealModel) {
        if (mealModel.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new Meal");
        }
        Meal meal = toEntity(mealModel);
        meal = mealRepository.save(meal);
        return Optional.of(meal.getId());
    }

    public Optional<MealModel> updateMeal(Long id, MealModel mealModel) {
        Meal existingMeal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find Meal with id " + id));

        updateEntityFromModel(mealModel, existingMeal);
        Meal updatedMeal = mealRepository.save(existingMeal);
        MealModel updatedModel = toModel(updatedMeal);
        return Optional.of(updatedModel);
    }

    public void deleteMeal(Long id) {
        if (mealRepository.existsById(id)) {
            mealRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Could not find Meal by id: " + id);
        }
    }

    private MealModel toModel(Meal meal) {
        MealModel model = new MealModel();
        model.setId(meal.getId());
        model.setMealName(meal.getMealName());
        return model;
    }

    private Meal toEntity(MealModel model) {
        Meal entity = new Meal();
        entity.setMealName(model.getMealName());
        return entity;
    }

    private void updateEntityFromModel(MealModel model, Meal entity) {
        entity.setMealName(model.getMealName());
    }
}
