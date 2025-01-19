package com.example.animalinfoapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.animalinfoapp.R;
import com.example.animalinfoapp.models.Animal;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class AddAnimalFragment extends Fragment {

    private EditText etName, etDescription;
    private MaterialButton btnAdd, btnBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_animal, container, false);

        etName = view.findViewById(R.id.etAnimalName);
        etDescription = view.findViewById(R.id.etAnimalDescription);
        btnAdd = view.findViewById(R.id.btnAddAnimal);
        btnBack = view.findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String desc = etDescription.getText().toString().trim();

            if(TextUtils.isEmpty(name)){
                Toast.makeText(getContext(), getString(R.string.animal_name_required), Toast.LENGTH_SHORT).show();
                return;
            }

            // מזהה משתמש
            SharedPreferences prefs = requireContext().getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String emailOrPhone = prefs.getString("email", null);

            if(!TextUtils.isEmpty(emailOrPhone)){
                DatabaseReference userRef = FirebaseDatabase.getInstance()
                        .getReference("users")
                        .child(emailOrPhone.replace(".", "_"))  // דוגמה
                        .child("animals");
                String pushId = userRef.push().getKey();
                if(pushId != null) {
                    Animal animal = new Animal(name, desc);
                    userRef.child(pushId).setValue(animal)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(getContext(), getString(R.string.animal_added), Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view)
                                        .navigate(R.id.action_addAnimalFragment_to_animalsFragment);
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show()
                            );
                }
            } else {
                Toast.makeText(getContext(), getString(R.string.user_not_logged_in), Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_addAnimalFragment_to_animalsFragment);
        });

        return view;
    }
}
