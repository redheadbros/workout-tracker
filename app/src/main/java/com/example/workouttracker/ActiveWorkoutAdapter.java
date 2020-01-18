package com.example.workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class ActiveWorkoutAdapter extends RecyclerView.Adapter<ActiveWorkoutAdapter.ActiveWorkoutViewHolder> {

  public static class ActiveWorkoutViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout view;
    public ActiveWorkoutViewHolder(LinearLayout v) {
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

    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.active_workout_cycle_view, parent, false);

    ActiveWorkoutViewHolder holder = new ActiveWorkoutViewHolder(linearLayout);
    return holder;
  }

  @Override
  public void onBindViewHolder(ActiveWorkoutViewHolder holder, final int position) {

  }

  public int getItemCount() {
    return cycles.size();
  }
}
