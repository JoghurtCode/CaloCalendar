package de.sep.calocalendar.controller;

import de.sep.calocalendar.api.UserProfileApiDelegate;
import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserProfileController implements UserProfileApiDelegate {

    @Autowired
    private UserProfileService service;

    @Override
    public ResponseEntity<Long> addUserProfile(UserProfileModel model) {
        return ResponseEntity.of(service.addUserProfile(model));
    }
}
