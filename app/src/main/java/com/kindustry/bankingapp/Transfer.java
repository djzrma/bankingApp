package com.kindustry.bankingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
        mainMenuActivityButton();
        accountsActivityButton();
        settingsActivityButton();
        activitySwitchMessage();
    }

    //method to switch to mainMenu Activity
    private void mainMenuActivityButton(){
        ImageButton mainMenuButton = findViewById(R.id.homeImageButton);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transfer.this, MainMenu.class);
                String passedMessage = "You are now at the Main Menu";
                intent.putExtra("mainMenuMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to switch to Accounts Activity
    private void accountsActivityButton(){
        ImageButton accountsButton = findViewById(R.id.accountsImageButton);
        accountsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transfer.this, Accounts.class);
                String passedMessage = "You are now on the Accounts Screen";
                intent.putExtra("accountsMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to switch to Settings Activity
    private void settingsActivityButton(){
        ImageButton settingsButton = findViewById(R.id.settingsImageButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transfer.this, Settings.class);
                String passedMessage = "You are now on the Settings Screen";
                intent.putExtra("settingsMessage", passedMessage);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //method to display activity switch confirmation message
    private void activitySwitchMessage(){
        Intent intent = getIntent();
        TextView message = findViewById(R.id.activitySwitchConfirmation);
        String tempString = intent.getStringExtra("transferMessage");
        message.setText(tempString);
    }
}