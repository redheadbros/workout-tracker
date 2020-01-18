package com.example.workouttracker;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;

import java.util.ArrayList;

public class ActiveCycleAdapter extends RecyclerView.Adapter<ActiveCycleAdapter.ActiveExerciseViewHolder> {

  public static class ActiveExerciseViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public ActiveExerciseViewHolder(LinearLayout l) {
      super(l);
      linearLayout = l;
    }
  }

  private Context context;
  private ArrayList<Exercise> exercises;

  public ActiveCycleAdapter(Context appContext, Cycle toTrack) {
    exercises = toTrack.getExercises();
  }

  @Override
  public ActiveExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

  }

  @Override
  public void onBindViewHolder(ActiveExerciseViewHolder holder, final int position) {

  }

  @Override
  public int getItemCount() {
    return exercises.size();
  }
}
