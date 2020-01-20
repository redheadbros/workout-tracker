package com.example.workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;

import java.util.ArrayList;

public class CycleDescriptionAdapter extends ArrayAdapter<Exercise> {

  private ArrayList<Exercise> exercises;
  private int layoutResource;
  private Context context;
  private final int EXERCISE_TITLE_INDEX = 0;
  private final int EXERCISE_DESCRIPTION_INDEX = 1;

  public CycleDescriptionAdapter(Context activityContext, int resource, ArrayList<Exercise> exerciseList) {
    super(activityContext, resource, exerciseList);
    context = activityContext;
    exercises = exerciseList;
    layoutResource = resource;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup container) {
    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context)
        .inflate(layoutResource, container, false);

    //get text to display
    Exercise exerciseToShow = exercises.get(position);
    String exerciseTitle = exerciseToShow.getName() + " (x";
    exerciseTitle += exerciseToShow.getSets() + ")";
    String exerciseDescription = exerciseToShow.getDescription();

    //insert into View
    TextView titleView = (TextView) linearLayout.getChildAt(EXERCISE_TITLE_INDEX);
    TextView descriptionView = (TextView) linearLayout.getChildAt(EXERCISE_DESCRIPTION_INDEX);
    titleView.setText("  " + exerciseTitle);
    descriptionView.setText("    " + exerciseDescription);

    return linearLayout;
  }

  @Override
  public int getCount() {
    return exercises.size();
  }
}
