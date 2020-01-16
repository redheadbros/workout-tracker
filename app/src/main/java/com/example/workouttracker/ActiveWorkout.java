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

import android.view.View;

public class ActiveWorkout extends AppCompatActivity {
  private Workout workout;
  private Record record = new Record();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_active_workout);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  public void finishWorkout(View v) {
    //Intent gotoDescription = new Intent(ActiveWorkout.this, WorkoutDescription.class);

    //startActivity(gotoDescription);

    record.setRecord(workout);

    finish();
  }

  public void saveHistory(View v){
    HistoryData historyData = Json.loadFromJson(getApplicationContext(), HistoryData.class, "HISTORY,json");
    if(historyData == null){
      historyData = new HistoryData();
    }
    historyData.addHistory(record);
    Json.saveToJson(getApplicationContext(), historyData, "HISTORY,json");
  }

}
