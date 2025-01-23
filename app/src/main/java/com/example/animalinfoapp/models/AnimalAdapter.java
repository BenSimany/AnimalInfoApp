package com.example.animalinfoapp.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.animalinfoapp.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private final List<Animal> animalList;
    private final String currentLang;

    public AnimalAdapter(Context context, List<Animal> animalList) {
        this.animalList = animalList;

        // קוראים את השפה הנוכחית מ-SharedPreferences
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        currentLang = prefs.getString("lang", "en"); // ברירת מחדל אנגלית
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animalList.get(position);

        // מציגים שם ותיאור (אם תרצה גם place, הוסף זאת) בשפה הנוכחית
        if ("he".equals(currentLang)) {
            holder.nameTextView.setText(animal.getNameHe());
            holder.descriptionTextView.setText(animal.getDescriptionHe());
            holder.placeTextView.setText(animal.getPlaceOfFoundHe());
        } else {
            holder.nameTextView.setText(animal.getNameEn());
            holder.descriptionTextView.setText(animal.getDescriptionEn());
            holder.placeTextView.setText(animal.getPlaceOfFoundEn());
        }
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, descriptionTextView,placeTextView;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView        = itemView.findViewById(R.id.animalName);
            descriptionTextView = itemView.findViewById(R.id.animalDescription);
            placeTextView = itemView.findViewById(R.id.animalPlace);
        }
    }
}
