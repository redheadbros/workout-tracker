package com.example.workouttracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class CyclesEditor extends AppCompatActivity {

    private Cycle cycle = new Cycle();
    private Workout workout;
    private Workout originWorkout;
    private int cycleIndex;
    private int numberOfCycle = 1;
    private TextView cycleNum;
    private EditText cycleName;
    private String defaultName;
    private RecyclerView allExercises;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent dataReceiver = getIntent();
        workout = (Workout)(dataReceiver.getSerializableExtra("workout"));
        cycleIndex = (int)(dataReceiver.getSerializableExtra("cycleIndex"));
        originWorkout = workout;
        setContentView(R.layout.activity_cycles_editor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cycleNum = findViewById(R.id.numOfCycles);
        cycleName = findViewById(R.id.NameOfCycle);
        allExercises = findViewById(R.id.AllExercises);
        if(cycleIndex == workout.getCycles().size()){
            ArrayList<Cycle> cycles = workout.getCycles();
            cycles.add(cycle);
            workout.setCycles(cycles);
            defaultName = "Cycle " + (cycleIndex + 1);
        }else {
            cycle = workout.getCycles().get(cycleIndex);
            defaultName = cycle.getName();
        }
        numberOfCycle = cycle.getCycleRepetitions();
        System.out.println(numberOfCycle);
        cycleNum.setText(String.valueOf(numberOfCycle));
        cycleName.setText(defaultName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        allExercises.setLayoutManager(layoutManager);
        CycleEditorAdapter adapter = new CycleEditorAdapter(this, workout,cycleIndex);
        allExercises.setAdapter(adapter);
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
        cycle.setCycleRepetitions(numberOfCycle);
    }

    public void subtractNumber(View v){
        if(numberOfCycle > 1){
            numberOfCycle -=1;
        }
        cycleNum.setText(String.valueOf(numberOfCycle));
        cycle.setCycleRepetitions(numberOfCycle);
    }

    private void saveName(){
        ArrayList<Cycle> cycleList = workout.getCycles();
        cycle.setName(cycleName.getText().toString());
        cycleList.set(cycleIndex,cycle);
        workout.setCycles(cycleList);
    }

    public void onClickDeleteButton(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this cycle?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteCycle();

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

    public void deleteCycle(){
        ArrayList<Cycle> cycleList = workout.getCycles();
        cycleList.remove(cycleIndex);
        workout.setCycles(cycleList);
        Intent workoutPage = new Intent(CyclesEditor.this, WorkoutEditor.class);
        workoutPage.putExtra("workout",workout);
        startActivity(workoutPage);
        finish();
    }

    @Override
    public void onBackPressed() { }
}