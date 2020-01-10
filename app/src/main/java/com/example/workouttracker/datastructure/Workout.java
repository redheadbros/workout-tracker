package com.example.workouttracker.datastructure;

import java.util.ArrayList;

public class Workout {
    private String name;
    private ArrayList<Cycle> cycles;

    public Workout() {
        name = "";
        cycles = new ArrayList<>();
    }

    public Workout(String name, ArrayList<Cycle> cycles) {
        this.name = name;
        this.cycles = cycles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCycles(ArrayList<Cycle> cycles) {
        this.cycles = cycles;
    }

    public ArrayList<Cycle> getCycles() {
        return cycles;
    }
}