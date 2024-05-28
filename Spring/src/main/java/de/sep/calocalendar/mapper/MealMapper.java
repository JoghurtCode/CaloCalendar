package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.Meal;
import de.sep.calocalendar.model.MealModel;
import org.mapstruct.Mapper;

@Mapper
public interface MealMapper {

    Meal toEntity(MealModel model);

    MealModel toModel(Meal entity);

}
