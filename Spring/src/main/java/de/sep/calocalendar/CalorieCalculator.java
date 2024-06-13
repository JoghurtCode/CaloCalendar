package de.sep.calocalendar;

import javax.swing.*;

public class CalorieCalculator {

    private static final int MALE = 0;
    private static final int FEMALE = 1;

    private static final int LOW_ACTIVITY = 0;
    private static final int MEDIUM_ACTIVITY = 1;
    private static final int HIGH_ACTIVITY = 2;

    private static final int LOSE_WEIGHT = 0;
    private static final int HOLD_WEIGHT = 1;
    private static final int GAIN_WEIGHT = 2;

    /**
     * Calculate the daily calorie needs based on various factors.
     *
     * @param gender            Gender of the individual (0 = male, 1 = female).
     * @param bodyweight        Body weight in kilograms.
     * @param height            Height in centimeters.
     * @param age               Age in years.
     * @param physicalActivity  Level of physical activity (0 = low, 1 = medium, 2 = high).
     * @param selectedGoal      Goal (0 = lose weight, 1 = hold weight, 2 = gain weight).
     * @return                  The calculated daily calorie needs.
     */

    public int calculateCalorieNeeds(int gender, double bodyweight, double height, double age, int physicalActivity, int selectedGoal) {

        double neededCalories = calculateBasalMetabolicRate(gender, bodyweight, height, age);
        neededCalories = adjustForPhysicalActivity(neededCalories, physicalActivity);
        neededCalories = adjustForGoal(neededCalories, selectedGoal);

        return (int) Math.round(neededCalories);
    }

    private double calculateBasalMetabolicRate(int gender, double bodyweight, double height, double age) {
        switch (gender) {
            case MALE:
                return (10 * bodyweight) + (6.25 * height) - (5 * age) + 5;
            case FEMALE:
                return (10 * bodyweight) + (6.25 * height) - (5 * age) - 161;
            default:
                throw new IllegalArgumentException("Invalid gender: " + gender);
        }
    }

    private double adjustForPhysicalActivity(double calories, int physicalActivity) {
        switch (physicalActivity) {
            case LOW_ACTIVITY:
                return calories * 1.25;
            case MEDIUM_ACTIVITY:
                return calories * 1.4;
            case HIGH_ACTIVITY:
                return calories * 1.55;
            default:
                throw new IllegalArgumentException("Invalid level of physical activity: " + physicalActivity);
        }
    }

    private double adjustForGoal(double calories, int selectedGoal) {
        switch (selectedGoal) {
            case LOSE_WEIGHT:
                return calories - 500;
            case HOLD_WEIGHT:
                return calories; // No change
            case GAIN_WEIGHT:
                return calories + 500;
            default:
                throw new IllegalArgumentException("Invalid goal: " + selectedGoal);
        }
    }

    public double calculateWeightChangePerWeek(int rate, int currentWeight, int targetWeight, int calculatedCalories){ //get currentWeight from calculateCalorieNeeds
        /*
        rate = 0 -> slow
        rate = 1 -> normal
        rate = 2 -> fast
         */
        int weightdiff = 0;
        double weeksUntilGoalReached = 0;

        if(currentWeight == targetWeight){ //gewicht halten
            return calculatedCalories;

        }else if(currentWeight < targetWeight){ //zunehmen
            weightdiff = targetWeight-currentWeight;
                switch(rate){
                    case 0:
                        calculatedCalories = calculatedCalories - 200; //+0.3kg/week
                        weeksUntilGoalReached = weightdiff/0.4;
                        break;
                    case 1:
                        calculatedCalories = calculatedCalories; //+0.5kg/week -> default
                        weeksUntilGoalReached = weightdiff/0.6;
                        break;
                    case 2:
                        calculatedCalories = calculatedCalories + 200; //+0.7kg/week
                        weeksUntilGoalReached = weightdiff/0.8;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid rate: " + rate);
                        break;
                }

            }else if(currentWeight > targetWeight){ //abnehmen
                weightdiff = currentWeight-targetWeight;
                switch(rate){
                    case 0:
                        calculatedCalories = calculatedCalories + 200; //-0.3kg/week
                        weeksUntilGoalReached = weightdiff/0.4;
                        break;
                    case 1:
                        calculatedCalories = calculatedCalories; //-0.5kg/week -> default
                        weeksUntilGoalReached = weightdiff/0.6;
                        break;
                    case 2:
                        calculatedCalories = calculatedCalories - 200; //-0.7kg/week
                        weeksUntilGoalReached = weightdiff/0.8;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid rate: " + rate);
                        break;
                }
            }


        }

    }

}
