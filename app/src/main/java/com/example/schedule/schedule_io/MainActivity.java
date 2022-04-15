package com.example.schedule.schedule_io;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void Wordle(View view){
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
        setContentView(R.layout.activity_main);
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
        int i = 0;
        TextView current = (TextView)findViewById(ids[i]);
        TextView t = (TextView)view;
        i++;
        while(current.getText().toString()!="" && i<ids.length)
        {
            current = (TextView)findViewById(ids[i]);
            i++;
        }

        current.setText(t.getText().toString());
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
        int i = ids.length-1;
        TextView current = (TextView)findViewById(ids[i]);
        i--;
        while(current.getText().toString()=="" && i>=0)
        {
            current = (TextView)findViewById(ids[i]);
            i--;
        }
        current.setText("");
    }
}