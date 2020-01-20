package com.example.workouttracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

/**
 * ActiveCycleAdapter: lays out the elements of one cycle of "Active Workout"
 *
 * ActiveExerciseViewHolder (class): holds the layout of one exercise, and provides
 *   references to different parts of said layout.
 *
 * onCreateViewHolder(ViewGroup, int) -> ActiveExerciseViewHolder:
 *   creates a new custom view holder from its layout file.
 *
 * onBindViewHolder(ActiveExerciseViewHolder, int):
 *   applies the correct details to the layout, and sets up the set counter.
 *
 * onViewRecycled(ActiveExerciseViewHolder):
 *   reads the current counter value and stores it until this view returns.
 *
 * getItemCount() -> int:
 *   returns the number of exercises in this cycle.
 */
public class ActiveCycleAdapter extends RecyclerView.Adapter<ActiveCycleAdapter.ActiveExerciseViewHolder> {

  private static final int EXERCISE_TITLE_LAYOUT_INDEX = 0;
  private static final int EXERCISE_DESCRIPTION_VIEW_INDEX = 1;
  private static final int EXERCISE_TITLE_VIEW_INDEX = 0;
  private static final int EXERCISE_COUNTER_LAYOUT = 1;

  /**
   * ActiveExerciseViewHolder (class): holds the layout of one exercise, and provides
   *   references to different parts of said layout.
   */
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
      titleLayout = (LinearLayout) exerciseLayout.getChildAt(EXERCISE_TITLE_LAYOUT_INDEX);
      titleView = (TextView) titleLayout.getChildAt(EXERCISE_TITLE_VIEW_INDEX);
      counterLayout = (LinearLayout) titleLayout.getChildAt(EXERCISE_COUNTER_LAYOUT);
      descriptionView = (TextView) exerciseLayout.getChildAt(EXERCISE_DESCRIPTION_VIEW_INDEX);
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

  /**
   * onCreateViewHolder(ViewGroup, int) -> ActiveExerciseViewHolder:
   *   creates a new custom view holder from its layout file.
   * @param parent: the view group this will be a part of.
   * @param viewType: the view type (unused)
   * @return: a new view holder.
   */
  @Override
  public ActiveExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.active_cycle_exercise_view, parent, false);

    ActiveExerciseViewHolder holder = new ActiveExerciseViewHolder(linearLayout);
    return holder;
  }

  /**
   * onBindViewHolder(ActiveExerciseViewHolder, int):
   *   applies the correct details to the layout, and sets up the set counter.
   * @param holder: the view holder to be set up.
   * @param position: index of the exercise to be displayed.
   */
  @Override
  public void onBindViewHolder(ActiveExerciseViewHolder holder, final int position) {
    //store position data
    holder.exerciseIndex = position;

    //set exercise details
    Exercise currentExercise = exercises.get(position);
    String exerciseTitle = currentExercise.getName() + " (x";
    exerciseTitle += currentExercise.getSets() + ")";
    holder.titleView.setText("   " +exerciseTitle);
    holder.descriptionView.setText("      " + currentExercise.getDescription());

    //setup counter
    CustomCounterHelper.setupSetCounter(holder.counterLayout, workout, progress, cycleIndex, position);
  }

  /**
   * onViewRecycled(ActiveExerciseViewHolder):
   *   reads the current counter value and stores it until this view returns.
   * @param holder: the holder for the to-be-recycled view.
   */
  @Override
  public void onViewRecycled(ActiveExerciseViewHolder holder) {
    TextView counterTextView = (TextView) holder.counterLayout.getChildAt(CustomCounterHelper.COUNTER_TEXT_INDEX);
    int setsCompleted = Integer.parseInt((String) counterTextView.getText());
    progress.setSetsCompleted(cycleIndex, holder.exerciseIndex, setsCompleted);
  }

  /**
   * getItemCount() -> int:
   *   returns the number of exercises in this cycle.
   * @return: the number of exercises.
   */
  @Override
  public int getItemCount() {
    return exercises.size();
  }
}
