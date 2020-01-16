package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class CyclesEditor extends AppCompatActivity {

    Cycle cycle = new Cycle();
    Workout workout;
    Workout originWorkout;
    private int cycleIndex;
    private int numberOfCycle = 1;
    TextView cycleNum;
    EditText cycleName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent dataReceiver = getIntent();
        workout = (Workout)(dataReceiver.getSerializableExtra("workout"));
        cycleIndex = (int)(dataReceiver.getSerializableExtra("cycleIndex"));
        originWorkout = workout;
        if(cycleIndex == workout.getCycles().size()){
            originWorkout = workout;
            ArrayList<Cycle> cycles = workout.getCycles();
            cycles.add(cycle);
            workout.setCycles(cycles);
        }else {
            cycle = workout.getCycles().get(cycleIndex);
        }
        setContentView(R.layout.activity_cycles_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cycleNum = findViewById(R.id.numOfCycles);
        cycleName = findViewById(R.id.NameOfCycle);
        numberOfCycle = cycle.getCycleReptations();
        cycleNum.setText(String.valueOf(numberOfCycle));
    }

    public void EditExercise(View v, int exerciseIndex){
        Intent editExercise = new Intent(CyclesEditor.this, ExerciseEditor.class);
        editExercise.putExtra("exerciseIndex",exerciseIndex);
        editExercise.putExtra("workout",workout);
        editExercise.putExtra("cycleIndex",cycleIndex);
        startActivity(editExercise);
        finish();
    }

    public void newExercise(View v){
        saveName();
        Intent newExercise = new Intent(CyclesEditor.this, ExerciseEditor.class);
        newExercise.putExtra("exerciseIndex",cycle.getExercises().size());
        newExercise.putExtra("workout",workout);
        newExercise.putExtra("cycleIndex",cycleIndex);
        startActivity(newExercise);
        finish();
    }

    public void backToWorkout(View v){
        Intent workoutPage = new Intent(CyclesEditor.this, WorkoutEditor.class);
        workoutPage.putExtra("workout",originWorkout);
        startActivity(workoutPage);
        finish();
    }

    public void saveCycle(View v){
        saveName();
        Intent workoutPage = new Intent(CyclesEditor.this, WorkoutEditor.class);
        workoutPage.putExtra("workout",workout);
        startActivity(workoutPage);
        finish();
    }

    public void addNumber(View v){
        numberOfCycle +=1;
        cycleNum.setText(String.valueOf(numberOfCycle));
    }

    public void subtractNumber(View v){
        if(numberOfCycle > 1){
            numberOfCycle -=1;
        }
        cycleNum.setText(String.valueOf(numberOfCycle));
    }

    private void saveName(){
        ArrayList<Cycle> cycleList = workout.getCycles();
        cycle.setName(cycleName.getText().toString());
        cycleList.set(cycleIndex,cycle);
        workout.setCycles(cycleList);
    }


}
