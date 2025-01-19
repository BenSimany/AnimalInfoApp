package com.example.animalinfoapp.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.animalinfoapp.R;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animalList;

    public AnimalAdapter(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDesc;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtAnimalName);
            txtDesc = itemView.findViewById(R.id.txtAnimalDesc);
        }
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
        holder.txtName.setText(animal.getName());
        holder.txtDesc.setText(animal.getDescription());
    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    // אפשר להוסיף method אם רוצים להוסיף חיות לרשימה מבחוץ
}
