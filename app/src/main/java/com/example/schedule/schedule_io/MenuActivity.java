package com.example.schedule.schedule_io;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_menu);
    }

    public void Menu(View view){
        setContentView(R.layout.main_menu);
    }

    public void Tuto(View view) {
        setContentView(R.layout.tuto);
    }

    public void Leaderboard(View view) {
        Intent switchActivityIntent = new Intent(this, LeaderboardActivity.class);
        startActivity(switchActivityIntent);
    }

    public void Play(View view){
        Intent switchActivityIntent = new Intent(this, WordleActivity.class);
        startActivity(switchActivityIntent);
    }

    public void SignOut(View view){
        FirebaseAuth.getInstance().signOut();
        Intent LoginIntent = new Intent(this, LoginActivity.class);
        startActivity(LoginIntent);

    }


}