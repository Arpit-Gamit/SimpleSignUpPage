package com.example.sign_up_page;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sign_up_page.databinding.ActivityThirdPageBinding;

public class third_page extends AppCompatActivity {

    ActivityThirdPageBinding binding;
    DatabaseHelper db;
    EditText u2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = new DatabaseHelper(this);

        Intent k = getIntent();
        String name = k.getStringExtra("name");
        u2 = findViewById(R.id.Uname);
        u2.setText(name);
        binding.UButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name;

                String email = binding.UEmail.getText().toString();
                String password = binding.UPassword.getText().toString();

                if( username.equals("") || email.equals("") || password.equals(""))
                {
                    Toast.makeText(third_page.this, "Provide all Information!!", Toast.LENGTH_SHORT).show();
                }
                else{

                    boolean result = db.update(username,email,password);

                    if(result == true)
                    {
                        Toast.makeText(third_page.this, "Data Updated Successfully!!", Toast.LENGTH_SHORT).show();
                        Intent o = new Intent(getApplicationContext(),userlist.class);
                        startActivity(o);
                    }
                    else{
                        Toast.makeText(third_page.this, "User Not Avaliable!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.VButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent q = new Intent(getApplicationContext(),userlist.class);
                startActivity(q);
            }
        });
        binding.GLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(x);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}