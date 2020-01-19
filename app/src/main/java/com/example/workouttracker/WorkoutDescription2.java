package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkoutDescription2 extends AppCompatActivity {
  private ImageView imageView;

  private RecyclerView recyclerView;
  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;

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
        Intent gotoWorkout = new Intent(WorkoutDescription2.this, ActiveWorkout.class);
        startActivity(gotoWorkout);
      }
    });

    //prepare the recycler view
    recyclerView = findViewById(R.id.description_recycler_view);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    //setup description
    Intent intent = getIntent();
    //replace this with 'workoutData' when you can actually pass it
    Serializable workoutData = intent.getSerializableExtra("workout");

    //somehow get the workout object itself from whatever workoutData is
    //final Workout workoutData = makeSampleWorkout();

    //edit this to properly put in names and such
    //put somethign here
    adapter = new WorkoutDescriptionAdapter(this, (Workout) workoutData);

    recyclerView.setAdapter(adapter);
  }

}
