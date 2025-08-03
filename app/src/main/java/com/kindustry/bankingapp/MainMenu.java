package com.kindustry.bankingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainMenu extends AppCompatActivity {

    private TextView greetingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main_menu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupNavigation();
        displayGreeting();
    }

    private void setupNavigation() {
        ImageButton home = findViewById(R.id.homeImageButton);
        home.setOnClickListener(v -> {
            // Already on Main Menu
        });

        ImageButton accounts = findViewById(R.id.accountsImageButton);
        accounts.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Accounts.class);
            Toast.makeText(MainMenu.this, "Now on Accounts Screen", Toast.LENGTH_LONG).show();
            startActivity(intent);
        });

        ImageButton transfer = findViewById(R.id.transferImageButton);
        transfer.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Transfer.class);
            Toast.makeText(MainMenu.this, "Now on Transfer Screen", Toast.LENGTH_LONG).show();
            startActivity(intent);
        });

        ImageButton settings = findViewById(R.id.settingsImageButton);
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Settings.class);
            Toast.makeText(MainMenu.this, "Now on Settings Screen", Toast.LENGTH_LONG).show();
            startActivity(intent);
        });
    }

    private void displayGreeting() {
        greetingText = findViewById(R.id.accountsTitle);
        SharedPreferences prefs = getSharedPreferences("UserSettings", MODE_PRIVATE);
        String displayName = prefs.getString("displayName", "User");

        greetingText.setText("Welcome, USERNAME HERE!");
    }
}
