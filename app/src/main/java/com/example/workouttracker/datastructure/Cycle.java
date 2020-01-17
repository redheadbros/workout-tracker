package com.example.workouttracker.datastructure;

import java.io.Serializable;
import java.util.ArrayList;

public class Cycle implements Serializable {
    private String name;
    private ArrayList<Exercise> exercises;
    private int cycleRepetitions;

    public Cycle(){
        name = "";
        exercises = new ArrayList<>();
        cycleRepetitions = 1;
    }

    public Cycle(String name, ArrayList<Exercise> exercises, int cycleRepetitions){
        this.name = name;
        this.exercises = exercises;
        this.cycleRepetitions = cycleRepetitions;
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
