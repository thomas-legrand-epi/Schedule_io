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
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView LeWord , RegisterUser , Back_To_Log;
    private EditText editTextName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;



    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        LeWord = (TextView) findViewById(R.id.LeWord1);
        LeWord.setOnClickListener(this);

        RegisterUser = (Button) findViewById(R.id.register_button);
        RegisterUser.setOnClickListener(this);

        Back_To_Log = (Button) findViewById(R.id.Login_Redirect);
        Back_To_Log.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextEmail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword2);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Login_Redirect:
                Intent LoginIntent = new Intent(this, LoginActivity.class);
                startActivity(LoginIntent);
                break;
            case R.id.register_button:
                registerUser();
                break;
        }


    }

    private void registerUser() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;

        }

        if(password.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;

        }

        if(name.isEmpty()){
            editTextName.setError("name is required");
            editTextName.requestFocus();
            return;

        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Provide a correct email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.length()<6){
            editTextPassword.setError("Min pass length is 6 character");
            editTextPassword.requestFocus();
            return;

        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,email);

                            FirebaseDatabase.getInstance("https://leword-549be-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                        if(task1.isSuccessful()){
                                            Toast.makeText(RegisterUser.getContext(), "User has been registered", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);




                                        }
                                        else{
                                            Toast.makeText(RegisterUser.getContext(),"User has not been registered",Toast.LENGTH_LONG).show();
                                        }
                                        progressBar.setVisibility(View.GONE);
                                    });


                        }
                        else{
                            Toast.makeText(RegisterUser.getContext(),"User has not been registered",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}