package com.example.workouttracker.datastructure;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String name;
    private String description;
    private int sets;

    public Exercise(){
        name = "";
        description = "";
        sets = 1;
    }

    public Exercise(String name, String description, int sets){
        this.name = name;
        this.description = description;
        this.sets = sets;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getSets() {
        return sets;
    }
}
