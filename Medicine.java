package edu.gwu.coviddashboard;

import android.os.Build;


public class Medicine extends LogEvent{
    private String name; //name of medicine
    private String dosage;

    public Medicine(String name, String dosage, String startDate, String description, double riskFactor) {
        super(startDate, riskFactor, description);
        this.name = name;
        this.dosage = dosage;
    }

    public Medicine(String name, String dosage, String date_first_experienced, String description) {
        super(date_first_experienced, description);
        this.name = name;
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDate() {
        return super.getDate();
    }

    public void setDate(String startDate) {
        super.setDate(startDate);
    }

    public Double getRisk_factor() {
        return super.getRiskFactor();
    }

    public void setRisk_factor(Double risk_factor) {

        super.setRiskFactor(risk_factor);
    }
}
