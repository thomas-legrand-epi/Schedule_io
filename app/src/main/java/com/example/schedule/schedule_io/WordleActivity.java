package com.example.schedule.schedule_io;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WordleActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    String[] words = new String[5757];
    String solution = "";
    int stage = 0;
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
            R.id.guess24,
            R.id.guess25,
            R.id.guess26,
            R.id.guess27,
            R.id.guess28,
            R.id.guess29
    };
    int[] keyboard = {
            R.id.keyboard,
            R.id.keyboard2,
            R.id.keyboard3,
            R.id.keyboard4,
            R.id.keyboard5,
            R.id.keyboard6,
            R.id.keyboard7,
            R.id.keyboard8,
            R.id.keyboard9,
            R.id.keyboard10,
            R.id.keyboard11,
            R.id.keyboard12,
            R.id.keyboard13,
            R.id.keyboard14,
            R.id.keyboard15,
            R.id.keyboard16,
            R.id.keyboard17,
            R.id.keyboard18,
            R.id.keyboard19,
            R.id.keyboard21,
            R.id.keyboard22,
            R.id.keyboard23,
            R.id.keyboard24,
            R.id.keyboard25,
            R.id.keyboard26,
            R.id.keyboard27
    };
    int score = 0;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.wordle);
        mAuth = FirebaseAuth.getInstance();
        TextView time = (TextView)findViewById(R.id.textView27);
        timer = new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(millisUntilFinished/60000+":"+(millisUntilFinished % 60000)/1000);
            }

            public void onFinish() {
                this.cancel();
                finalscore();
                updateScore(score);

            }
        };
        timer.start();
        BufferedReader reader;

        try{
            final InputStream file = getAssets().open("dictionary.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            int x = 0;
            while(line != null){
                Log.d("StackOverflow", line);
                line = reader.readLine();
                words[x] = line;
                x++;
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        double rnd = Math.random() * 5757;
        solution = words[(int)rnd];

        TextView isscore = findViewById(R.id.isword);
        isscore.setText("Your score is : " + score);
    }

    public void finalscore(){
        timer.cancel();
        Intent switchActivityIntent = new Intent(this, ScoreActivity.class);
        switchActivityIntent.putExtra("score", score);
        startActivity(switchActivityIntent);
    }

    public void Menu(View view){
        timer.cancel();
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }

    public void Guess(View view){
        if(stage>=30){
            reset();
        }
        int i = stage;
        if(i<30) {
            TextView current = (TextView) findViewById(ids[i]);
            TextView t = (TextView) view;
            i++;
            while (current.getText().toString() != "" && i < stage + 5) {
                current = (TextView) findViewById(ids[i]);
                i++;
            }
            if (current.getText().toString() == "") {
                current.setText(t.getText().toString());
            }
        }
    }

    public void backspace(View view){
        if(stage>=0) {
            int i = 4;
            TextView current = (TextView) findViewById(ids[stage + i]);
            i--;
            while (current.getText().toString() == "" && i >= 0) {
                current = (TextView) findViewById(ids[stage + i]);
                i--;
            }
            current.setText("");
        }
    }

    public void Enter(View view){
        if(stage>=30){
            reset();
        }
        else{
            TextView isword = (TextView) findViewById(R.id.isword);
            String guess = "";
            TextView current;
            for (int a = 0; a < 5; a++) {
                current = (TextView) findViewById(ids[stage + a]);
                guess += current.getText().toString().toLowerCase(Locale.ROOT);
            }
            if (!Arrays.asList(words).contains(guess)) {
                isword.setText("Not a word.");
            } else {
                int[] rights = right(guess);
                ArrayList<Integer> checked = new ArrayList<>();
                TextView key;
                for (int a = 0; a < 5; a++) {
                    current = (TextView) findViewById(ids[stage + a]);
                    key = findViewById(keyboard[0]);
                    int i = 1;
                    while (key.getText().toString() != current.getText().toString() && i < 26) {
                        key = findViewById(keyboard[i]);
                        i++;
                    }
                    if(!checked.contains(key.getId()))
                        key.setBackground(getDrawable(R.drawable.rounded_corner_dark));
                    if (rights[a]==1) {
                        current.setBackground(getDrawable(R.drawable.rounded_corner_green));
                        key.setBackground(getDrawable(R.drawable.rounded_corner_green));
                        checked.add(key.getId());
                    }
                    if (rights[a]==0) {
                        current.setBackground(getDrawable(R.drawable.rounded_corner_yellow));
                        if(!checked.contains(key.getId()))
                            key.setBackground(getDrawable(R.drawable.rounded_corner_yellow));
                    }
                    isword.setText(isword.getText().toString() + rights[a]);
                }

                if (guess.equals(solution)) {
                    score++;
                    stage = 30;
                    isword.setText("Your score is : " + score);
                }
                else {
                    isword.setText("Your score is : " + score);
                    stage += 5;
                }
            }
        }
    }

    public void pass(View view){
        reset();
    }

    public void reset(){
        double rnd = Math.random() * 5757;
        solution = words[(int) rnd];
        stage = 0;
        int i = 1;
        TextView current = (TextView)findViewById(ids[0]);
        while(current.getText().toString()!="" && i<30){
            current.setText("");
            current.setBackground(getDrawable(R.drawable.rounded_corner));
            current = (TextView)findViewById(ids[i]);
            i++;
        }
        current.setText("");
        current.setBackground(getDrawable(R.drawable.rounded_corner));
        TextView key = (TextView)findViewById(keyboard[0]);
        i=1;
        while(key.getText().toString()!="" && i<26){
            key.setBackground(getDrawable(R.drawable.keyboard));
            key = (TextView)findViewById(keyboard[i]);
            i++;
        }
        key.setBackground(getDrawable(R.drawable.keyboard));
    }

    public int[] In(){
        int[] times = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for (int a = 0; a<5; a++){
            times[((int)solution.charAt(a))-97]++;
        }
        return times;
    }

    public int[] right(String guess){
        int[] rights = {-1,-1,-1,-1,-1};
        int[] times = In();
        //every letter in both words
        for (int i = 0; i<5; i++){
            if(times[((int)guess.charAt(i))-97]>0) {
                times[((int) guess.charAt(i)) - 97]--;
                rights[i] = 0;
            }
        }
        for (int i = 0; i<5; i++){
            //letters at the right place
            if(guess.charAt(i)==solution.charAt(i)) {
                times[((int) guess.charAt(i)) - 97]++;
                rights[i] = 1;
            }
            for (int j = 0; j<5; j++){
                if (rights[i]==1 && times[((int)guess.charAt(i))-97]<1) {
                    rights[j] = -1;
                }
            }
        }
        return rights;
    }

    public void updateScore(int score){
        DatabaseReference rootref = FirebaseDatabase.getInstance("https://leword-549be-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
        Map<String,Object> map = new HashMap<>();
        map.put("score",score);
        rootref.child("Users").child(mAuth.getUid()).updateChildren(map);

    }
}
