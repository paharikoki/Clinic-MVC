package Klinik.Model;

public class Visit {
    private int visitId;
    private Doctor doctor;
    private Patient patient;
    private String visitDate;
    private String diagnosis;
    private String treatment;

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public Doctor getDokter() {
        return doctor;
    }

    public void setDokter(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPasien() {
        return patient;
    }

    public void setPasien(Patient patient) {
        this.patient = patient;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
