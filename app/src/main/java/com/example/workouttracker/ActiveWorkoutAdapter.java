package com.example.workouttracker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class ActiveWorkoutAdapter extends RecyclerView.Adapter<ActiveWorkoutAdapter.ActiveWorkoutViewHolder> {

  public static class ActiveWorkoutViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public ActiveWorkoutViewHolder(View v) {
      super(v);
      view = v;
    }
  }

  private Context context;
  private Workout workout;
  private ArrayList<Cycle> cycles;

  public ActiveWorkoutAdapter(Context appContext, Workout workoutToDo) {
    context = appContext;
    workout = workoutToDo;
    cycles = workoutToDo.getCycles();
  }

  @Override
  public ActiveWorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

  }

  @Override
  public void onBindViewHolder(ActiveWorkoutViewHolder holder, final int position) {

  }

  public int getItemCount() {
    return cycles.size();
  }
}
