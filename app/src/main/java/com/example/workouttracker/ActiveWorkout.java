package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.HistoryData;
import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.Record;
import com.example.workouttracker.datastructure.Workout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.Date;

public class ActiveWorkout extends AppCompatActivity {
  private Workout workout;
  private Record record = new Record();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_active_workout);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //get input workout
    Intent intent = getIntent();
    workout = (Workout) intent.getSerializableExtra("workout");

    //setup recyclerView:
    //set layout manager
    RecyclerView workoutView = findViewById(R.id.active_workout_recycler_view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    workoutView.setLayoutManager(layoutManager);

    //set adapter
    ActiveWorkoutAdapter adapter = new ActiveWorkoutAdapter(this, workout);
    workoutView.setAdapter(adapter);
  }

  public void finishWorkout(View v) {
    //Intent gotoDescription = new Intent(ActiveWorkout.this, WorkoutDescription.class);

    //startActivity(gotoDescription);

    record.setRecord(workout);
    saveHistory();

    finish();
  }

  public void saveHistory(){
    HistoryData historyData = Json.loadFromJson(getApplicationContext(), HistoryData.class, "HISTORY.json");
    if(historyData == null){
      historyData = new HistoryData();
    }
    historyData.addHistory(record);
    Json.saveToJson(getApplicationContext(), historyData, "HISTORY.json");
  }



}
