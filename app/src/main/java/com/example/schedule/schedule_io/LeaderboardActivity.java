package com.example.schedule.schedule_io;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceUser;
    private List<User> users = new ArrayList<>();
    int[] ranks = {
            R.id.textTop1,
            R.id.textTop2,
            R.id.textTop3,
            R.id.textTop4,
            R.id.textTop5,
            R.id.textTop6,
            R.id.textTop7,
            R.id.textTop8,
            R.id.textTop9,
            R.id.textTop10
    };

    public interface DataStatus{
        void DataIsLoaded(List<User> users, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        mDataBase = FirebaseDatabase.getInstance("https://leword-549be-default-rtdb.europe-west1.firebasedatabase.app/");
        mReferenceUser = mDataBase.getReference("Users");

        mReferenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Get map of users in datasnapshot
                int i = 0;
                TextView current;
                for (DataSnapshot user: dataSnapshot.getChildren()) {
                    if(i<10) {
                        current = findViewById(ranks[i]);
                        current.setText(user.getValue(User.class).fullName + " : " + user.getValue(User.class).score + "");
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //handle databaseError
            }
        });
    }

    public void readData(final DataStatus dataStatus){
        mReferenceUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    users.add(user);

                }
                dataStatus.DataIsLoaded(users,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void Menu(View view){
        Intent switchActivityIntent = new Intent(this, MenuActivity.class);
        startActivity(switchActivityIntent);
    }
}