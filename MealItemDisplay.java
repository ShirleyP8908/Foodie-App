package com.example.foodieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MealItemDisplay extends AppCompatActivity {
    private TextView mealTitle;
    private TextView mealDescription;
    private TextView mealCalories;
    private TextView mealRecipe;
    private TextView mealIngredients;
    private ImageView imageViewMeal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_item_display);

        Intent intent = getIntent();
            String title = intent.getStringExtra("meal_title");
            String description = intent.getStringExtra("meal_description");
            int calories = intent.getIntExtra("meal_calories", 0);
            String recipe = intent.getStringExtra("meal_recipe");
            String ingredients = intent.getStringExtra("meal_ingredients");
            int imageResource = intent.getIntExtra("meal_image", 0);

            mealTitle = findViewById(R.id.meal_display_title);
            imageViewMeal = findViewById(R.id.image_meal);
            mealDescription = findViewById(R.id.meal_display_details);
            mealCalories = findViewById(R.id.mean_display_calories);
            mealRecipe = findViewById(R.id.mean_display_recipe);
            mealIngredients = findViewById(R.id.mean_display_ingredients);
            imageViewMeal = findViewById(R.id.image_meal);

            mealTitle.setText(title);
            mealDescription.setText(description);
            mealCalories.setText(String.valueOf(calories));
            mealIngredients.setText(ingredients);
            imageViewMeal.setImageResource(imageResource);
            mealRecipe.setText(recipe);

        MealItem meal = new MealItem(title, description, ingredients, calories, imageResource, recipe);

        mealTitle.setText(meal.getTitle());
        mealDescription.setText(meal.getDescription());
        mealCalories.setText(String.valueOf(meal.getCalories()));
        mealIngredients.setText(meal.getIngredients());
        imageViewMeal.setImageResource(meal.getImageResource());
        mealRecipe.setText(meal.getRecipe());



        mealRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent website = new Intent(Intent.ACTION_VIEW);
                Uri link = Uri.parse(meal.getRecipe());
                website.setData(link);
                startActivity(website);

            }
        });
    }
}
