package com.example.workouttracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;


public class WorkoutDescriptionAdapter extends RecyclerView.Adapter<WorkoutDescriptionAdapter.CycleViewHolder> {

  private Workout workout;
  private ArrayList<Cycle> cycles;

  //contains class for holding views for part of the description
  public static class CycleViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;

    public CycleViewHolder(LinearLayout v) {
      super(v);
      linearLayout = v;
    }
  }

  //constructor, here.
  public WorkoutDescriptionAdapter(Workout toDisplay) {
    workout = toDisplay;
    cycles = workout.getCycles();
  }

  @Override
  public WorkoutDescriptionAdapter.CycleViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
    //create TextView (or other view) for CycleViewHolder
    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.cycle_description_view, parent, false);

    CycleViewHolder holder = new CycleViewHolder(linearLayout);
    return holder;
  }

  @Override
  public void onBindViewHolder(CycleViewHolder holder, int position) {

    //setup cycle title
    Cycle cycle = cycles.get(position)
    String text = cycle.getName();
    TextView textView = (TextView) holder.linearLayout.getChildAt(0);
    textView.setText(text);

    //setup exercise list
    //TODO: figure out how to get a proper context what the heck is this
    RecyclerView recyclerView = (RecyclerView) holder.linearLayout.getChildAt(1);
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);

    CycleDescriptionAdapter adapter = new CycleDescriptionAdapter(cycle);
    recyclerView.setAdapter(adapter);
  }

  @Override
  public int getItemCount() {
    return cycles.size();
  }
}
