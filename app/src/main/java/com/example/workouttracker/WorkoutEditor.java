package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class WorkoutEditor extends AppCompatActivity {

    private Workout workout;
    EditText nameOfWorkout;
    String defaultName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_editor);
        nameOfWorkout = findViewById(R.id.NameOfWorkout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent workoutData = getIntent();
        Bundle extras = workoutData.getExtras();
        if(extras != null){
            workout = (Workout)workoutData.getSerializableExtra("workout");
            defaultName = workout.getName();
        }else {
            workout = new Workout();
            WorkoutList workoutList = Json.loadFromJson(getApplicationContext(), WorkoutList.class, "WORKOUT.json");
            if(workoutList == null){
                workoutList = new WorkoutList();
            }
            ArrayList<Workout> workoutArray = workoutList.getWorkoutList();
            defaultName = "Workout" + String.valueOf(workoutArray.size() + 1);
        }
        RecyclerView recyclerView = findViewById(R.id.allCycles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        WorkoutEditorAdapter adapter = new WorkoutEditorAdapter(this, workout);
        recyclerView.setAdapter(adapter);
        nameOfWorkout.setText(workout.getName());
    }



    public void goback(View v){
        Intent mainScreen = new Intent(WorkoutEditor.this, SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

    public void newCycle(View v){
        Intent newCycle = new Intent(WorkoutEditor.this, CyclesEditor.class);
        workout.setName(nameOfWorkout.getText().toString());
        newCycle.putExtra("workout",workout);
        newCycle.putExtra("cycleIndex",workout.getCycles().size());
        startActivity(newCycle);
    }

    public void saveWorkout(View v){
        workout.setName(nameOfWorkout.getText().toString());
        WorkoutList workoutList = Json.loadFromJson(getApplicationContext(), WorkoutList.class, "WORKOUT.json");
        if(workoutList == null){
            workoutList = new WorkoutList();
        }
        if(workout.getIndex() == -10){
            workoutList.addWorkout(workout);
        }else{
           ArrayList<Workout> ar= workoutList.getWorkoutList();
           ar.set(workout.getIndex(),workout);
           workoutList.setWorkoutList(ar);
        }
        Json.saveToJson(getApplicationContext(), workoutList, "WORKOUT.json");
        Intent mainScreen = new Intent(WorkoutEditor.this,SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

    public void deleteWorkout(View v){
        Intent workoutData = getIntent();
        Bundle extras = workoutData.getExtras();
        if(extras != null){
            int index = workout.getIndex();
            WorkoutList workoutList = Json.loadFromJson(getApplicationContext(), WorkoutList.class, "WORKOUT.json");
            ArrayList<Workout> workoutArray = workoutList.getWorkoutList();
            workoutArray.remove(index);
            workoutList.setWorkoutList(workoutArray);
            Json.saveToJson(getApplicationContext(), workoutList, "WORKOUT.json");
        }
        Intent mainScreen = new Intent(WorkoutEditor.this,SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

}
