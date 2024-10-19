package com.example.contacts_manager_app_roomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    //if you need to use context inside your viewModel
    //you need to use androidViewModel instead of ViewModel
    //because it content application context

    //Respository
    private respository myrespositery;

    //Live Data
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myrespositery = new respository(application);
    }

    //AndroidViewModel is a subclass of ViewModel
    //and similar to them ,they are designed to store and manage
    //UI related data are responsible to
    //prepare and provide data for UI and automatically
    //allow data to survive configuration change

    public LiveData<List<Contacts>> getallcontacts(){
        allContacts=myrespositery.getallcontacts();
        return allContacts;
    }

    public void addContact(Contacts contacts){
        myrespositery.addContact(contacts);
    }

    public void deleteContacts(Contacts contacts){
        myrespositery.deleteContacts(contacts);

    }

}
