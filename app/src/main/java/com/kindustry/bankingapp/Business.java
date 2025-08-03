package com.kindustry.bankingapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")  // Must match DAO query
public class Business {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String username;
    public String password;
    public String fullName;
    public String email;
    public String phone;
    public String address;

    public Business(String username, String password, String fullName,
                    String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
