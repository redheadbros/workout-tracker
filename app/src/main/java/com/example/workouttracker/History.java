package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity {
    ListView listView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        listView = (ListView)findViewById(R.id.listview);

        HashMap<String, String> dateWorkout = new HashMap<>();
        dateWorkout.put("10/01/2020", "Leg Day");
        dateWorkout.put("11/01/2019", "Arm Day");
        dateWorkout.put("10/10/2019", "Full Body");
        dateWorkout.put("25/12/2019", "Yoga");
        dateWorkout.put("30/01/2019", "Leg Day");
        dateWorkout.put("17/07/2019", "Arm Day");
        dateWorkout.put("23/08/2019", "Yoga");
        dateWorkout.put("18/12/2019", "Full Body");
        dateWorkout.put("02/12/2019", "Leg Day");
        dateWorkout.put("04/01/2020", "Arm Day");
        dateWorkout.put("03/03/2019", "Yoga");
        dateWorkout.put("04/04/2019", "Leg Day");






        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_view,
                new String[] {"First Line", "Second Line"},
                new int[]{R.id.textview1, R.id.textview2});
        Iterator It = dateWorkout.entrySet().iterator();
        while (It.hasNext()){
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) It.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(History.this, WorkoutDescription.class);
                    startActivity(intent);
                }
            });
        }

        listView.setAdapter(adapter);

    }

}
