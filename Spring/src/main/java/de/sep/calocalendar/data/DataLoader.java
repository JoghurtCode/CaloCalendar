package de.sep.calocalendar.data;

import de.sep.calocalendar.model.GoalModel;
import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.model.GroceryItemModel;
import de.sep.calocalendar.model.MealModel;
import de.sep.calocalendar.service.GoalService;
import de.sep.calocalendar.service.UserProfileService;
import de.sep.calocalendar.service.GroceryItemService;
import de.sep.calocalendar.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private GoalService goalService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private GroceryItemService groceryItemService;
    @Autowired
    private MealService mealService;

    public void loadAllData() {
        loadGoals();
        loadUserProfiles();
        loadGroceryItems();
        loadMeals();
    }

    private void loadGoals() {
        List<GoalModel> goals = List.of(
                new GoalModel().goalWeight(70.0f).goalSpeed(10),
                new GoalModel().goalWeight(65.0f).goalSpeed(12),
                new GoalModel().goalWeight(80.0f).goalSpeed(8),
                new GoalModel().goalWeight(75.0f).goalSpeed(9),
                new GoalModel().goalWeight(85.0f).goalSpeed(7)
        );

        goals.forEach(goalService::createGoal);
    }

    private void loadUserProfiles() {
        List<UserProfileModel> userProfiles = List.of(
                new UserProfileModel("jannBeier", 1, 30, 75.0f, 3, new GoalModel().goalWeight(70.0f).goalSpeed(10)),
                new UserProfileModel("anneSmith", 2, 25, 65.0f, 2, new GoalModel().goalWeight(65.0f).goalSpeed(12)),
                new UserProfileModel("mikeJohnson", 1, 28, 80.0f, 4, new GoalModel().goalWeight(80.0f).goalSpeed(8)),
                new UserProfileModel("lisaBrown", 2, 22, 55.0f, 1, new GoalModel().goalWeight(55.0f).goalSpeed(5)),
                new UserProfileModel("daveClark", 1, 35, 85.0f, 5, new GoalModel().goalWeight(85.0f).goalSpeed(7))
        );

        userProfiles.forEach(userProfileService::createUserProfile);
    }

    private void loadGroceryItems() {
        List<GroceryItemModel> groceryItems = List.of(
                new GroceryItemModel().name("Apple").calories(95).weight(100),
                new GroceryItemModel().name("Banana").calories(105).weight(118),
                new GroceryItemModel().name("Orange").calories(62).weight(131),
                new GroceryItemModel().name("Strawberry").calories(4).weight(12),
                new GroceryItemModel().name("Grapes").calories(62).weight(151),
                new GroceryItemModel().name("Carrot").calories(25).weight(61),
                new GroceryItemModel().name("Broccoli").calories(55).weight(148),
                new GroceryItemModel().name("Spinach").calories(23).weight(30),
                new GroceryItemModel().name("Potato").calories(163).weight(213),
                new GroceryItemModel().name("Tomato").calories(22).weight(123),
                new GroceryItemModel().name("Onion").calories(44).weight(110),
                new GroceryItemModel().name("Cucumber").calories(16).weight(300),
                new GroceryItemModel().name("Bell Pepper").calories(24).weight(119),
                new GroceryItemModel().name("Mushroom").calories(15).weight(70),
                new GroceryItemModel().name("Garlic").calories(5).weight(3)
        );

        groceryItems.forEach(groceryItemService::createGroceryItem);
    }

    private void loadMeals() {
        List<MealModel> meals = List.of(
                new MealModel().mealName("Breakfast"),
                new MealModel().mealName("Lunch"),
                new MealModel().mealName("Dinner"),
                new MealModel().mealName("Snack"),
                new MealModel().mealName("Brunch"),
                new MealModel().mealName("Supper"),
                new MealModel().mealName("Dessert")
        );

        meals.forEach(mealService::createMeal);
    }
}
