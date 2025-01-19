package com.example.animalinfoapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.animalinfoapp.R;
import com.example.animalinfoapp.models.Animal;
import com.example.animalinfoapp.models.AnimalAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AnimalsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AnimalAdapter adapter;
    private ArrayList<Animal> animalsList = new ArrayList<>();
    private Button btnAddAnimal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animals, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AnimalAdapter(animalsList);
        recyclerView.setAdapter(adapter);

        btnAddAnimal = view.findViewById(R.id.btnGoToAddAnimal);
        btnAddAnimal.setOnClickListener(v -> {
            Navigation.findNavController(view)
                    .navigate(R.id.action_animalsFragment_to_addAnimalFragment);
        });

        loadAnimalsFromFirebase();

        return view;
    }

    private void loadAnimalsFromFirebase(){
        // מניחים שהמספר טלפון משמש כמזהה משתמש (או אימייל)
        SharedPreferences prefs = requireContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String emailOrPhone = prefs.getString("email", null);
        if(TextUtils.isEmpty(emailOrPhone)) {
            // במציאות: ייתכן שרוצים להכריח חזרה ל-LoginFragment
            return;
        }

        // ניגשים למשתמש הספציפי ב-Realtime Database
        DatabaseReference userAnimalsRef = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(emailOrPhone.replace(".", "_")) // דוגמה (אם הטלפון/אימייל)
                .child("animals");

        userAnimalsRef.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                animalsList.clear();
                DataSnapshot snapshot = task.getResult();
                if(snapshot != null){
                    for(DataSnapshot ds : snapshot.getChildren()){
                        Animal animal = ds.getValue(Animal.class);
                        if(animal != null) {
                            animalsList.add(animal);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
