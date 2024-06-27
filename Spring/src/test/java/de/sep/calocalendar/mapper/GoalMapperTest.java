package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.Goal;
import de.sep.calocalendar.model.GoalModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoalMapperTest {

    private final GoalMapper mapper = new GoalMapperImpl();

    private Goal entity;
    private GoalModel model;

    @BeforeEach
    void Setup() {
        model = new GoalModel();
        model.setId(1L);
        model.setGoalWeight(70000);
        model.setGoalSpeed(10);

        entity = new Goal();
        entity.setId(1L);
        entity.setGoalWeight(70000);
        entity.setGoalSpeed(10);
    }

    @Test
    void toEntity_WhenModelProvided_ThenCorrectEntity() {
        var actual = mapper.toEntity(model);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(entity.getId());
        assertThat(actual.getGoalWeight()).isEqualTo(entity.getGoalWeight());
        assertThat(actual.getGoalSpeed()).isEqualTo(entity.getGoalSpeed());
    }

    @Test
    void toModel_WhenEntityProvided_ThenCorrectModel() {
        var actual = mapper.toModel(entity);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(model.getId());
        assertThat(actual.getGoalWeight()).isEqualTo(model.getGoalWeight());
        assertThat(actual.getGoalSpeed()).isEqualTo(model.getGoalSpeed());
    }
}
