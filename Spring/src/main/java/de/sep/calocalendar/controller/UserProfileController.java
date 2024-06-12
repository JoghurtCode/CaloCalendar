package de.sep.calocalendar.controller;

import de.sep.calocalendar.api.UserProfileApiDelegate;
import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserProfileController implements UserProfileApiDelegate {

    @Autowired
    private UserProfileService service;

    @Override
    public ResponseEntity<Long> addUserProfile(UserProfileModel model) {
        Optional<Long> result = service.addUserProfile(model);
        return result.map(ResponseEntity::ok)
                     .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<List<UserProfileModel>> getAllUserProfiles() {
        Optional<List<UserProfileModel>> result = service.getAllUserProfiles();
        return result.map(ResponseEntity::ok)
                     .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @Override
    public ResponseEntity<UserProfileModel> updateUserProfile(UserProfileModel model) {
        Optional<UserProfileModel> result = service.updateUserProfile(model);
        return result.map(ResponseEntity::ok)
                     .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<UserProfileModel> getUserProfileById(Long id) {
        Optional<UserProfileModel> result = service.getUserProfileById(id);
        return result.map(ResponseEntity::ok)
                     .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        service.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
