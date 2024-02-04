package com.example.wordbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login);
        registerButton = findViewById(R.id.register);

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });
        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        });
    }

}