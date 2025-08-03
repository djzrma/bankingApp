package com.kindustry.bankingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private EditText nameInput;
    private Switch notificationsSwitch, darkModeSwitch, autoLoginSwitch;
    private Spinner currencySpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_page);

        setupViews();
        loadSettings();
        logoutButtonAction();
        setupNavigation();
    }

    private void setupViews() {
        nameInput = findViewById(R.id.nameInput);
        notificationsSwitch = findViewById(R.id.notificationsSwitch);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        autoLoginSwitch = findViewById(R.id.autoLoginSwitch);
        currencySpinner = findViewById(R.id.currencySpinner);
        saveButton = findViewById(R.id.saveButton);

        // Currency spinner options
        String[] currencies = {"USD", "EUR", "GBP"};
        ArrayAdapter<String> currencyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(currencyAdapter);

        saveButton.setOnClickListener(v -> saveSettings());
    }

    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences("UserSettings", MODE_PRIVATE);

        nameInput.setText(prefs.getString("displayName", ""));
        notificationsSwitch.setChecked(prefs.getBoolean("notificationsEnabled", false));
        darkModeSwitch.setChecked(prefs.getBoolean("darkMode", false));
        autoLoginSwitch.setChecked(prefs.getBoolean("autoLogin", false));

        String currency = prefs.getString("currency", "USD");
        switch (currency) {
            case "EUR":
                currencySpinner.setSelection(1);
                break;
            case "GBP":
                currencySpinner.setSelection(2);
                break;
            default:
                currencySpinner.setSelection(0);
        }
    }

    private void saveSettings() {
        SharedPreferences prefs = getSharedPreferences("UserSettings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("displayName", nameInput.getText().toString());
        editor.putBoolean("notificationsEnabled", notificationsSwitch.isChecked());
        editor.putBoolean("darkMode", darkModeSwitch.isChecked());
        editor.putBoolean("autoLogin", autoLoginSwitch.isChecked());
        editor.putString("currency", currencySpinner.getSelectedItem().toString());

        editor.apply();
        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
    }

    private void logoutButtonAction(){
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, LoginPage.class);
                Toast.makeText(Settings.this, "Successfully Logged Out!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

    private void setupNavigation() {
        ImageButton home = findViewById(R.id.homeImageButton);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, MainMenu.class);
            startActivity(intent);
        });

        ImageButton accounts = findViewById(R.id.accountsImageButton);
        accounts.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, Accounts.class);
            startActivity(intent);
        });

        ImageButton transfer = findViewById(R.id.transferImageButton);
        transfer.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, Transfer.class);
            startActivity(intent);
        });

        ImageButton settings = findViewById(R.id.settingsImageButton);
        settings.setOnClickListener(v -> {
            // Already on Settings
        });
    }
}


