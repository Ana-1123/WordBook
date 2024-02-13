package com.example.wordbook;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText emailEditText, passwordEditText;

    ImageView show_hide_password;
    Button registerButton;
    TextView login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        registerButton = findViewById(R.id.buttonRegister);
        login = findViewById(R.id.textViewLogin);
        show_hide_password = findViewById(R.id.passwordToggleImageView);

        registerButton.setOnClickListener(v -> registerNewUser());

        login.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });
        show_hide_password.setOnClickListener(v -> {
            if(passwordEditText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance()))
            {
                passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                show_hide_password.setImageResource(R.drawable.ic_hide_password);
            }else{
                passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                show_hide_password.setImageResource(R.drawable.ic_show_password);
            }
        });
    }

    private void registerNewUser() {
        String email, password;
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        // Validations for input email and password
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter your password!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(getApplicationContext(), "Enter!", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.length()<6)
        {
            Toast.makeText(getApplicationContext(), "Password must have at least 6 characters", Toast.LENGTH_LONG).show();
            return;
        }
        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),
                                        "Successful registration!",
                                        Toast.LENGTH_LONG)
                                .show();

                        // if the user created intent to login activity
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    } else {

                        // Registration failed
                        Toast.makeText(
                                        getApplicationContext(),
                                        "Registration failed!"
                                                + "Try later.",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }
}