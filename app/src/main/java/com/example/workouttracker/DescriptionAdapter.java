package com.example.workouttracker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;


public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.CycleViewHolder> {

  //contains class for holding views for part of the description
  public static class CycleViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public CycleViewHolder(TextView v) {
      super(v);
      textView = v;
    }
  }

  private Workout workout;

  //constructor, here.
  public DescriptionAdapter(Workout toDisplay) {
    workout = toDisplay;
  }

  @Override
  public DescriptionAdapter.CycleViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
    //create TextView (or other view) for CycleViewHolder
    TextView v = (TextView) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.cycle_description_view, parent, false);

    CycleViewHolder holder = new CycleViewHolder(v);
    return holder;
  }

  @Override
  public void onBindViewHolder(CycleViewHolder holder, int position) {
    ArrayList<Cycle> cycles = workout.getCycles();
    String text = cycles.get(position).getName();
    holder.textView.setText(text);
  }

  @Override
  public int getItemCount() {
    return workout.getCycles().size();
  }
}
