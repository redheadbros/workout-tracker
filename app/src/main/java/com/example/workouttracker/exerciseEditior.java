package com.example.workouttracker;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class exerciseEditior extends AppCompatActivity {

    private int numberOfSets = 0;
    TextView setsNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_editior);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setsNum = (TextView)findViewById(R.id.numOfSets);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public int getNumberOfSets(View v){
        return numberOfSets;
    }

    public void addNumber(View v){
        numberOfSets +=1;
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void subtractNumber(View v){
        if(numberOfSets > 0){
            numberOfSets -=1;
        }
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void goback(View v){
        finish();
    }
}
