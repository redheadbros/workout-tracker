package com.example.workouttracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import androidx.appcompat.app.AlertDialog;
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
    private Boolean editingMode = false;
    private TextView setsNum;
    private EditText exerciseName;
    private EditText exerciseDes;
    private Exercise exercise;
    private String defaultName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent cycleEditorData = getIntent();
        workout = (Workout)(cycleEditorData.getSerializableExtra("workout"));
        exerciseIndex = (int)cycleEditorData.getSerializableExtra("exerciseIndex");
        cycleIndex = (int)cycleEditorData.getSerializableExtra("cycleIndex");
        cycle = workout.getCycles().get(cycleIndex);
        exerciseList = cycle.getExercises();
        setContentView(R.layout.activity_exercise_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setsNum = findViewById(R.id.numOfSets);
        exerciseName = findViewById(R.id.getExceriseName);
        exerciseDes = findViewById(R.id.getDescription);
        if(exerciseIndex == exerciseList.size()){
            exercise = new Exercise();
            defaultName = "Exercise " + (exerciseIndex + 1);
        }else{
            editingMode = true;
            exercise = exerciseList.get(exerciseIndex);
            defaultName = exercise.getName();
        }
        numberOfSets = exercise.getSets();
        setsNum.setText(String.valueOf(numberOfSets));
        exerciseName.setText(defaultName);
        if(editingMode){
            exerciseDes.setText(exercise.getDescription());
        }
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

    public void onClickDeleteButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this exercise?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteExercise();

                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void deleteExercise(){
        if(editingMode){
            exerciseList.remove(exerciseIndex);
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

    public void goBack(View v){
        Intent cycleEditor = new Intent(ExerciseEditor.this, CyclesEditor.class);
        cycleEditor.putExtra("workout",workout);
        cycleEditor.putExtra("cycleIndex",cycleIndex);
        startActivity(cycleEditor);
        finish();
    }

    @Override
    public void onBackPressed() { }
}
