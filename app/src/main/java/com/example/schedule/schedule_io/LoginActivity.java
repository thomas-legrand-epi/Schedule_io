package com.example.schedule.schedule_io;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView register;
    private EditText editTextMail , editTextPassword;

    private ProgressBar progressBar;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        register = (TextView) findViewById(R.id.sign_up_log_btn);
        register.setOnClickListener(this);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        editTextMail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.sign_up_log_btn:
                Intent RegisterIntent = new Intent(this, SignUpActivity.class);
                startActivity(RegisterIntent);
                break;

            case R.id.button:
                userLogin();
                break;
        }

    }

    private void userLogin() {

        String email = editTextMail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextMail.setError("email is required");
            editTextMail.requestFocus();
            return;

        }

        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;

        }



        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextMail.setError("Provide a correct email");
            editTextMail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Failed to login! Check Your credentials", Toast.LENGTH_LONG).show();
                }




            }
        });


        }
    }
