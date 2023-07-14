package com.example.foodieapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.foodieapp.MealItem;
import com.example.foodieapp.MealItemDisplay;

import java.util.ArrayList;
import java.util.Random;

public class MyReceiver extends BroadcastReceiver {
    private ArrayList<MealItem> mealItems;
    private Context context;

    protected static final String ACTION_CUSTOM_BROADCAST = "com.example.I_AM_HOME";

    public MyReceiver(ArrayList<MealItem> mealItems) {
        this.mealItems = mealItems;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String intentAction = intent.getAction();
        displayToastForIntentAction(intentAction);
    }

    private void displayToastForIntentAction(String intentAction) {
        switch (intentAction) {
            case ACTION_CUSTOM_BROADCAST:
                    Random randomMeal = new Random();
                    int mealIndex = randomMeal.nextInt(mealItems.size());
                    MealItem selectedMeal = mealItems.get(mealIndex);
                    Toast.makeText(context, "Happy cooking: " + selectedMeal.getTitle(), Toast.LENGTH_SHORT).show();
                    displayMealItem(selectedMeal);
        }
    }

    private void displayMealItem(MealItem mealItem) {
        Intent displayIntent = new Intent(context, MealItemDisplay.class);
        displayIntent.putExtra("meal_title", mealItem.getTitle());
        displayIntent.putExtra("meal_description", mealItem.getDescription());
        displayIntent.putExtra("meal_calories", mealItem.getCalories());
        displayIntent.putExtra("meal_recipe", mealItem.getRecipe());
        displayIntent.putExtra("meal_ingredients", mealItem.getIngredients());
        displayIntent.putExtra("meal_image", mealItem.getImageResource());
        context.startActivity(displayIntent);
    }
}