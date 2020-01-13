package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import com.example.workouttracker.datastructure.Exercise;

public class cyclesEditor extends AppCompatActivity {

    private int numberOfCycle = 1;
    TextView cycleNum;
    TextView cycleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycles_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cycleNum = findViewById(R.id.numOfCycles);
        cycleName = findViewById(R.id.NameOfCycle);
    }

    public void newExercise(View v){
        Intent newExercise = new Intent(cyclesEditor.this, exerciseEditior.class);
        startActivity(newExercise);
    }

    public void goback(View v){
        finish();
    }

    public void saveCycle(View v){

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
