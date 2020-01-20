package com.example.workouttracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.workouttracker.datastructure.HistoryData;
import com.example.workouttracker.datastructure.Json;
import com.example.workouttracker.datastructure.Workout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History extends AppCompatActivity {
    private ListView listView;
    private ImageView imageView;
    private ArrayList<String> dateList;
    private ArrayList<Workout> workoutList;
    private ArrayAdapter<String> adapter;
    private ImageView clearHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //get data from history file, fill date and workout lists

        dateList = getDates();
        workoutList = getWorkouts();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dateList);



        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //goto appropriate workout description
                Intent gotoWorkoutDescription = new Intent(History.this,
                    WorkoutDescription.class);
                gotoWorkoutDescription.putExtra("workout", workoutList.get(position));
                gotoWorkoutDescription.putExtra("history",true);
                startActivity(gotoWorkoutDescription);
            }
        });

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        clearHistory = findViewById(R.id.imageView7);
        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                builder.setMessage("Are you sure you want to clear your records?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clearHistory();

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

    private ArrayList<String> getDates(){
        HistoryData historyData = Json.loadFromJson(getApplicationContext(), HistoryData.class,"HISTORY.json");
        if(historyData == null){
            return new ArrayList<>();
        }

        //setup dateList
        ArrayList<String> newDateList = new ArrayList<>();
        int index = 0;
        while(index < historyData.getHistoryList().size()){
            Date date = historyData.getDate(index);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = dateFormat.format(date);
            newDateList.add(strDate);
            index++;
        }

        return newDateList;
    }

    private ArrayList<Workout> getWorkouts(){
        HistoryData historyData = Json.loadFromJson(getApplicationContext(),HistoryData.class,"HISTORY.json");
        if(historyData == null){
            return new ArrayList<>();
        }

        //setup workout list
        ArrayList<Workout> newWorkoutList = new ArrayList<>();
        int index = 0;
        while(index < historyData.getHistoryList().size()){
            Workout workout = historyData.getWorkout(index);
            newWorkoutList.add(workout);
            index++;
        }

        return newWorkoutList;
    }



    public void clearHistory(){
        HistoryData nothing = new HistoryData();
        Json.saveToJson(getApplicationContext(),nothing, "HISTORY.json");
    }


}
