package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SelectWorkout extends AppCompatActivity {
  private Button button;
  private WorkoutList workouts;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_workout);

    button = (Button)findViewById(R.id.buttonh);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openhistory();
      }
    });

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkoutEditor = new Intent(SelectWorkout.this, WorkoutEditor.class);
        startActivity(gotoWorkoutEditor);
      }
    });

    workouts = getSampleWorkoutList();
  }

  public void openWorkout(View v) {
    Intent gotoWorkout = new Intent(SelectWorkout.this, WorkoutDescription.class);

    gotoWorkout.putExtra("com.example.workouttracker.workout", "workoutDataHere");
    startActivity(gotoWorkout);
  }

  public void openhistory(){
    Intent intent = new Intent(SelectWorkout.this, History.class);
    startActivity(intent);
  }

  public void newWorkout(View v){
    Intent gotoWorkoutEditior = new Intent(SelectWorkout.this, WorkoutEditor.class);
    startActivity(gotoWorkoutEditior);
  }


  private WorkoutList getSampleWorkoutList() {
    //workout 1
    Exercise e1 = new Exercise("exercise 1:", "bro do you even lift", 69);
    ArrayList<Exercise> exercises1 = new ArrayList<>();
    exercises1.add(e1);
    Cycle c1 = new Cycle("Buff dude time", exercises1, 420);
    ArrayList<Cycle> cycles1 = new ArrayList<>();
    cycles1.add(c1);

    //workout 2
    Exercise e2 = new Exercise("exercise 2:", "bro do you even sift", 68);
    ArrayList<Exercise> exercises2 = new ArrayList<>();
    exercises2.add(e2);
    Cycle c2 = new Cycle("Tuff dude time", exercises2, 421);
    ArrayList<Cycle> cycles2 = new ArrayList<>();
    cycles1.add(c2);

    //by your powers combined
    Workout w1 = new Workout("Test workout 1", cycles1);
    Workout w2 = new Workout("Test Workout 2", cycles2);
    ArrayList<Workout> testWorkouts = new ArrayList<>();
    testWorkouts.add(w1);
    testWorkouts.add(w2);

    //create workout list
    WorkoutList workoutList = new WorkoutList();
    workoutList.setWorkoutList(testWorkouts);

    return workoutList;
  }
}
