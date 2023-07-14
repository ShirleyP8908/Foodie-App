package com.example.foodieapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddMealItem extends AppCompatActivity {

   private EditText mealTitle;
   private EditText mealDescription;
   private EditText mealIngredients;
   private EditText mealRecipe;
   private EditText mealCalories;
   private Button mealSubmit;

   private ImageView mealImage;

   private String Title;
   private String Description;
   private String Ingredients;
   private String Recipe;
   private String Calories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meal_item);

        mealTitle = findViewById(R.id.new_meal_title);
        mealDescription = findViewById(R.id.new_meal_description);
        mealIngredients = findViewById(R.id.new_meal_ingredients);
        mealRecipe = findViewById(R.id.new_mean_recipe);
        mealCalories = findViewById(R.id.new_meal_calories);
        mealSubmit = findViewById(R.id.submit_meal);
        mealImage = findViewById(R.id.add_meal_img);


        mealSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newMealIntent = new Intent(AddMealItem.this, MealItemDisplay.class);
                Title = mealTitle.getText().toString();
                Description = mealDescription.getText().toString();
                Ingredients = mealIngredients.getText().toString();
                Recipe = mealRecipe.getText().toString();
                Calories = mealCalories.getText().toString();

                newMealIntent.putExtra("New_Meal_Title", Title);
                newMealIntent.putExtra("New_Meal_Description", Description);
                newMealIntent.putExtra("New_Meal_Ingredients", Ingredients);
                newMealIntent.putExtra("New_Meal_Recipe", Recipe);
                newMealIntent.putExtra("New_Meal_Calories", Calories);

                setResult(RESULT_OK, newMealIntent);
                finish();
            }
        });

    }


}
