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
    private UserProfileRepository repo;

    @Autowired
    private final UserProfileMapper mapper = new UserProfileMapperImpl();

    public Optional<Long> addUserProfile(UserProfileModel model){
        if(model.getId() != null) throw new IllegalArgumentException("Don't give an id!");

        var entity = mapper.toEntity(model);
        entity = repo.save(entity);

        return Optional.of(entity.getId());
    }

    public Optional<List<UserProfileModel>> getAllUserProfiles() {
        List<UserProfile> userProfiles = repo.findAll();
        List<UserProfileModel> userProfileModels = userProfiles.stream()
                .map(mapper::toModel)
                .toList();
        return Optional.of(userProfileModels);
    }

    public Optional<UserProfileModel> getUserProfileById(Long id) {
        UserProfile userProfile = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find UserProfile with id " + id));
        UserProfileModel userProfileModel = mapper.toModel(userProfile);
        return Optional.of(userProfileModel);
    }

    public Optional<UserProfileModel> updateUserProfile(Long id, UserProfileModel userProfileModel) {
        UserProfile existingUserProfile = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find UserProfile with id " + id));

        updateEntityFromModel(userProfileModel, existingUserProfile);
        UserProfile updatedUserProfile = repo.save(existingUserProfile);
        UserProfileModel updatedModel = mapper.toModel(updatedUserProfile);
        return Optional.of(updatedModel);
    }

    public void deleteUserProfile(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
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