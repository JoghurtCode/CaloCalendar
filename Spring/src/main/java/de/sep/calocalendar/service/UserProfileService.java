package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.UserProfile;
import de.sep.calocalendar.mapper.UserProfileMapper;
import de.sep.calocalendar.mapper.UserProfileMapperImpl;
import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserProfileMapper mapper = new UserProfileMapperImpl();

    public Optional<List<UserProfileModel>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        List<UserProfileModel> userProfileModels = userProfiles.stream()
                .map(mapper::toModel)
                .toList();
        return Optional.of(userProfileModels);
    }

    public Optional<UserProfileModel> getUserProfileById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find UserProfile with id " + id));
        UserProfileModel userProfileModel = mapper.toModel(userProfile);
        return Optional.of(userProfileModel);
    }

    public Optional<Long> createUserProfile(UserProfileModel userProfileModel) {
        if (userProfileModel.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new UserProfile");
        }
        UserProfile userProfile = mapper.toEntity(userProfileModel);
        userProfile = userProfileRepository.save(userProfile);
        return Optional.of(userProfile.getId());
    }

    public Optional<UserProfileModel> updateUserProfile(Long id, UserProfileModel userProfileModel) {
        UserProfile existingUserProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find UserProfile with id " + id));

        updateEntityFromModel(userProfileModel, existingUserProfile);
        UserProfile updatedUserProfile = userProfileRepository.save(existingUserProfile);
        UserProfileModel updatedModel = mapper.toModel(updatedUserProfile);
        return Optional.of(updatedModel);
    }

    public void deleteUserProfile(Long id) {
        if (userProfileRepository.existsById(id)) {
            userProfileRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Could not find UserProfile by id: " + id);
        }
    }

    private void updateEntityFromModel(UserProfileModel model, UserProfile entity) {
        entity.setUserName(model.getUserName());
        entity.setGender(model.getGender());
        entity.setAge(model.getAge());
        entity.setWeight(model.getWeight());
        entity.setLevelOfPhysicalActivity(model.getLevelOfPhysicalActivity());
    }
}
