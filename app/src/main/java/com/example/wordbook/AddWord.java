package com.example.wordbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddWord extends AppCompatActivity {
    Button addWord;
    EditText wordText, meaningText, sourceText;
    FirebaseAuth mAuth;
    FirebaseUser loggedUser;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        mAuth = FirebaseAuth.getInstance();
        loggedUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users").child(loggedUser.getUid());

        ImageButton backButton = findViewById(R.id.backButton);
        addWord = findViewById(R.id.buttonAddWord);
        wordText = findViewById(R.id.editTextWord);
        meaningText = findViewById(R.id.editTextMeaning);
        sourceText = findViewById(R.id.editTextSource);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddWord.this, Home.class);
            startActivity(intent);
        });

        addWord.setOnClickListener(v -> addWordFirebaseData());
    }

    public void addWordFirebaseData(){
        String word, meaning, source;
        word = wordText.getText().toString();
        meaning = meaningText.getText().toString();
        source = sourceText.getText().toString();

        String ID_word = databaseReference.push().getKey();

        if (word.isEmpty() || meaning.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Field word and meaning should be completed!", Toast.LENGTH_LONG).show();
        }
        HashMap<String, String> newWord = new HashMap<>();
        newWord.put("word", word);
        newWord.put("meaning", meaning);
        newWord.put("source",source);
        databaseReference.child(ID_word).setValue(newWord);

    }
}
