package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class exerciseEditior extends AppCompatActivity {

    private int numberOfSets;
    TextView setsNum;
    EditText exerciseName;
    EditText exerciseDes;
    Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_editior);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setsNum = findViewById(R.id.numOfSets);
        exerciseName = findViewById(R.id.getExceriseName);
        exerciseDes = findViewById(R.id.getDescription);
        setsNum.setText("0");
        numberOfSets = 1;
        exercise = new Exercise();
    }

    public void saveExercise(View v){
        exercise.setName(exerciseName.getText().toString());
        exercise.setDescription((exerciseDes.getText().toString()));
        Intent cycleEditor = new Intent(exerciseEditior.this, cyclesEditor.class);
        cycleEditor.putExtra(e)
    }


    public void addNumber(View v){
        numberOfSets +=1;
        exercise.setSets(numberOfSets);
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void subtractNumber(View v){
        if(numberOfSets > 1){
            numberOfSets -=1;
        }
        exercise.setSets(numberOfSets);
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void goback(View v){
        finish();
    }
}
