package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.Meal;
import de.sep.calocalendar.model.MealModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MealMapperTest {

    private final MealMapper mapper = new MealMapperImpl();

    private MealModel model;
    private Meal entity;

    @BeforeEach
    void Setup() {

        model = new MealModel();
        model.setId(1L);
        model.setMealName("TestMeal");

        entity = new Meal();
        entity.setId(1L);
        entity.setMealName("TestMeal");
    }

    @Test
    void toEntity_WhenModelProvided_ThenCorrectEntity() {
        var actual = mapper.toEntity(model);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(entity.getId());
        assertThat(actual.getMealName()).isEqualTo(entity.getMealName());
    }

    @Test
    void toModel_WhenEntityProvided_ThenCorrectModel() {
        var actual = mapper.toModel(entity);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(model.getId());
        assertThat(actual.getMealName()).isEqualTo(model.getMealName());
    }
}
