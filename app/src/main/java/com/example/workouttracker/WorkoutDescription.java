package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class WorkoutDescription extends AppCompatActivity {
  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workout_description);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    imageView = (ImageView)findViewById(R.id.imageView2);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkout = new Intent(WorkoutDescription.this, ActiveWorkout.class);
        startActivity(gotoWorkout);
      }
    });

    ImageView imageV = findViewById(R.id.imageView5);
    imageV.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent gotoWorkoutEditior = new Intent(WorkoutDescription.this, workoutEditor.class);
        startActivity(gotoWorkoutEditior);
      }
    });
  }

}
