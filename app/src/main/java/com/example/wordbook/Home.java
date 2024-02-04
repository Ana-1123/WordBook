package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button logOut, addWord, allWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logOut = findViewById(R.id.logOut);
        addWord = findViewById(R.id.addWord);
        allWords = findViewById(R.id.allWords);

        logOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Home.this, MainActivity.class);
            startActivity(intent);
        });

        addWord.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, AddWord.class);
            startActivity(intent);
        });

        allWords.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, AllWords.class);
            startActivity(intent);
        });
    }
}