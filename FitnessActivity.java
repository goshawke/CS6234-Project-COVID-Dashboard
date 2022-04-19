package edu.gwu.coviddashboard;

public class FitnessActivity extends LogEvent {

    private String name;
    private String duration;
    private String caloriesBurned;



    private Double rf;

    public FitnessActivity(String name, String duration, String caloriesBurned, String date, String description, Double rf) {
        super(date, rf, description);
        this.name = name;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.rf = rf;
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

    public String getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(String caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Double getRf() {
        return rf;
    }

    public void setRf(Double rf) {
        this.rf = rf;
    }
}
