package com.example.workouttracker.datastructure;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable {
    private String name;
    private ArrayList<Cycle> cycles;
    private int index;

    public Workout() {
        name = "";
        cycles = new ArrayList<>();
        index = -10;
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

    public void setIndex(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
