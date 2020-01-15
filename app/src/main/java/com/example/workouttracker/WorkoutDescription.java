package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;

public class WorkoutDescription extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workout_description);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //setup the 'play' button
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkout = new Intent(WorkoutDescription.this, ActiveWorkout.class);
        startActivity(gotoWorkout);
      }
    });

    //setup the 'edit' button
    FloatingActionButton fab2 = findViewById(R.id.fab2);
    fab2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkoutEditior = new Intent(WorkoutDescription.this, workoutEditor.class);
        startActivity(gotoWorkoutEditior);
      }
    });

    //setup description
    Intent intent = getIntent();
    //replace this with 'workoutData' when you can actually pass it
    Object workoutDataDefault = intent.getStringExtra("com.example.workouttracker.workout");

    //somehow get the workout object itself from whatever workoutData is
    Workout workoutData = makeSampleWorkout();

  }

  private Workout makeSampleWorkout() {
    Exercise exercise1 = new Exercise();
    Exercise exercise2 = new Exercise();
    Exercise exercise3 = new Exercise();

    exercise1.setName("pushups");
    exercise2.setName("crunches");
    exercise3.setName("squats");

    exercise1.setDescription("Lay down");
    exercise2.setDescription("Try not to cry");
    exercise3.setDescription("Cry a lot");

    ArrayList<Exercise> exercises = new ArrayList<>();
    exercises.add(exercise1);
    exercises.add(exercise2);
    exercises.add(exercise3);

    Cycle cycle1 = new Cycle("Denial", (ArrayList<Exercise>) exercises.clone(), 5);
    Cycle cycle2 = new Cycle("Anger", (ArrayList<Exercise>) exercises.clone(), 3);
    Cycle cycle3 = new Cycle("Bargaining", (ArrayList<Exercise>) exercises.clone(), 4);
    Cycle cycle4 = new Cycle("Depression", (ArrayList<Exercise>) exercises.clone(), 17);
    Cycle cycle5 = new Cycle("Acceptance", (ArrayList<Exercise>) exercises.clone(), 1);

    ArrayList<Cycle> cycles = new ArrayList<>();
    cycles.add(cycle1);
    cycles.add(cycle2);
    cycles.add(cycle3);
    cycles.add(cycle4);
    cycles.add(cycle5);

    Workout sampleWorkout = new Workout("Stages of Fitness", cycles);
    return sampleWorkout;
  }
}
