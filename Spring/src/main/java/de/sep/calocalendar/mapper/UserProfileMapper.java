package de.sep.calocalendar.mapper;

import de.sep.calocalendar.entities.UserProfile;
import de.sep.calocalendar.model.UserProfileModel;
import org.mapstruct.Mapper;

@Mapper
public interface UserProfileMapper {

    UserProfile toEntity(UserProfileModel model);

    UserProfileModel toModel(UserProfile entity);
}
