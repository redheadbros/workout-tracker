package com.example.workouttracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class WorkoutEditorAdapter extends RecyclerView.Adapter<WorkoutEditorAdapter.WorkoutEditorViewHolder> {

  public static class WorkoutEditorViewHolder extends RecyclerView.ViewHolder{
    public Button button;
    public WorkoutEditorViewHolder (Button b) {
      super(b);
      button = b;
    }
  }

  private ArrayList<Cycle> cycles;
  private Workout workoutBeingEdited;
  private Context context;

  public WorkoutEditorAdapter(Context c, Workout workout) {
    workoutBeingEdited = workout;
    cycles = workout.getCycles();
  }

  @Override
  public WorkoutEditorAdapter.WorkoutEditorViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
    Button button = (Button) LayoutInflater.from(parent.getContext())
        .inflate(viewType, parent, false);

    WorkoutEditorViewHolder holder = new WorkoutEditorViewHolder(button);

    return holder;
  }

  @Override
  public void onBindViewHolder(WorkoutEditorViewHolder holder, final int position) {
    holder.button.setText(cycles.get(position).getName());

    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent editCycle = new Intent(context, CyclesEditor.class);

        editCycle.putExtra("workout", workoutBeingEdited);
        editCycle.putExtra("cycleIndex", position);
        context.startActivity(editCycle);
      }
    });
  }

  @Override
  public int getItemCount() {
    return cycles.size();
  }
}
