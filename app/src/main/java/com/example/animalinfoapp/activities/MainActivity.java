package com.example.animalinfoapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.animalinfoapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // ראה למטה את layout
    }

    // כפתור EN/HE (מוגדר ב-activity_main.xml: android:onClick="onLanguageSwitchClicked")
    public void onLanguageSwitchClicked(View view) {
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String currentLang = prefs.getString("lang", "en");

        // מחליפים בין אנגלית לעברית
        if ("he".equals(currentLang)) {
            prefs.edit().putString("lang", "en").apply();
        } else {
            prefs.edit().putString("lang", "he").apply();
        }

        // רענון (יגרום לטעינה מחדש של הפרגמנטים)
        recreate();
    }
}
