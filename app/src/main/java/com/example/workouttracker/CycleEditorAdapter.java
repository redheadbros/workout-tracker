package com.example.workouttracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class CycleEditorAdapter extends RecyclerView.Adapter<CycleEditorAdapter.CycleEditorViewHolder> {

  public static class CycleEditorViewHolder extends RecyclerView.ViewHolder{
    public Button button;
    public CycleEditorViewHolder (Button b) {
      super(b);
      button = b;
    }
  }

  private ArrayList<Exercise> exercises;
  private Workout workoutBeingEdited;
  private int cycleIndex;
  private Cycle cycleBeingEdited;
  private Context context;

  public CycleEditorAdapter(Context c, Workout workout, int cycleIndexToEdit) {
    workoutBeingEdited = workout;
    context = c;
    ArrayList<Cycle> cycles = workout.getCycles();
    cycleIndex = cycleIndexToEdit;
    cycleBeingEdited = cycles.get(cycleIndex);
    exercises = cycleBeingEdited.getExercises();
  }

  @Override
  public CycleEditorAdapter.CycleEditorViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
    Button button = (Button) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.select_workout_button, parent, false);

   CycleEditorViewHolder holder = new CycleEditorViewHolder(button);

    return holder;
  }

  @Override
  public void onBindViewHolder(CycleEditorViewHolder holder, final int position) {
    holder.button.setText(exercises.get(position).getName());

    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent editExercise = new Intent(context, ExerciseEditor.class);

        editExercise.putExtra("workout", workoutBeingEdited);
        editExercise.putExtra("exerciseIndex", position);
        editExercise.putExtra("cycleIndex", cycleIndex);
        context.startActivity(editExercise);
      }
    });
  }

  @Override
  public int getItemCount() {
    return exercises.size();
  }
}
