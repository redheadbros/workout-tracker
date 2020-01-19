package com.example.workouttracker;

import com.example.workouttracker.datastructure.Cycle;
import com.example.workouttracker.datastructure.Exercise;
import com.example.workouttracker.datastructure.Workout;

import java.util.ArrayList;

public class WorkoutProgress {

  private ArrayList<Integer> cyclesCompleted;
  private ArrayList<ArrayList<Integer>> setsCompleted;

  public WorkoutProgress(Workout toTrack) {
    cyclesCompleted = new ArrayList<>();
    setsCompleted = new ArrayList<>();

    ArrayList<Cycle> cycles = toTrack.getCycles();
    ArrayList<Exercise> exercises;

    for (int cycleIndex = 0; cycleIndex < cycles.size(); cycleIndex++) {
      cyclesCompleted.add(0);
      setsCompleted.add(new ArrayList<Integer>());

      exercises = cycles.get(cycleIndex).getExercises();
      for (int exerciseIndex = 0; exerciseIndex < exercises.size(); exerciseIndex++) {
        setsCompleted.get(cycleIndex).add(0);
      }
    }
  }

  public int getCyclesCompleted(int cycleIndex) {
    return cyclesCompleted.get(cycleIndex);
  }

  public int getSetsCompleted(int cycleIndex, int exerciseIndex) {
    return setsCompleted.get(cycleIndex).get(exerciseIndex);
  }

  public void setCyclesCompleted(int cycleIndex, int value) {
    cyclesCompleted.set(cycleIndex, value);
  }

  public void setSetsCompleted(int cycleIndex, int exerciseIndex, int value) {
    setsCompleted.get(cycleIndex).set(exerciseIndex,value);
  }
}
