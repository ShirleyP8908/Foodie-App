package com.example.foodieapp;

import java.io.Serializable;

public class MealItem implements Serializable {

    private String title;
    private String description;
    private String ingredients;
    private int calories;
    private int imageResource;
    private String recipe;

    MealItem(String title, String description, String ingredients,
             int calories, int imageResource, String recipe ){

        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.calories = calories;
        this.imageResource = imageResource;
        this.recipe = recipe;
    }




    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getIngredients(){

        return ingredients;
    }

    public int getCalories(){
        return calories;
    }

    public int getImageResource(){
        return imageResource;
    }

    public String getRecipe(){
        return recipe;
    }
}
