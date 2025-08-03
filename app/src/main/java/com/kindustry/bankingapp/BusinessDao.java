package com.kindustry.bankingapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface BusinessDao {

    @Insert
    long insert(Business user);

    @Update
    int update(Business user);

    @Query("SELECT * FROM user_table WHERE id = :id LIMIT 1")
    Business getUserById(int id);

}
