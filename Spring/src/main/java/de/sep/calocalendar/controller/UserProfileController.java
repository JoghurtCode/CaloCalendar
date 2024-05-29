package de.sep.calocalendar.controller;

import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userprofiles")
public class UserProfileController {

   @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<List<UserProfileModel>> getAllUserProfiles() {
        return ResponseEntity.of(userProfileService.getAllUserProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileModel> getUserProfileById(@PathVariable Long id) {
        return ResponseEntity.of(userProfileService.getUserProfileById(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUserProfile(@RequestBody UserProfileModel userProfileModel) {
        return ResponseEntity.of(userProfileService.createUserProfile(userProfileModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileModel> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileModel userProfileModel) {
        return ResponseEntity.of(userProfileService.updateUserProfile(id, userProfileModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
