package com.example.contacts_manager_app_roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class},version = 1)
public abstract class contactDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDao();

    //singleton design pattern
    private static contactDatabase dbInstance;

    static synchronized contactDatabase getInstance(Context context){

        if(dbInstance==null){
            dbInstance= Room.databaseBuilder(context.getApplicationContext(), contactDatabase.class,"contacts_db")
                    .fallbackToDestructiveMigration().build();
        }
        return dbInstance;
    }
}
