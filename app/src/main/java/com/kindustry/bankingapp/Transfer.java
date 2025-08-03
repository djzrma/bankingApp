package com.kindustry.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.transfer_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupNavigation();
    }
    private void setupNavigation() {
        ImageButton home = findViewById(R.id.homeImageButton);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(Transfer.this, MainMenu.class);
            startActivity(intent);
        });

        ImageButton accounts = findViewById(R.id.accountsImageButton);
        accounts.setOnClickListener(v -> {
            Intent intent = new Intent(Transfer.this, Accounts.class);
            startActivity(intent);
        });

        ImageButton transfer = findViewById(R.id.transferImageButton);
        transfer.setOnClickListener(v -> {
            // Already on Transfer
        });

        ImageButton settings = findViewById(R.id.settingsImageButton);
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(Transfer.this, Settings.class);
            startActivity(intent);
        });
    }
}