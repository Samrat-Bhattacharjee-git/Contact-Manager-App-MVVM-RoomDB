package com.example.contacts_manager_app_roomdb;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {

    Context context;
    Contacts contacts;

    MyViewModel myViewModel;

    public AddNewContactClickHandler(Context context, Contacts contacts, MyViewModel myViewModel) {
        this.context = context;
        this.contacts = contacts;
        this.myViewModel = myViewModel;
    }


    public void onAddNewContactClicked(View view){
        if(contacts.getName()==null || contacts.getEmail()==null){
            Toast.makeText(context, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i=new Intent(context,MainActivity.class);
//        i.putExtra("NAME",contacts.getName());
//        i.putExtra("EMAIL",contacts.getEmail());

            Contacts c=new Contacts(contacts.getName(),contacts.getEmail());

            myViewModel.addContact(c);

            context.startActivity(i);

        }

    }
}
