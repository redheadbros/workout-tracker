package com.example.workouttracker;

import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;

import java.util.ArrayList;

public class SelectWorkoutAdapter extends RecyclerView.Adapter<SelectWorkoutAdapter.WorkoutButtonViewHolder> {

  private ArrayList<Workout> workouts;

  public static class WorkoutButtonViewHolder extends RecyclerView.ViewHolder {
    public Button button;
    public WorkoutButtonViewHolder(Button b) {
      super(b);
      button = b;
    }
  }

  public SelectWorkoutAdapter(WorkoutList workoutList) {
    workouts = workoutList.getWorkoutList();
  }

  public SelectWorkoutAdapter.WorkoutButtonViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {

  }

  public void onBindViewHolder(WorkoutButtonViewHolder holder, int position) {

  }

  public int getItemCount() {
    return workouts.size();
  }
}
