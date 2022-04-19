package edu.gwu.coviddashboard;

public class Symptom extends LogEvent{
    private String name; //name of symptom
    private int severity;



    public Symptom(String name, int severity, String date_first_experienced, String description, int riskFactor) {
        super(date_first_experienced, riskFactor, description);
        this.name = name;
        this.severity = severity;
    }

    public Symptom(String name, int severity, String date_first_experienced, String description) {
        super(date_first_experienced, description);
        this.name = name;
        this.severity = severity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getDate() {
        return super.getDate();
    }

    public void setDate(String date_first_experienced) {
        super.setDate(date_first_experienced);
    }

    public Double getRisk_factor() {
        return super.getRiskFactor();
    }

    public void setRisk_factor(Double risk_factor) {

        super.setRiskFactor(risk_factor);

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
