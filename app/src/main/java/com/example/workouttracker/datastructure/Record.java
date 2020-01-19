package com.example.workouttracker.datastructure;

import java.util.Date;

public class Record {
    private Date date;
    private Workout workout;

    public void Record(){
        date = new Date(System.currentTimeMillis());
    }

    public void setRecord(Workout workout){
        date = new Date(System.currentTimeMillis());
        this.workout = workout;
    }

    public Date getDate() {
        return date;
    }

    public Workout getWorkout() {
        return workout;
    }
}
