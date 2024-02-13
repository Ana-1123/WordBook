package com.example.wordbook;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    ImageView show_hide_password;
    Button loginButton;
    TextView register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        register = findViewById(R.id.textViewRegister);
        show_hide_password = findViewById(R.id.passwordToggleImageView);

        loginButton.setOnClickListener(v -> logIntoAccount());
        register.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
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
    private void logIntoAccount()
    {
        String email, password;
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter your password!", Toast.LENGTH_LONG).show();
            return;
        }
        // sign in existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                                "Logged in!",
                                                Toast.LENGTH_LONG)
                                        .show();
                                // if sign-in is successful
                                // intent to main activity
                                Intent intent
                                        = new Intent(Login.this,
                                        Home.class);
                                startActivity(intent);
                            }

                            else {

                                // sign-in failed
                                Toast.makeText(getApplicationContext(),
                                                "Log in failed!",
                                                Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
    }
}