package com.example.workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class ActiveWorkoutAdapter extends RecyclerView.Adapter<ActiveWorkoutAdapter.ActiveCycleViewHolder> {

private static final int CYCLE_TITLE_LAYOUT_INDEX = 0;
private static final int EXERCISE_LIST_INDEX = 1;
private static final int CYCLE_TITLE_VIEW_INDEX = 0;
private static final int CYCLE_COUNTER_LAYOUT_INDEX = 1;

  public static class ActiveCycleViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout cycleLayout;
    public LinearLayout cycleTitleLayout;
    public TextView titleView;
    public LinearLayout counterLayout;
    public RecyclerView exerciseView;

    public int cycleIndex;

    public ActiveCycleViewHolder(LinearLayout l) {
      super(l);
      cycleLayout = l;
      cycleTitleLayout = (LinearLayout) cycleLayout.getChildAt(CYCLE_TITLE_LAYOUT_INDEX);
      titleView = (TextView) cycleTitleLayout.getChildAt(CYCLE_TITLE_VIEW_INDEX);
      counterLayout = (LinearLayout) cycleTitleLayout.getChildAt(CYCLE_COUNTER_LAYOUT_INDEX);
      exerciseView = (RecyclerView) cycleLayout.getChildAt(EXERCISE_LIST_INDEX);
    }
  }

  private Context context;
  private Workout workout;
  private ArrayList<Cycle> cycles;
  private WorkoutProgress progress;

  public ActiveWorkoutAdapter(Context appContext, Workout workoutToDo) {
    context = appContext;
    workout = workoutToDo;
    cycles = workoutToDo.getCycles();
    progress = new WorkoutProgress(workoutToDo);
  }

  @Override
  public ActiveCycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.active_workout_cycle_view, parent, false);

    ActiveCycleViewHolder holder = new ActiveCycleViewHolder(linearLayout);
    return holder;
  }

  @Override
  public void onBindViewHolder(ActiveCycleViewHolder holder, final int position) {
    //store position data
    holder.cycleIndex = position;

    //set title of textView
    Cycle currentCycle = cycles.get(position);
    String cycleTitleText = currentCycle.getName() + " (x";
    cycleTitleText += currentCycle.getCycleRepetitions() + ")";
    holder.titleView.setText(cycleTitleText);

    //setup counter
    CustomCounterHelper.setupCycleCounter(holder.counterLayout,workout, progress, position);

    //setup recyclerView
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    holder.exerciseView.setLayoutManager(layoutManager);

    ActiveCycleAdapter adapter = new ActiveCycleAdapter(workout, position, progress);
    holder.exerciseView.setAdapter(adapter);
  }

  @Override
  public void onViewRecycled(ActiveCycleViewHolder holder) {
    TextView counterTextView = (TextView) holder.counterLayout.getChildAt(CustomCounterHelper.COUNTER_TEXT_INDEX);
    int cyclesCompleted = Integer.parseInt((String) counterTextView.getText());
    progress.setCyclesCompleted(holder.cycleIndex, cyclesCompleted);
  }

  @Override
  public int getItemCount() {
    return cycles.size();
  }
}
