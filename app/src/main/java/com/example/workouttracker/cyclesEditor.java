package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class cyclesEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycles_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cancel = findViewById(R.id.cancelButton);

    }

    public void goWorkoutEditior(View v){
        Intent goWorkoutEditior = new Intent(cyclesEditor.this, workoutEditor.class);
        startActivity(goWorkoutEditior);
    }

    public void newExercise(View v){
        Intent newExercise = new Intent(cyclesEditor.this, exerciseEditior.class);
        startActivity(newExercise);
    }

    public void goback(View v){
        finish();
    }



}
