package com.example.schedule.schedule_io;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.score);
        TextView fs = findViewById(R.id.finalscore);
        fs.setText("Your final score is : "+getIntent().getIntExtra("score",0));
    }

    public void again(View view){
        Intent switchActivityIntent = new Intent(this, WordleActivity.class);
        startActivity(switchActivityIntent);
    }

    public void Menu(View view){
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }
}
