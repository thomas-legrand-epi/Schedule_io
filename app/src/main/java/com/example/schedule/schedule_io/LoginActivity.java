package com.example.schedule.schedule_io;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView sign_up_log_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_up_log_btn = (TextView) findViewById(R.id.sign_up_log_btn);
        sign_up_log_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.sign_up_log_btn:
                Intent RegisterIntent = new Intent(this, SignUpActivity.class);
                startActivity(RegisterIntent);
                break;
        }

    }
}