package com.example.foodieapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.ViewHolder> {
    private ArrayList<MealItem> mealItems;
    private Context context;


    public MealItemAdapter(Context context, ArrayList<MealItem>mealItemArrayList) {
        this.context = context;
        mealItems = mealItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false)));

    }

    @Override
    public void onBindViewHolder(@NonNull MealItemAdapter.ViewHolder holder, int position) {
    MealItem currMeal = mealItems.get(position);
    holder.bindItem(currMeal);
    }

    @Override
    public int getItemCount() {
        return mealItems.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textTitle, textInfo;
        private ImageView imageViewMeal ;
        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title);
            textInfo = itemView.findViewById(R.id.subtitle);
            imageViewMeal = itemView.findViewById(R.id.image_meal);
            itemView.setOnClickListener(this);


            itemView.setOnLongClickListener(v -> {
                removeItemDialouge(getAdapterPosition());
                return true;
            });
        }

        public void removeItemDialouge(int position){
            AlertDialog.Builder build = new AlertDialog.Builder(context);
            build.setTitle("Remove Item").setMessage("Would you like to remove this meal item?").setPositiveButton("Remove", (dialog, which) -> removeItem(position)).setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            build.show();
        }

        public void removeItem(int position){
            mealItems.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            MealItem clickedMeal = mealItems.get(position);
            Intent intent = new Intent(context, MealItemDisplay.class);
            intent.putExtra("meal_title", clickedMeal.getTitle());
            intent.putExtra("meal_description", clickedMeal.getDescription());
            intent.putExtra("meal_ingredients", clickedMeal.getIngredients());
            intent.putExtra("meal_calories", clickedMeal.getCalories());
            intent.putExtra("meal_recipe", clickedMeal.getRecipe());
            intent.putExtra("meal_image", clickedMeal.getImageResource());
            context.startActivity(intent);
        }

        public void bindItem(MealItem currentMeal){
        textTitle.setText(currentMeal.getTitle());
        textInfo.setText(currentMeal.getDescription());
            Glide.with(context).load(currentMeal.getImageResource()).into(imageViewMeal);
        }
    }
}
