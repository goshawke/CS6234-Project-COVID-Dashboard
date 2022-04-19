package edu.gwu.coviddashboard;

public class LogEvent {
    private String date;
    private double riskFactor;
    private String description;

    public LogEvent(String date,double riskFactor, String description) {
        this.date = date;
        this.riskFactor = riskFactor;
        this.description = description;
    }

    public LogEvent(String date, String description) {
        this.date = date;
        this.riskFactor = 0.0;
        this.description = description;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(double riskFactor) {
        this.riskFactor = riskFactor;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
