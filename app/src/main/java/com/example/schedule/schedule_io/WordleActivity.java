package com.example.schedule.schedule_io;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WordleActivity extends AppCompatActivity {

    String guess = "";
    int stage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.wordle);
        TextView time = (TextView)findViewById(R.id.textView27);
        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(millisUntilFinished/60000+":"+(millisUntilFinished % 60000)/1000);
            }

            public void onFinish() {
                time.setText("done!");
            }
        }.start();
    }

    public void Menu(View view){
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void Guess(View view){
        int[] ids = {
                R.id.guess,
                R.id.guess1,
                R.id.guess2,
                R.id.guess3,
                R.id.guess4,
                R.id.guess5,
                R.id.guess6,
                R.id.guess7,
                R.id.guess8,
                R.id.guess9,
                R.id.guess10,
                R.id.guess11,
                R.id.guess12,
                R.id.guess13,
                R.id.guess14,
                R.id.guess15,
                R.id.guess16,
                R.id.guess17,
                R.id.guess18,
                R.id.guess19,
                R.id.guess20,
                R.id.guess21,
                R.id.guess22,
                R.id.guess23,
                R.id.guess24
        };
        int i = stage;
        if(i<25) {
            TextView current = (TextView) findViewById(ids[i]);
            TextView t = (TextView) view;
            i++;
            while (current.getText().toString() != "" && i < stage + 5) {
                current = (TextView) findViewById(ids[i]);
                i++;
            }
            if (current.getText().toString() == "") {
                guess += current.getText().toString();
                current.setText(t.getText().toString());
            }
        }
    }

    public void backspace(View view){
        int[] ids = {
                R.id.guess,
                R.id.guess1,
                R.id.guess2,
                R.id.guess3,
                R.id.guess4,
                R.id.guess5,
                R.id.guess6,
                R.id.guess7,
                R.id.guess8,
                R.id.guess9,
                R.id.guess10,
                R.id.guess11,
                R.id.guess12,
                R.id.guess13,
                R.id.guess14,
                R.id.guess15,
                R.id.guess16,
                R.id.guess17,
                R.id.guess18,
                R.id.guess19,
                R.id.guess20,
                R.id.guess21,
                R.id.guess22,
                R.id.guess23,
                R.id.guess24
        };
        int i = 4;
        TextView current = (TextView)findViewById(ids[stage+i]);
        i--;
        while(current.getText().toString()=="" && i>=0)
        {
            current = (TextView)findViewById(ids[stage+i]);
            i--;
        }
        current.setText("");
    }

    public void Enter(View view){
        if(stage<25)
            stage += 5;
    }
}
