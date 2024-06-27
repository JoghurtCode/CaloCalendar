package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.Goal;
import de.sep.calocalendar.model.GoalModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    Goal toEntity(GoalModel model);
    GoalModel toModel(Goal entity);
}
