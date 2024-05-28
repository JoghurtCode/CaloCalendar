import javax.swing.*;

public class CalorieCalculator {

        /*
        gender  0=female
                1=male

        bodyweight in kg

        height in cm

        age in years

        levelOfPhysicalActivity     0=low physical activity
                                    1=medium physical activity
                                    2=high physical activity

        goal                        0=lose weight
                                    1=hold weight
                                    2=gain weight
         */

int calculateCalorieNeeds(int gender,double bodyweight,double height, double age, int physicalActivity, int selectedGoal) {

    double neededCalories;

    switch (gender) {
        case 0:
            neededCalories = (10*bodyweight) + (6.25*height) - (5*age) + 5;
            break;
        case 1:
            neededCalories = (10*bodyweight) + (6.25*height) - (5*age) - 161;
            break;
        default:
            // Handle unexpected cases , f.e. missing inputs
            // ToDo: show error message
            break;
    }

    switch(physicalActivity){
        case 0:
            neededCalories = neededCalories*1.25;
            break;
        case 1:
            neededCalories = neededCalories*1.4;
            break;
        case 2:
            neededCalories = neededCalories*1.55;
            break;
        default:
            // Handle unexpected cases , f.e. missing inputs
            // ToDo: show error message
            break;
    }

    switch(selectedGoal){
        case 0:
            neededCalories = neededCalories-500;
            break;
        case 1:
            //leave calorie needs as they are to hold weight
            break;
        case 2:
            neededCalories = neededCalories+500;
            break;
        default:
            // Handle unexpected cases , f.e. missing inputs
            // ToDo: show error message
            break;
    }

    int neededCaloriesRounded = (int) Math.round(neededCalories);
    return neededCaloriesRounded;

}
}

