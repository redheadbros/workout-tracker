package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectWorkout extends AppCompatActivity {
  private ImageView imageView;
  private ImageView iV;
  private Button button;
  private WorkoutList workouts;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_workout);

    imageView = findViewById(R.id.imageView3);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openHistory();
      }
    });

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    iV = findViewById(R.id.imageView4);
    iV.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkoutEditor = new Intent(SelectWorkout.this, WorkoutEditor.class);
        startActivity(gotoWorkoutEditor);
      }
    });

    //workouts = getSampleWorkoutList();
    workouts = Json.loadFromJson(getApplicationContext(),WorkoutList.class,"WORKOUT.json");
    if(workouts == null){
      workouts = new WorkoutList();
    }

    RecyclerView recyclerView = findViewById(R.id.select_workout_recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    SelectWorkoutAdapter adapter = new SelectWorkoutAdapter(this, workouts);
    recyclerView.setAdapter(adapter);
  }

  public void openHistory(){
    Intent intent = new Intent(SelectWorkout.this, History.class);
    startActivity(intent);
  }

  public void newWorkout(View v){
    Intent gotoWorkoutEditor = new Intent(SelectWorkout.this, WorkoutEditor.class);
    startActivity(gotoWorkoutEditor);
  }

}
