package com.example.workouttracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class ActiveCycleAdapter extends RecyclerView.Adapter<ActiveCycleAdapter.ActiveExerciseViewHolder> {

  public static class ActiveExerciseViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public int exerciseIndex;
    public ActiveExerciseViewHolder(LinearLayout l) {
      super(l);
      linearLayout = l;
    }
  }

  private ArrayList<Exercise> exercises;
  private WorkoutProgress progress;
  private int cycleIndex;
  private Workout workout;

  public ActiveCycleAdapter(Workout toTrack, int currentCycleIndex,
                            WorkoutProgress workoutProgress) {
    workout = toTrack;
    cycleIndex = currentCycleIndex;
    exercises = toTrack.getCycles().get(cycleIndex).getExercises();
    progress = workoutProgress;
  }

  @Override
  public ActiveExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.active_cycle_exercise_view, parent, false);

    ActiveExerciseViewHolder holder = new ActiveExerciseViewHolder(linearLayout);
    return holder;
  }

  @Override
  public void onBindViewHolder(ActiveExerciseViewHolder holder, final int position) {
    //store position data
    holder.exerciseIndex = position;

    //find the children
    Exercise currentExercise = exercises.get(position);
    LinearLayout exerciseTitleLayout = (LinearLayout) holder.linearLayout.getChildAt(0);
    TextView exerciseTitle = (TextView) exerciseTitleLayout.getChildAt(0);

    //set exercise title
    exerciseTitle.setText(currentExercise.getName());

    //setup counter
    LinearLayout counterLayout = (LinearLayout) exerciseTitleLayout.getChildAt(1);
    CustomCounterHelper.setupSetCounter(counterLayout, workout, progress, cycleIndex, position);

    //set exercise description
    TextView description = (TextView) holder.linearLayout.getChildAt(1);
    description.setText(currentExercise.getDescription());
  }

  @Override
  public void onViewRecycled(ActiveExerciseViewHolder holder) {
    //TODO

  }

  @Override
  public int getItemCount() {
    return exercises.size();
  }
}
