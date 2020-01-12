package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class cyclesEditor extends AppCompatActivity {

    private int numberOfCycle = 0;
    TextView cycleNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycles_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cycleNum = findViewById(R.id.numOfCycles);
    }

    public void newExercise(View v){
        Intent newExercise = new Intent(cyclesEditor.this, exerciseEditior.class);
        startActivity(newExercise);
    }

    public void goback(View v){
        finish();
    }

    public void addNumber(View v){
        numberOfCycle +=1;
        cycleNum.setText(String.valueOf(numberOfCycle));
    }

    public void subtractNumber(View v){
        if(numberOfCycle > 0){
            numberOfCycle -=1;
        }
        cycleNum.setText(String.valueOf(numberOfCycle));
    }


}
