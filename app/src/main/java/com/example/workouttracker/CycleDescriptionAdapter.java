package com.example.workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;

import java.util.ArrayList;

public class CycleDescriptionAdapter extends ArrayAdapter<Exercise> {

  private ArrayList<Exercise> exercises;

  public CycleDescriptionAdapter(Context context, int resource, ArrayList<Exercise> exerciseList) {
    super(context, resource, exerciseList);
    exercises = exerciseList;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup container) {

  }
}
