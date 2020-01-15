package com.example.workouttracker;

import android.content.Intent;
import android.os.Bundle;

import com.example.workouttracker.datastructure.Workout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class workoutEditor extends AppCompatActivity {

    private Workout workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_editor);
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
        Intent mainScreen = new Intent(workoutEditor.this, SelectWorkout.class);
        startActivity(mainScreen);
        finish();
    }

    public void newCycle(View v){
        Intent newCycle = new Intent(workoutEditor.this, cyclesEditor.class);
        newCycle.putExtra("workout",workout);
        newCycle.putExtra("cycleIndex",workout.getCycles().size());
        startActivity(newCycle);
    }

    public void editCycle(View v, int cycleIndex){
        Intent editCycle = new Intent(workoutEditor.this, cyclesEditor.class);
        editCycle.putExtra("workout",workout);
        editCycle.putExtra("cycleIndex",cycleIndex);
        startActivity(editCycle);
    }

}
