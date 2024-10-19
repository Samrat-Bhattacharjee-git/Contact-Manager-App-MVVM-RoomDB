package com.example.contacts_manager_app_roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contacts_manager_app_roomdb.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private AddNewContactClickHandler handler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        contacts =new Contacts();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);

        MyViewModel myViewModel=new ViewModelProvider(this).get(MyViewModel.class);

        handler=new AddNewContactClickHandler(this,contacts,myViewModel);

        binding.setContactDetails(contacts);
        binding.setClickHandler(handler);


    }
}