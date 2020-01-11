package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class WorkoutDescription extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workout_description);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkout = new Intent(WorkoutDescription.this, ActiveWorkout.class);
        startActivity(gotoWorkout);
      }
    });

    FloatingActionButton fab2 = findViewById(R.id.fab2);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkoutEditior = new Intent(WorkoutDescription.this, workoutEditor.class);
        startActivity(gotoWorkoutEditior);
      }
    });
  }

}
