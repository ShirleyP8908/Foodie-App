package com.example.foodieapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<MealItem> mealItems;
    private MealItemAdapter mealItemAdapter;
    private int gridColumnCount;

    private Button broadcastingButton;

    private static final int ADD_NEW_MEAL_ITEM = 1;


    private String Title;
    private String Description;
    private String Ingredients;
    private String Recipe;
    private String Calories;

    private MyReceiver myReceiver;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        mealItems = new ArrayList<>();
        mealItemAdapter = new MealItemAdapter(this, mealItems);
        recyclerView.setAdapter(mealItemAdapter);
        loadMealData();

        myReceiver = new MyReceiver(mealItems);

        broadcastingButton = findViewById(R.id.Broadcast);

        broadcastingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startBroadcast();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMealItem.class);
                startActivityForResult(intent, ADD_NEW_MEAL_ITEM);
            }
        });
    }


    private void loadMealData() {
        String[] mealTitles = getResources().getStringArray(R.array.meal_titles);
        String[] mealInfo = getResources().getStringArray(R.array.meal_info);
        TypedArray mealImages = getResources().obtainTypedArray(R.array.meal_images);
        String[] mealRecipes = getResources().getStringArray(R.array.meal_recipes);
        int[] mealCalories = getResources().getIntArray(R.array.meal_calories);
        String[] mealIngredients = getResources().getStringArray(R.array.meal_ingredients);

        for (int i = 0; i < mealTitles.length; i++){
            mealItems.add(new MealItem(mealTitles[i],mealInfo[i], mealIngredients[i],mealCalories[i], mealImages.getResourceId(i,0),mealRecipes[i]));

        }
        mealItemAdapter.notifyDataSetChanged();
        mealImages.recycle();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startBroadcast(){
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("com.example.I_AM_HOME");
        sendBroadcast(broadcastIntent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("com.example.I_AM_HOME");
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ADD_NEW_MEAL_ITEM) {

            Title = data.getStringExtra("New_Meal_Title");
            Description = data.getStringExtra("New_Meal_Description");
            Ingredients = data.getStringExtra("New_Meal_Ingredients");
            Calories = data.getStringExtra("New_Meal_Calories");
            Recipe = data.getStringExtra("New_Meal_Recipe");

            MealItem newMeal = new MealItem(Title, Description, Ingredients, Integer.valueOf(Calories), 0, Recipe);

            mealItems.add(newMeal);
            mealItemAdapter.notifyItemInserted(mealItems.size());
        }


    }

}