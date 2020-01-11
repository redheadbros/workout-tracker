package com.example.workouttracker.datastructure;

public class Exercise {
    private String name;
    private String description;
    private int sets;

    public Exercise(){
        name = "";
        description = "";
        sets = 0;
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
