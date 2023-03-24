package com.example.sign_up_page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sign_up_page.databinding.ActivityLoginBinding;
import com.example.sign_up_page.databinding.ActivityUserlistBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class userlist extends AppCompatActivity {


    RecyclerView recyclerView;
    Button button;
    ArrayList<String> name,email,password;
    DatabaseHelper db;
    MyAdapter adapter;
    ActivityUserlistBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityUserlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(this);
        name = new ArrayList<>();
        email = new ArrayList<>();
        password = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,name,email,password);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  h = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(h);
            }
        });
        new ItemTouchHelper(simpleCallback1).attachToRecyclerView(recyclerView);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        displaydata();
//        adapter.setOnItemClickListener(new MyAdapter.onItemClickListener() {
//
//            @Override
//            public void onItemDlt(int position) {
//
//                boolean dltdata = db.delete(String.valueOf(name));
//                if(dltdata == true)
//                {
//                    Toast.makeText(userlist.this, "Data Deleted Successfully!!", Toast.LENGTH_SHORT).show();
//                    name.remove(position);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//            @Override
//            public void onItemEdit(int position) {
//                Toast.makeText(userlist.this, "Clicked on Edit Button", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void displaydata() {

        Cursor cursor = db.getdata();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data Found!!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while(cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                email.add(cursor.getString(1));
                password.add(cursor.getString(2));
            }
        }
    }

//    String deletedata;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
//            deletedata = name.get(position);

            db.delete(name.get(position));
            name.remove(position);
            adapter.notifyDataSetChanged();

//            Snackbar.make(recyclerView,deletedata,Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    db.insert(name.get(position),email.get(position),password.get(position));
//                    name.add(position,deletedata);
//                    email.add(position,deletedata1);
//                    password.add(position,deletedata2);
//                    adapter.notifyDataSetChanged();
//                }
//            }).show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(userlist.this, R.color.Deletecolor))
                    .addSwipeLeftActionIcon(R.drawable.deleteicon)
                    .addSwipeLeftLabel("Delete")
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    String data;
    ItemTouchHelper.SimpleCallback simpleCallback1 = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Intent g = new Intent(getApplicationContext(),third_page.class);
            startActivity(g);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(userlist.this, R.color.teal_200))
                    .addSwipeRightActionIcon(R.drawable.editicon)
                    .addSwipeRightLabel("Edit")
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}