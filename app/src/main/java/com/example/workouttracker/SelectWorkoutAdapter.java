package com.example.workouttracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Workout;
import com.example.workouttracker.datastructure.WorkoutList;

import java.util.ArrayList;

public class SelectWorkoutAdapter extends RecyclerView.Adapter<SelectWorkoutAdapter.WorkoutButtonViewHolder> {

  private ArrayList<Workout> workouts;
  private Context context;

  public static class WorkoutButtonViewHolder extends RecyclerView.ViewHolder {
    public Button button;
    public WorkoutButtonViewHolder(Button b) {
      super(b);
      button = b;
    }
  }

  public SelectWorkoutAdapter(Context activityContext, WorkoutList workoutList) {
    workouts = workoutList.getWorkoutList();
    context = activityContext;
  }

  public SelectWorkoutAdapter.WorkoutButtonViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
    Button button = (Button) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.select_workout_button, parent, false);

    WorkoutButtonViewHolder holder = new WorkoutButtonViewHolder(button);

    return holder;
  }

  public void onBindViewHolder(WorkoutButtonViewHolder holder, final int position) {

    holder.button.setText(workouts.get(position).getName());

    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent gotoWorkout = new Intent(context, WorkoutDescription.class);

        gotoWorkout.putExtra("workout", workouts.get(position));
        context.startActivity(gotoWorkout);
      }
    });
  }

  public int getItemCount() {
    return workouts.size();
  }
}
