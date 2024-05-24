package de.sep.calocalendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.sep.calocalendar.model.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}

