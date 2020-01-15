package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectWorkout extends AppCompatActivity {
  private ImageView imageView;
  private ImageView iV;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_workout);

    imageView = (ImageView) findViewById(R.id.imageView3);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openhistory();
      }
    });

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    iV = (ImageView) findViewById(R.id.imageView4);
    iV.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkoutEditior = new Intent(SelectWorkout.this, workoutEditor.class);
        startActivity(gotoWorkoutEditior);
      }
    });
  }

  public void openWorkout(View v) {
    Intent gotoWorkout = new Intent(SelectWorkout.this, WorkoutDescription.class);

    startActivity(gotoWorkout);
  }

  public void openhistory(){
    Intent intent = new Intent(SelectWorkout.this, history.class);
    startActivity(intent);
  }
}
