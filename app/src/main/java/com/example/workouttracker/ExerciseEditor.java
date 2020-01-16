package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseEditor extends AppCompatActivity{

    private int numberOfSets;
    private int exerciseIndex;
    private int cycleIndex;
    private ArrayList<Exercise> exerciseList;
    private Cycle cycle;
    private Workout workout;
    TextView setsNum;
    EditText exerciseName;
    EditText exerciseDes;
    Exercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent cycleEditorData = getIntent();
        workout = (Workout)(cycleEditorData.getSerializableExtra("workout"));
        exerciseIndex = (int)cycleEditorData.getSerializableExtra("exerciseIndex");
        cycleIndex = (int)cycleEditorData.getSerializableExtra("cycleIndex");
        cycle = workout.getCycles().get(cycleIndex);
        exerciseList = cycle.getExercises();
        setContentView(R.layout.activity_exercise_editior);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setsNum = findViewById(R.id.numOfSets);
        exerciseName = findViewById(R.id.getExceriseName);
        exerciseDes = findViewById(R.id.getDescription);
        if(exerciseIndex == exerciseList.size()){
            exercise = new Exercise();
        }else{
            exercise = exerciseList.get(exerciseIndex);
        }
        numberOfSets = exercise.getSets();
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void saveExercise(View v){
        exercise.setName(exerciseName.getText().toString());
        exercise.setDescription((exerciseDes.getText().toString()));
        exercise.setSets(numberOfSets);
        if(exerciseIndex == exerciseList.size()){
            exerciseList.add(exercise);
        }else{
            exerciseList.set(exerciseIndex,exercise);
        }
        cycle.setExercises(exerciseList);
        ArrayList<Cycle> cycleList = workout.getCycles();
        cycleList.set(cycleIndex,cycle);
        workout.setCycles(cycleList);
        Intent cycleEditor = new Intent(ExerciseEditor.this, CyclesEditor.class);
        cycleEditor.putExtra("workout",workout);
        cycleEditor.putExtra("cycleIndex",cycleIndex);
        startActivity(cycleEditor);
        finish();
    }


    public void addNumber(View v){
        numberOfSets +=1;
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void subtractNumber(View v){
        if(numberOfSets > 1){
            numberOfSets -=1;
        }
        setsNum.setText(String.valueOf(numberOfSets));
    }

    public void goback(View v){
        finish();
    }
}
