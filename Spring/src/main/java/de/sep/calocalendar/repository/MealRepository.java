package de.sep.calocalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.sep.calocalendar.entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
