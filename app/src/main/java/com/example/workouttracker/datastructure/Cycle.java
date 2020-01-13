package com.example.workouttracker.datastructure;

import java.util.ArrayList;

public class Cycle {
    private String name;
    private ArrayList<Exercise> exercises;
    private int cycleRepetitions;

    public Cycle(){
        name = "";
        exercises = new ArrayList<>();
        cycleRepetitions = 0;
    }

    public Cycle(String name, ArrayList<Exercise> exercises, int cycleReptations){
        this.name = name;
        this.exercises = exercises;
        this.cycleRepetitions = cycleReptations;
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

    public void setCycleRepetitions(int cycleRepetitions) {
        this.cycleRepetitions = cycleRepetitions;
    }

    public int getCycleRepetitions() {
        return cycleRepetitions;
    }
}
