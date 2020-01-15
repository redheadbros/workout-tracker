package com.example.workouttracker;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Workout;


public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.CycleViewHolder> {

  //contains class for holding views for part of the description
  public static class CycleViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    //TODO: add more detail, or sublists, et cetera for cycle details
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
    //TODO: fill this out
    //create TextView (or other view) for CycleViewHolder

    //fill in constructor
    CycleViewHolder holder = new CycleViewHolder();
    return holder;
  }

  @Override
  public void onBindViewHolder(CycleViewHolder holder, int position) {
    //TODO:stick cycle details into the view holder's text fields
  }

  @Override
  public int getItemCount() {
    return workout.getCycles().size();
  }
}
