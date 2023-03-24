package com.example.sign_up_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sign_up_page.databinding.ActivityLoginBinding;
import com.example.sign_up_page.databinding.ActivityMainBinding;

import java.util.Random;

public class loginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    DatabaseHelper databaseHelper;
    String captchaCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseHelper = new DatabaseHelper(this);

        refreshCaptcha();
        binding.imageViewCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshCaptcha();
            }
        });

        binding.LButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String username = binding.logusername.getText().toString();
                    String password = binding.Lpassword.getText().toString();
                    String CText = binding.CaptchaText.getText().toString().trim();
                    
                    if(username.equals("") || password.equals("") || CText.equals(""))
                    {
                        Toast.makeText(loginActivity.this, "Provide All Information!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        boolean checkLogin = databaseHelper.checkusernamepassword(username,password);

                        boolean isCValid = CText.equals(captchaCode);
                        if(checkLogin == true && isCValid == true)
                        {
                            Toast.makeText(loginActivity.this, "Login Successfully!!", Toast.LENGTH_SHORT).show();
                            Intent j = new Intent(getApplicationContext(),userlist.class);
                            startActivity(j);
                        }
                        else if (checkLogin == false){
                            Toast.makeText(loginActivity.this, "Not a an Account!!", Toast.LENGTH_SHORT).show();
                        }
                        else if (isCValid == false)
                        {
                            Toast.makeText(loginActivity.this, "Please Provide Correct Captcha!!", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
        binding.signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(c);
            }
        });


    }

    private void refreshCaptcha() {
        captchaCode = generateCaptchaCode();
        Bitmap captchaBitmap = generateCaptchaBitmap(captchaCode);
        binding.imageViewCaptcha.setImageBitmap(captchaBitmap);
    }

    private String generateCaptchaCode() {
        // Generate a random 6-digit code
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private Bitmap generateCaptchaBitmap(String captchaCode) {
        // Generate a bitmap image of the Captcha code
        int width = 200;
        int height = 80;
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.LTGRAY);
        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setColor(Color.BLACK);
        canvas.drawText(captchaCode, 30, 50, paint);
        return bitmap;
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}