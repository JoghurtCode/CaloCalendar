package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.Goal;
import de.sep.calocalendar.model.GoalModel;
import de.sep.calocalendar.repository.GoalRepository;
import de.sep.calocalendar.mapper.GoalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private GoalMapper goalMapper;

    public Optional<GoalModel> createGoal(GoalModel goalModel) {
        Goal goal = goalMapper.toEntity(goalModel);
        goal = goalRepository.save(goal);
        return Optional.of(goalMapper.toModel(goal));
    }

    public Optional<GoalModel> getGoalById(Long id) {
        return goalRepository.findById(id).map(goalMapper::toModel);
    }

    public Optional<GoalModel> updateGoal(Long id, GoalModel goalModel) {
        if (!goalRepository.existsById(id)) {
            return Optional.empty();
        }
        Goal goal = goalMapper.toEntity(goalModel);
        goal.setId(id);
        goal = goalRepository.save(goal);
        return Optional.of(goalMapper.toModel(goal));
    }

    public void deleteGoal(Long id) {
        if (goalRepository.existsById(id)) {
            goalRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Goal with id: " + id + " does not exist");
        }
    }

    public List<GoalModel> getAllGoals() {
        return goalRepository.findAll().stream()
                .map(goalMapper::toModel)
                .collect(Collectors.toList());
    }
}
