package de.sep.calocalendar.service;

import de.sep.calocalendar.entities.UserProfile;
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

    public Optional<List<UserProfileModel>> getAllUserProfiles() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        List<UserProfileModel> userProfileModels = userProfiles.stream()
                .map(this::toModel)
                .toList();
        return Optional.of(userProfileModels);
    }

    public Optional<UserProfileModel> getUserProfileById(Long id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find UserProfile with id " + id));
        UserProfileModel userProfileModel = toModel(userProfile);
        return Optional.of(userProfileModel);
    }

    public Optional<Long> createUserProfile(UserProfileModel userProfileModel) {
        if (userProfileModel.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new UserProfile");
        }
        UserProfile userProfile = toEntity(userProfileModel);
        userProfile = userProfileRepository.save(userProfile);
        return Optional.of(userProfile.getId());
    }

    public Optional<UserProfileModel> updateUserProfile(Long id, UserProfileModel userProfileModel) {
        UserProfile existingUserProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find UserProfile with id " + id));

        updateEntityFromModel(userProfileModel, existingUserProfile);
        UserProfile updatedUserProfile = userProfileRepository.save(existingUserProfile);
        UserProfileModel updatedModel = toModel(updatedUserProfile);
        return Optional.of(updatedModel);
    }

    public void deleteUserProfile(Long id) {
        if (userProfileRepository.existsById(id)) {
            userProfileRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Could not find UserProfile by id: " + id);
        }
    }

    private UserProfileModel toModel(UserProfile userProfile) {
        UserProfileModel model = new UserProfileModel();
        model.setId(userProfile.getId());
        model.setUserName(userProfile.getUserName());
        model.setGender(userProfile.getGender());
        model.setAge(userProfile.getAge());
        model.setWeight(userProfile.getWeight());
        model.setLevelOfPhysicalActivity(userProfile.getLevelOfPhysicalActivity());
        return model;
    }

    private UserProfile toEntity(UserProfileModel model) {
        UserProfile entity = new UserProfile();
        entity.setUserName(model.getUserName());
        entity.setGender(model.getGender());
        entity.setAge(model.getAge());
        entity.setWeight(model.getWeight());
        entity.setLevelOfPhysicalActivity(model.getLevelOfPhysicalActivity());
        return entity;
    }

    private void updateEntityFromModel(UserProfileModel model, UserProfile entity) {
        entity.setUserName(model.getUserName());
        entity.setGender(model.getGender());
        entity.setAge(model.getAge());
        entity.setWeight(model.getWeight());
        entity.setLevelOfPhysicalActivity(model.getLevelOfPhysicalActivity());
    }
}
