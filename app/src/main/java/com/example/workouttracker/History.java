package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.workouttracker.datastructure.HistoryData;
import com.example.workouttracker.datastructure.Json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History extends AppCompatActivity {
    ListView listView;
    ImageView imageView;
    ArrayList<String> dateList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ActiveWorkout activeWorkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateAllDate();

        setContentView(R.layout.activity_history);
        activeWorkout = new ActiveWorkout();

        dateList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dateList);









        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectWorkout();

            }
        });

        listView = (ListView)findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openWorkoutDescription();

            }
        });

        listView.setAdapter(adapter);







    }
    public void openSelectWorkout() {
        Intent intent = new Intent(this, SelectWorkout.class);
        startActivity(intent);
    }

    public void generateAllDate(){
        HistoryData historyData = Json.loadFromJson(getApplicationContext(), HistoryData.class,"HISTORY,json");
        if(historyData == null){
            return;
        }
        int index = 0;
        while(index < historyData.getHistoryList().size()){
            Date date = historyData.getDate(index);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            dateList.add(strDate);
        }
    }

    public void openWorkoutDescription(){
        HistoryData historyData = Json.loadFromJson(getApplicationContext(), HistoryData.class, "HISTORY,json");
        if (historyData == null){
            return;

        }
        ;

    }
    public void getWorkout(){

    }

    public void clearHistory(){
        HistoryData nothing = new HistoryData();
        Json.saveToJson(getApplicationContext(),nothing, "HISTORY.json");
    }



}
