package com.example.sign_up_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sign_up_page.databinding.ActivityMainBinding;

import java.net.PasswordAuthentication;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.SButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the data which has been entered on edittext.
                String username = binding.username.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();
                String confirmpass = binding.confPassword.getText().toString();
                if(username.equals("") || email.equals("") || password.equals("") || confirmpass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Provide All Information!!", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    // if you want to try you can use this regression code for validation

//                    (/^
//                    (?=.*\d)                //should contain at least one digit
//                    (?=.*[@$%&#])           //should contain at least one special char
//                    (?=.*[A-Z])             //should contain at least one upper case
//                    [a-zA-Z0-9]{4,}         //should contain at least 8 from the mentioned characters
//                    $/)
                    boolean checkUser = databaseHelper.checkusername(username);
                    if(checkUser == false) {

                        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            boolean checkEmail = databaseHelper.checkuseremail(email);
                            if (checkEmail == false) {
                                if (password.length() >= 6) {
                                    if (password.equals(confirmpass)) {
                                        boolean insert = databaseHelper.insert(username, email, password);
                                        if (insert == true) {
                                            Toast.makeText(MainActivity.this, "Data inserted successfully!!!", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), loginActivity.class);
                                            startActivity(i);
                                            Toast.makeText(MainActivity.this, "Login Yourself!!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Signup Failed!!", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "Password Must Be Same!!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "password length should be greater than 6!!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Email is already Taken!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Enter Valid Email Address!!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                                Toast.makeText(MainActivity.this, "User Already Exits!", Toast.LENGTH_SHORT).show();
                           }
                }
            }
        });

        // Textview to go back to login page.
        binding.LoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),loginActivity.class);
                startActivity(a);
            }
        });

    }

    // Method to disable back button.
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}