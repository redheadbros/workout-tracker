package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class WorkoutEditor extends AppCompatActivity {

    private Workout workout;
    EditText nameOfWorkout;


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
        }else {
            workout = new Workout();
        }
    }



    public void goback(View v){
        Intent mainScreen = new Intent(WorkoutEditor.this, SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

    public void newCycle(View v){
        Intent newCycle = new Intent(WorkoutEditor.this, CyclesEditor.class);
        newCycle.putExtra("workout",workout);
        newCycle.putExtra("cycleIndex",workout.getCycles().size());
        startActivity(newCycle);
    }

    public void editCycle(View v, int cycleIndex){
        Intent editCycle = new Intent(WorkoutEditor.this, CyclesEditor.class);
        editCycle.putExtra("workout",workout);
        editCycle.putExtra("cycleIndex",cycleIndex);
        startActivity(editCycle);
    }

    public void saveWorkout(View v){
        workout.setName(nameOfWorkout.getText().toString());
        WorkoutList workoutList = Json.loadFromJson(getApplicationContext(), WorkoutList.class, "WORKOUT.json");
        if(workoutList == null){
            workoutList = new WorkoutList();
        }
        workoutList.addWorkout(workout);
        Json.saveToJson(getApplicationContext(), workoutList, "WORKOUT.json");
        Intent mainScreen = new Intent(WorkoutEditor.this,SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

    public void deleteWorkout(View v){
        Intent workoutData = getIntent();
        Bundle extras = workoutData.getExtras();
        if(extras != null){
            int index = (int)workoutData.getSerializableExtra("index");
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
