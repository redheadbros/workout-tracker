package com.example.workouttracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.cycle_description_view, parent, false);

    CycleViewHolder holder = new CycleViewHolder(v);
    return holder;
  }

  @Override
  public void onBindViewHolder(CycleViewHolder holder, int position) {
    String text = cycles.get(position).getName();
    TextView textView = (TextView) holder.linearLayout.getChildAt(0);
    textView.setText(text);
  }

  @Override
  public int getItemCount() {
    return workout.getCycles().size();
  }
}
