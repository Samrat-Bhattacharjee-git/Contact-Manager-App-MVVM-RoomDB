package com.example.contacts_manager_app_roomdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contacts_manager_app_roomdb.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Data Source
    private contactDatabase database;
    private ArrayList<Contacts> contactsArrayList=new ArrayList<>();

    //Adapter
    private MyAdapter myAdapter;

    //Binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        handlers=new MainActivityClickHandler(this);

        mainBinding.setClickHandler(handlers);

        //RecyclerView
        RecyclerView recyclerView=mainBinding.recycle;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //database
        database=contactDatabase.getInstance(this);

        //View model
        MyViewModel viewModel=new ViewModelProvider(this).get(MyViewModel.class);

        //inserting
//        Contacts c1=new Contacts("Rancho","rancho@gmail.com");
//        viewModel.addContact(c1);

        //Loading data from room db
        viewModel.getallcontacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                contactsArrayList.clear();

                for(Contacts c: contacts){

                    contactsArrayList.add(c);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        //adapter
        myAdapter=new MyAdapter(contactsArrayList);

        //linking the recyclerview with adapter
        recyclerView.setAdapter(myAdapter);

        //swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //swipe the items to left to delete

                Contacts c=contactsArrayList.get(viewHolder.getAdapterPosition());

                viewModel.deleteContacts(c);
            }

        }).attachToRecyclerView(recyclerView);

    }
}