package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.UserProfile;
import de.sep.calocalendar.model.UserProfileModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = GoalMapper.class)
public interface UserProfileMapper {
    @Mapping(source = "goal", target = "goal")
    UserProfile toEntity(UserProfileModel model);

    @Mapping(source = "goal", target = "goal")
    UserProfileModel toModel(UserProfile entity);
}
