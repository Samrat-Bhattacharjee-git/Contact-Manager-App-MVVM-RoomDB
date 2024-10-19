package com.example.contacts_manager_app_roomdb;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class respository {

    //available data sources:
    //ROOM Database
    private final ContactDAO contactDAO;
    ExecutorService executor;
    android.os.Handler handler;

    public respository(Application application) {

        contactDatabase contactdatabase=contactDatabase.getInstance(application);
        this.contactDAO = contactdatabase.getContactDao();

        //used for background database operations
         executor= Executors.newSingleThreadExecutor();

        //used for updating the UI
         handler=new Handler(Looper.getMainLooper());
    }

    public void addContact(Contacts contacts){
        //Runnable:Executing task on separate thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });
    }

    public void deleteContacts(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });

    }

    public LiveData<List<Contacts>> getallcontacts(){
        return contactDAO.getallcontacts();
    }
}
