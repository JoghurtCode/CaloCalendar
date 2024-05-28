package de.sep.calocalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.sep.calocalendar.entities.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
