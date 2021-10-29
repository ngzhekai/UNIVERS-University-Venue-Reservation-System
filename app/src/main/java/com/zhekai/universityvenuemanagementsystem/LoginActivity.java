package com.zhekai.universityvenuemanagementsystem;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private EditText username, passcode;
    static public String UserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent(this, MainActivity.class);

        Button loginBtn = findViewById(R.id.loginBtn);
        username = (EditText) findViewById(R.id.userId);
        passcode = (EditText) findViewById(R.id.password);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && passcode.getText().toString().equals("1234")){
                    Toast.makeText(getApplicationContext(), "Signing In...", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Sign In Successfully!");
                    UserId = username.getText().toString();
                    startActivity(intent);
                    finish();
                }
                else if (username.getText().toString().equals("student1") && passcode.getText().toString().equals("1234"))
                {
                    Toast.makeText(getApplicationContext(), "Signing In...", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Sign In Successfully!");
                    UserId = username.getText().toString();
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Sign In Failed!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Sign In Denied!");
                }
            }
        });

    }
}