package com.example.animalinfoapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.animalinfoapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // הגדרת שפה לברירת מחדל לפי Firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.setLanguageCode("he"); // קוד השפה לעברית
    }
}