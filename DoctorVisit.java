package edu.gwu.coviddashboard;

public class DoctorVisit extends LogEvent{
    private String VisitType; //type of visit (check-up, consultation, surgery, emergency visit, other)
    private String DoctorType; //type of doctor

    public DoctorVisit(String VisitType,String DoctorType, String date_of_visit, String description, double riskFactor) {
        super(date_of_visit, riskFactor, description);
        this.VisitType = VisitType;
        this.DoctorType = DoctorType;
    }

    public DoctorVisit(String VisitType,String DoctorType, String date_of_visit, String description) {
        super(date_of_visit, description);
        this.VisitType = VisitType;
        this.DoctorType = DoctorType;
    }

    public String getVisitType() {
        return VisitType;
    }

    public String getDoctorType() {
        return DoctorType;
    }

    public void setVisitType(String type) {
        this.VisitType = VisitType;
    }

    public void setDoctorType(String type) {
        this.DoctorType = DoctorType;
    }

    public String getDateOfVisit() {
        return super.getDate();
    }

    public void setDateOfVisit(String date_of_visit) {
        super.setDate(date_of_visit);
    }

    public Double getRisk_factor() {
        return super.getRiskFactor();
    }

    public void setRisk_factor(Double risk_factor) {

        super.setRiskFactor(risk_factor);
    }
}
