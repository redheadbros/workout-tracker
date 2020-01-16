package com.example.workouttracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;

import java.util.ArrayList;

public class CycleDescriptionAdapter extends RecyclerView.Adapter<CycleDescriptionAdapter.ExerciseViewHolder> {

  private Cycle cycle;
  private ArrayList<Exercise> exercises;

  public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ExerciseViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  public CycleDescriptionAdapter(Cycle toDisplay) {
    cycle = toDisplay;
    exercises = cycle.getExercises();
  }

  @Override
  public CycleDescriptionAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
    TextView v = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.exercise_description_view, parent, false);

    //create an exercise view holder
    CycleDescriptionAdapter.ExerciseViewHolder holder = new ExerciseViewHolder(v);
    return holder;
  }

  @Override
  public void onBindViewHolder(ExerciseViewHolder holder, int position) {
    String text = exercises.get(position).getName();
    holder.textView.setText(text);
  }

  @Override
  public int getItemCount() {
    return exercises.size();
  }
}
