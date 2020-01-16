package com.example.workouttracker.datastructure;

import java.util.ArrayList;

public class WorkoutList {
    private ArrayList<Workout> workoutList;

    public WorkoutList(){
        workoutList = new ArrayList<Workout>();
    }

    public ArrayList<Workout> getWorkoutList() {
        return workoutList;
    }

    public void setWorkoutList(ArrayList<Workout> workoutList) {
        this.workoutList = workoutList;
    }

    public void addWorkout(Workout workout){
        workoutList.add(workout);
    }
}
