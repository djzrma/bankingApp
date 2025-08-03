package com.kindustry.bankingapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Business.class}, version = 1)
public abstract class BusinessDatabase extends RoomDatabase {

    public abstract BusinessDao businessDao();

    private static volatile BusinessDatabase INSTANCE;

    public static BusinessDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BusinessDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            BusinessDatabase.class,
                            "user_database"   // database name
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
