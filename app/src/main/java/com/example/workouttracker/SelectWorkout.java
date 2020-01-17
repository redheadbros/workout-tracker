package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.WorkoutList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectWorkout extends AppCompatActivity {
  private Button button;

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
        Intent gotoWorkoutEditior = new Intent(SelectWorkout.this, WorkoutEditor.class);
        startActivity(gotoWorkoutEditior);
      }
    });
  }

  public void openWorkout(View v) {
    Intent gotoWorkout = new Intent(SelectWorkout.this, WorkoutDescription.class);

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
}
