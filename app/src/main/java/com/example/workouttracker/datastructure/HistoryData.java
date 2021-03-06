package com.example.workouttracker.datastructure;

import java.util.ArrayList;
import java.util.Date;

public class HistoryData {
    private ArrayList<Record> historyList;

    public HistoryData(){
        historyList = new ArrayList<>();
    }

    public HistoryData(ArrayList<Record> historyList){
        this.historyList = historyList;
    }

    public void addHistory(Record record){
        historyList.add(record);
    }

    public ArrayList<Record> getHistoryList() {
        return historyList;
    }

    public Workout getWorkout(int index){
        return historyList.get(index).getWorkout();
    }

    public Date getDate(int index){
        return historyList.get(index).getDate();
    }
}
