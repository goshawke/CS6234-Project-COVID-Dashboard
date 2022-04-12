package com.practice.coviddashboard;

public class FitnessActivity extends LogEvent {

    private String name;
    private String duration;
    private int caloriesBurned;

    public FitnessActivity(String name, String duration, int caloriesBurned, String date, String description, Double rf) {
        super(date, rf, description);
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }


}
