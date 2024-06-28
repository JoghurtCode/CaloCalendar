package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.Meal;
import de.sep.calocalendar.mapper.MealMapper;
import de.sep.calocalendar.model.MealModel;
import de.sep.calocalendar.repository.MealRepository;
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

public class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private MealMapper mealMapper;

    @InjectMocks
    private MealService mealService;

    private Meal meal;
    private MealModel mealModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        meal = new Meal();
        meal.setId(1L);
        meal.setMealName("Breakfast");

        mealModel = new MealModel();
        mealModel.setId(1L);
        mealModel.setMealName("Breakfast");
    }

    @Test
    void testGetAllMeals() {
        when(mealRepository.findAll()).thenReturn(Arrays.asList(meal));
        when(mealMapper.toModel(any(Meal.class))).thenReturn(mealModel);

        Optional<List<MealModel>> mealModels = mealService.getAllMeals();

        assertThat(mealModels).isPresent();
        assertThat(mealModels.get()).hasSize(1);
        verify(mealRepository, times(1)).findAll();
    }

    @Test
    void testGetMealById() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
        when(mealMapper.toModel(any(Meal.class))).thenReturn(mealModel);

        Optional<MealModel> fetchedMealModel = mealService.getMealById(1L);

        assertThat(fetchedMealModel).isPresent();
        assertThat(fetchedMealModel.get().getMealName()).isEqualTo("Breakfast");
        verify(mealRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateMeal() {
        mealModel.setId(null);
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);
        when(mealMapper.toEntity(any(MealModel.class))).thenReturn(meal);

        Optional<Long> mealId = mealService.createMeal(mealModel);

        assertThat(mealId).isPresent();
        assertThat(mealId.get()).isEqualTo(meal.getId());
        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    @Test
    void testUpdateMeal() {
        when(mealRepository.findById(1L)).thenReturn(Optional.of(meal));
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);
        when(mealMapper.toEntity(any(MealModel.class))).thenReturn(meal);
        when(mealMapper.toModel(any(Meal.class))).thenReturn(mealModel);

        Optional<MealModel> updatedMealModel = mealService.updateMeal(1L, mealModel);

        assertThat(updatedMealModel).isPresent();
        assertThat(updatedMealModel.get().getMealName()).isEqualTo("Breakfast");
        verify(mealRepository, times(1)).findById(1L);
        verify(mealRepository, times(1)).save(any(Meal.class));
    }

    @Test
    void testDeleteMeal() {
        when(mealRepository.existsById(1L)).thenReturn(true);

        mealService.deleteMeal(1L);

        verify(mealRepository, times(1)).deleteById(1L);
    }
}
