package de.sep.calocalendar.data;

import de.sep.calocalendar.model.GoalModel;
import de.sep.calocalendar.model.UserProfileModel;
import de.sep.calocalendar.model.GroceryItemModel;
import de.sep.calocalendar.model.MealModel;
import de.sep.calocalendar.service.GoalService;
import de.sep.calocalendar.service.UserProfileService;
import de.sep.calocalendar.service.GroceryItemService;
import de.sep.calocalendar.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import java.util.List;

public class DataLoaderTest {

    @Mock
    private GoalService goalService;

    @Mock
    private UserProfileService userProfileService;

    @Mock
    private GroceryItemService groceryItemService;

    @Mock
    private MealService mealService;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadAllData() {
        dataLoader.loadAllData();

        // Verify that the services are called with the correct parameters
        verify(goalService).createGoal(new GoalModel().goalWeight(70.0f).goalSpeed(10));
        verify(goalService).createGoal(new GoalModel().goalWeight(65.0f).goalSpeed(12));
        verify(goalService).createGoal(new GoalModel().goalWeight(80.0f).goalSpeed(8));
        verify(goalService).createGoal(new GoalModel().goalWeight(75.0f).goalSpeed(9));
        verify(goalService).createGoal(new GoalModel().goalWeight(85.0f).goalSpeed(7));

        verify(userProfileService).createUserProfile(new UserProfileModel("jannBeier", 1, 30, 75.0f, 3, new GoalModel().goalWeight(70.0f).goalSpeed(10)));
        verify(userProfileService).createUserProfile(new UserProfileModel("anneSmith", 2, 25, 65.0f, 2, new GoalModel().goalWeight(65.0f).goalSpeed(12)));
        verify(userProfileService).createUserProfile(new UserProfileModel("mikeJohnson", 1, 28, 80.0f, 4, new GoalModel().goalWeight(80.0f).goalSpeed(8)));
        verify(userProfileService).createUserProfile(new UserProfileModel("lisaBrown", 2, 22, 55.0f, 1, new GoalModel().goalWeight(55.0f).goalSpeed(5)));
        verify(userProfileService).createUserProfile(new UserProfileModel("daveClark", 1, 35, 85.0f, 5, new GoalModel().goalWeight(85.0f).goalSpeed(7)));

        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Apple").calories(95).weight(100));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Banana").calories(105).weight(118));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Orange").calories(62).weight(131));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Strawberry").calories(4).weight(12));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Grapes").calories(62).weight(151));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Carrot").calories(25).weight(61));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Broccoli").calories(55).weight(148));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Spinach").calories(23).weight(30));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Potato").calories(163).weight(213));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Tomato").calories(22).weight(123));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Onion").calories(44).weight(110));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Cucumber").calories(16).weight(300));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Bell Pepper").calories(24).weight(119));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Mushroom").calories(15).weight(70));
        verify(groceryItemService).createGroceryItem(new GroceryItemModel().name("Garlic").calories(5).weight(3));

        verify(mealService).createMeal(new MealModel().mealName("Breakfast"));
        verify(mealService).createMeal(new MealModel().mealName("Lunch"));
        verify(mealService).createMeal(new MealModel().mealName("Dinner"));
        verify(mealService).createMeal(new MealModel().mealName("Snack"));
        verify(mealService).createMeal(new MealModel().mealName("Brunch"));
        verify(mealService).createMeal(new MealModel().mealName("Supper"));
        verify(mealService).createMeal(new MealModel().mealName("Dessert"));
    }
}