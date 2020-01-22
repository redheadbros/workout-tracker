package com.example.workouttracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class WorkoutEditor extends AppCompatActivity {

    private Workout workout;
    private EditText nameOfWorkout;
    private String defaultName;
    private boolean toBeSaved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_editor);
        nameOfWorkout = findViewById(R.id.NameOfWorkout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toBeSaved = true;
        Intent workoutData = getIntent();
        Bundle extras = workoutData.getExtras();
        if(extras != null){
            workout = (Workout)workoutData.getSerializableExtra("workout");
            try {
                defaultName = workout.getName();
            }catch(NullPointerException e){
                System.out.println("This is an exception that possibly but never showed in any test");
                System.out.println("Android studio just kept warning me about it");
                throw e;
            }
        }else {
            workout = new Workout();
            WorkoutList workoutList = Json.loadFromJson(getApplicationContext(), WorkoutList.class, "WORKOUT.json");
            if(workoutList == null){
                workoutList = new WorkoutList();
            }
            ArrayList<Workout> workoutArray = workoutList.getWorkoutList();
            defaultName = "Workout " + (workoutArray.size() + 1);
        }
        RecyclerView recyclerView = findViewById(R.id.allCycles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        WorkoutEditorAdapter adapter = new WorkoutEditorAdapter(this, workout);
        recyclerView.setAdapter(adapter);
        nameOfWorkout.setText(defaultName);

        ImageView delete = findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WorkoutEditor.this);
                builder.setMessage("Are you sure you want to delete this workout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteWorkoutAndQuit();

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
        });
    }



    public void goBack(View v){
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

    public void saveWorkoutAndQuit(){
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
    }

    public void deleteWorkoutAndQuit(){
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

        toBeSaved = false;

        Intent mainScreen = new Intent(WorkoutEditor.this,SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toBeSaved) {
            saveWorkoutAndQuit();
        }
    }
}
