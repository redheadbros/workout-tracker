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
      cycleTitleLayout = (LinearLayout) cycleLayout.getChildAt(0);
      titleView = (TextView) cycleTitleLayout.getChildAt(0);
      counterLayout = (LinearLayout) cycleTitleLayout.getChildAt(1);
      exerciseView = (RecyclerView) cycleLayout.getChildAt(1);
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
    //  add on the number of repetitions, i.e. Cycle Name (x3)
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
    TextView counterTextView = (TextView) holder.counterLayout.getChildAt(1);
    int cyclesCompleted = Integer.parseInt((String) counterTextView.getText());
    progress.setCyclesCompleted(holder.cycleIndex, cyclesCompleted);
  }

  @Override
  public int getItemCount() {
    return cycles.size();
  }
}
