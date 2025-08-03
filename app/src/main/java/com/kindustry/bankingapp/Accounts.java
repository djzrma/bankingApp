package com.kindustry.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Accounts extends AppCompatActivity {

    private EditText etFullName, etEmail, etPhone, etAddress;
    private Button btnSave;

    private Business currentUser;
    private BusinessDao businessDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts_page);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnSave = findViewById(R.id.btnSave);

        BusinessDatabase db = BusinessDatabase.getDatabase(this);
        businessDao = db.businessDao();

        // Load or create user
        new Thread(() -> {
            currentUser = businessDao.getUserById(1);
            if (currentUser == null) {
                Business newUser = new Business("defaultUser", "1234", "", "", "", "");
                long newId = businessDao.insert(newUser);
                currentUser = businessDao.getUserById((int) newId);
            }
            runOnUiThread(this::populateForm);
        }).start();

        setupNavigation();
        btnSave.setOnClickListener(v -> saveUserInfo());
    }

    private void populateForm() {
        if (currentUser == null) return;
        etFullName.setText(currentUser.fullName);
        etEmail.setText(currentUser.email);
        etPhone.setText(currentUser.phone);
        etAddress.setText(currentUser.address);
    }

    private void saveUserInfo() {
        if (currentUser == null) return;

        currentUser.fullName = etFullName.getText().toString();
        currentUser.email = etEmail.getText().toString();
        currentUser.phone = etPhone.getText().toString();
        currentUser.address = etAddress.getText().toString();

        new Thread(() -> {
            int rows = businessDao.update(currentUser);  // change DAO to return int
            runOnUiThread(() -> {
                if (rows > 0) {
                    Toast.makeText(Accounts.this, "Account updated successfully!", Toast.LENGTH_SHORT).show();
                    // Navigate back to Main Menu
                    Intent intent = new Intent(Accounts.this, MainMenu.class);
                    startActivity(intent);
                    finish();  // close this activity
                } else {
                    Toast.makeText(Accounts.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void setupNavigation() {
        ImageButton home = findViewById(R.id.homeImageButton);
        home.setOnClickListener(v -> {
            Intent intent = new Intent(Accounts.this, MainMenu.class);
            startActivity(intent);
        });

        ImageButton accounts = findViewById(R.id.accountsImageButton);
        accounts.setOnClickListener(v -> {
            // Already on Accounts
        });

        ImageButton transfer = findViewById(R.id.transferImageButton);
        transfer.setOnClickListener(v -> {
            Intent intent = new Intent(Accounts.this, Transfer.class);
            startActivity(intent);
        });

        ImageButton settings = findViewById(R.id.settingsImageButton);
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(Accounts.this, Settings.class);
            startActivity(intent);
        });
    }
}
