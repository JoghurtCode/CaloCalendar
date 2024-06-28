package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.Goal;
import de.sep.calocalendar.model.GoalModel;
import de.sep.calocalendar.repository.GoalRepository;
import de.sep.calocalendar.mapper.GoalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GoalServiceTest {

    @Mock
    private GoalRepository goalRepository;
    @Mock
    private GoalMapper goalMapper;

    @InjectMocks
    private GoalService goalService;

    private Goal goal;
    private GoalModel goalModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        goal = new Goal();
        goal.setId(1L);
        goal.setGoalWeight(70.0f);
        goal.setGoalSpeed(10);

        goalModel = new GoalModel();
        goalModel.setId(1L);
        goalModel.setGoalWeight(70.0f);
        goalModel.setGoalSpeed(10);
    }

    @Test
    void testCreateGoal() {
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);
        when(goalMapper.toEntity(any(GoalModel.class))).thenReturn(goal);
        when(goalMapper.toModel(any(Goal.class))).thenReturn(goalModel);

        Optional<GoalModel> createdGoal = goalService.createGoal(goalModel);

        assertThat(createdGoal).isPresent();
        assertThat(createdGoal.get().getGoalWeight()).isEqualTo(70.0f);
        verify(goalRepository, times(1)).save(any(Goal.class));
    }

    @Test
    void testGetGoalById() {
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(goalMapper.toModel(any(Goal.class))).thenReturn(goalModel);

        Optional<GoalModel> fetchedGoal = goalService.getGoalById(1L);

        assertThat(fetchedGoal).isPresent();
        assertThat(fetchedGoal.get().getGoalWeight()).isEqualTo(70.0f);
        verify(goalRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateGoal() {
        when(goalRepository.existsById(1L)).thenReturn(true);
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);
        when(goalMapper.toEntity(any(GoalModel.class))).thenReturn(goal);
        when(goalMapper.toModel(any(Goal.class))).thenReturn(goalModel);

        Optional<GoalModel> updatedGoal = goalService.updateGoal(1L, goalModel);

        assertThat(updatedGoal).isPresent();
        assertThat(updatedGoal.get().getGoalWeight()).isEqualTo(70.0f);
        verify(goalRepository, times(1)).existsById(1L);
        verify(goalRepository, times(1)).save(any(Goal.class));
    }

    @Test
    void testDeleteGoal() {
        when(goalRepository.existsById(1L)).thenReturn(true);

        goalService.deleteGoal(1L);

        verify(goalRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllGoals() {
        when(goalRepository.findAll()).thenReturn(List.of(goal));
        when(goalMapper.toModel(any(Goal.class))).thenReturn(goalModel);

        var goals = goalService.getAllGoals();

        assertThat(goals).hasSize(1);
        assertThat(goals.get(0).getGoalWeight()).isEqualTo(70.0f);
        verify(goalRepository, times(1)).findAll();
    }
}
