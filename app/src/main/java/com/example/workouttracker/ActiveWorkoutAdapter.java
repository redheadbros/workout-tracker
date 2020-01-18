package com.example.workouttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class ActiveWorkoutAdapter extends RecyclerView.Adapter<ActiveWorkoutAdapter.ActiveWorkoutViewHolder> {

  public static class ActiveWorkoutViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public ActiveWorkoutViewHolder(LinearLayout l) {
      super(l);
      linearLayout = l;
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
  public ActiveWorkoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.active_workout_cycle_view, parent, false);

    ActiveWorkoutViewHolder holder = new ActiveWorkoutViewHolder(linearLayout);
    return holder;
  }

  @Override
  public void onBindViewHolder(ActiveWorkoutViewHolder holder, final int position) {
    //set title of textview
    LinearLayout cycleTitleLayout = (LinearLayout) holder.linearLayout.getChildAt(0);
    TextView cycleTitleTextView = (TextView) cycleTitleLayout.getChildAt(0);

    //add on the number of repetitions, i.e. Cycle Name (x3)
    Cycle currentCycle = cycles.get(position);
    String cycleTitleText = currentCycle.getName() + " (x";
    cycleTitleText += currentCycle.getCycleRepetitions() + ")";

    cycleTitleTextView.setText(cycleTitleText);

    //set counter correctly
    LinearLayout cycleTitleCounterLayout = (LinearLayout) cycleTitleLayout.getChildAt(1);
    TextView cycleTitleCounterTextView = (TextView) cycleTitleCounterLayout.getChildAt(1);
    cycleTitleCounterTextView.setText(String.valueOf(progress.getCyclesCompleted(position)));

    //setup counter buttons
    Button minus = (Button) cycleTitleCounterLayout.getChildAt(0);
    Button plus = (Button) cycleTitleCounterLayout.getChildAt(2);

    minus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //change progress
        progress.setCyclesCompleted(position, progress.getCyclesCompleted(position) - 1);

        //update text view
        LinearLayout cycleCounterLayout = (LinearLayout) v.getParent();
        TextView cycleCounterTextView = (TextView) cycleCounterLayout.getChildAt(1);
        cycleCounterTextView.setText(String.valueOf(progress.getCyclesCompleted(position)));
      }
    });

    plus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //change progress
        progress.setCyclesCompleted(position, progress.getCyclesCompleted(position) + 1);

        //update text view
        LinearLayout cycleCounterLayout = (LinearLayout) v.getParent();
        TextView cycleCounterTextView = (TextView) cycleCounterLayout.getChildAt(1);
        cycleCounterTextView.setText(String.valueOf(progress.getCyclesCompleted(position)));
      }
    });

    //setup recyclerView
    RecyclerView exerciseView = (RecyclerView) holder.linearLayout.getChildAt(1);
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    exerciseView.setLayoutManager(layoutManager);

    ActiveCycleAdapter adapter = new ActiveCycleAdapter(context, cycles.get(position));
    exerciseView.setAdapter(adapter);
  }

  public int getItemCount() {
    return cycles.size();
  }
}
