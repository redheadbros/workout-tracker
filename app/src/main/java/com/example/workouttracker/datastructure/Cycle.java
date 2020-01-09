package com.example.workouttracker.datastructure;

import java.util.ArrayList;

public class Cycle {
    private String name;
    private ArrayList<Exercise> exercises;
    private int cycleReptations;

    public Cycle(){
        name = "";
        exercises = new ArrayList<>();
        cycleReptations = 0;
    }

    public Cycle(String name, ArrayList<Exercise> exercises, int cycleReptations){
        this.name = name;
        this.exercises = exercises;
        this.cycleReptations = cycleReptations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setCycleReptations(int cycleReptations) {
        this.cycleReptations = cycleReptations;
    }

    public int getCycleReptations() {
        return cycleReptations;
    }
}
