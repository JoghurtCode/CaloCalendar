package de.sep.calocalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.sep.calocalendar.entities.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}

