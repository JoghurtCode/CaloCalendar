package de.sep.calocalendar.repository;

import de.sep.calocalendar.entities.Meal;
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
public class MealRepositoryTest {

    @Autowired
    private MealRepository mealRepository;

    private Meal meal;

    @BeforeEach
    public void setUp() {
        meal = new Meal();
        meal.setMealName("TestMeal");
    }

    @Test
    public void testCreateMeal() {
        Meal savedMeal = mealRepository.save(meal);
        assertThat(savedMeal).isNotNull();
        assertThat(savedMeal.getId()).isNotNull();
    }

    @Test
    public void testFindMealById() {
        Meal savedMeal = mealRepository.save(meal);
        Optional<Meal> fetchedMeal = mealRepository.findById(savedMeal.getId());
        assertThat(fetchedMeal).isPresent();
        assertThat(fetchedMeal.get().getMealName()).isEqualTo(meal.getMealName());
    }

    @Test
    public void testFindAllMeals() {
        mealRepository.save(meal);

        Meal anotherMeal = new Meal();
        anotherMeal.setMealName("TestMeal2");

        mealRepository.save(anotherMeal);

        List<Meal> meals = mealRepository.findAll();
        assertThat(meals).hasSize(2);
    }

    @Test
    public void testUpdateMeal() {
        Meal savedMeal = mealRepository.save(meal);
        savedMeal.setMealName("UpdatedMeal");
        Meal updatedMeal = mealRepository.save(savedMeal);
        assertThat(updatedMeal.getMealName()).isEqualTo("UpdatedMeal");
    }

    @Test
    public void testDeleteMeal() {
        Meal savedMeal = mealRepository.save(meal);
        mealRepository.deleteById(savedMeal.getId());
        Optional<Meal> deletedMeal = mealRepository.findById(savedMeal.getId());
        assertThat(deletedMeal).isNotPresent();
    }
}
