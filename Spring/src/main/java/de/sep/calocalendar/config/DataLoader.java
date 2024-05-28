package de.sep.calocalendar.config;

import de.sep.calocalendar.model.*;
import de.sep.calocalendar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserProfileRepository userProfileRepository;
    private final GroceryItemRepository groceryItemRepository;
    private final MealRepository mealRepository;

    @Autowired
    public DataLoader(UserProfileRepository userProfileRepository,
                      GroceryItemRepository groceryItemRepository,
                      MealRepository mealRepository) {
        this.userProfileRepository = userProfileRepository;
        this.groceryItemRepository = groceryItemRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Beispiel-Daten einfügen
        /*
        UserProfile user1 = new UserProfile();
        user1.setUsername("JohnDoe");
        user1.setEmail("john@example.com");
        user1.setPassword("password123");

        UserProfile user2 = new UserProfile();
        user2.setUsername("JaneSmith");
        user2.setEmail("jane@example.com");
        user2.setPassword("password456");

        userProfileRepository.saveAll(Arrays.asList(user1, user2));

        GroceryItem item1 = new GroceryItem();
        item1.setItemName("Apple");
        item1.setCalories(95);

        GroceryItem item2 = new GroceryItem();
        item2.setItemName("Banana");
        item2.setCalories(105);

        GroceryItem item3 = new GroceryItem();
        item3.setItemName("Carrot");
        item3.setCalories(41);

        groceryItemRepository.saveAll(Arrays.asList(item1, item2, item3));

        Meal meal1 = new Meal();
        meal1.setMealName("Breakfast");
        meal1.setUserProfile(user1);
        meal1.setGroceryItems(Arrays.asList(item1, item2));

        Meal meal2 = new Meal();
        meal2.setMealName("Lunch");
        meal2.setUserProfile(user2);
        meal2.setGroceryItems(Arrays.asList(item2, item3));

        mealRepository.saveAll(Arrays.asList(meal1, meal2));*/
    }
}
