package de.sep.calocalendar.controller;

import de.sep.calocalendar.entities.UserProfile;
import de.sep.calocalendar.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/userProfile")
public class UserProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping
    public Iterable<UserProfile> getUserProfiles() {
        return userProfileRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserProfile> getUserProfileById(@PathVariable Long id) {
        return userProfileRepository.findById(id);
    }

    @PostMapping
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @PutMapping
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteUserProfile(@PathVariable Long id) {
        userProfileRepository.deleteById(id);
    }
}
