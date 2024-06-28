package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.Goal;
import de.sep.calocalendar.entities.UserProfile;
import de.sep.calocalendar.model.GoalModel;
import de.sep.calocalendar.model.UserProfileModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserProfileMapperTest {

    @InjectMocks
    private UserProfileMapperImpl mapper;

    @Mock
    private GoalMapper goalMapper;

    private UserProfile entity;
    private UserProfileModel model;
    private Goal goalEntity;
    private GoalModel goalModel;

    @BeforeEach
    void Setup() {
        goalModel = new GoalModel();
        goalModel.setId(1L);
        goalModel.setGoalWeight(70000);
        goalModel.setGoalSpeed(10);

        goalEntity = new Goal();
        goalEntity.setId(1L);
        goalEntity.setGoalWeight(70000);
        goalEntity.setGoalSpeed(10);

        model = new UserProfileModel();
        model.setId(1L);
        model.setUserName("TestUser");
        model.setAge(25);
        model.setWeight(75.0F);
        model.setGender(1);
        model.setLevelOfPhysicalActivity(2);
        model.setGoal(goalModel);

        entity = new UserProfile();
        entity.setId(1L);
        entity.setUserName("TestUser");
        entity.setAge(25);
        entity.setWeight(75.0F);
        entity.setGender(1);
        entity.setLevelOfPhysicalActivity(2);
        entity.setGoal(goalEntity);
    }

    @Test
    void toEntity_WhenModelProvided_ThenCorrectEntity() {
        // Mocking the behavior of goalMapper
        when(goalMapper.toEntity(goalModel)).thenReturn(goalEntity);

        var actual = mapper.toEntity(model);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(entity.getId());
        assertThat(actual.getUserName()).isEqualTo(entity.getUserName());
        assertThat(actual.getAge()).isEqualTo(entity.getAge());
        assertThat(actual.getWeight()).isEqualTo(entity.getWeight());
        assertThat(actual.getGender()).isEqualTo(entity.getGender());
        assertThat(actual.getLevelOfPhysicalActivity()).isEqualTo(entity.getLevelOfPhysicalActivity());

        // Test the Goal mapping
        assertThat(actual.getGoal()).isNotNull();
        assertThat(actual.getGoal().getId()).isEqualTo(goalEntity.getId());
        assertThat(actual.getGoal().getGoalWeight()).isEqualTo(goalEntity.getGoalWeight());
        assertThat(actual.getGoal().getGoalSpeed()).isEqualTo(goalEntity.getGoalSpeed());
    }

    @Test
    void toModel_WhenEntityProvided_ThenCorrectModel() {
        // Mocking the behavior of goalMapper
        when(goalMapper.toModel(goalEntity)).thenReturn(goalModel);

        var actual = mapper.toModel(entity);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(model.getId());
        assertThat(actual.getUserName()).isEqualTo(model.getUserName());
        assertThat(actual.getAge()).isEqualTo(model.getAge());
        assertThat(actual.getWeight()).isEqualTo(model.getWeight());
        assertThat(actual.getGender()).isEqualTo(model.getGender());
        assertThat(actual.getLevelOfPhysicalActivity()).isEqualTo(model.getLevelOfPhysicalActivity());

        // Test the Goal mapping
        assertThat(actual.getGoal()).isNotNull();
        assertThat(actual.getGoal().getId()).isEqualTo(goalModel.getId());
        assertThat(actual.getGoal().getGoalWeight()).isEqualTo(goalModel.getGoalWeight());
        assertThat(actual.getGoal().getGoalSpeed()).isEqualTo(goalModel.getGoalSpeed());
    }
}
