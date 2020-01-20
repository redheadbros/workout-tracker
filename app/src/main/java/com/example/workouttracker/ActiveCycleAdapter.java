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
    public LinearLayout exerciseLayout;
    public LinearLayout titleLayout;
    public TextView titleView;
    public LinearLayout counterLayout;
    public TextView descriptionView;

    public int exerciseIndex;

    public ActiveExerciseViewHolder(LinearLayout l) {
      super(l);
      exerciseLayout = l;
      titleLayout = (LinearLayout) exerciseLayout.getChildAt(0);
      titleView = (TextView) titleLayout.getChildAt(0);
      counterLayout = (LinearLayout) titleLayout.getChildAt(1);
      descriptionView = (TextView) exerciseLayout.getChildAt(1);
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

    //set exercise details
    Exercise currentExercise = exercises.get(position);
    String exerciseTitle = currentExercise.getName() + " (";
    exerciseTitle += currentExercise.getSets() + ")";
    holder.titleView.setText("   " +exerciseTitle);
    holder.descriptionView.setText("      " + currentExercise.getDescription());

    //setup counter
    CustomCounterHelper.setupSetCounter(holder.counterLayout, workout, progress, cycleIndex, position);
  }

  @Override
  public void onViewRecycled(ActiveExerciseViewHolder holder) {
    TextView counterTextView = (TextView) holder.counterLayout.getChildAt(1);
    int setsCompleted = Integer.parseInt((String) counterTextView.getText());
    progress.setSetsCompleted(cycleIndex, holder.exerciseIndex, setsCompleted);
  }

  @Override
  public int getItemCount() {
    return exercises.size();
  }
}
