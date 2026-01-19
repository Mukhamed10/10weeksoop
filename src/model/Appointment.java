package model;

public class Appointment {

    private int id;
    private Patient patient;
    private Doctor doctor;
    private String dateTime;
    private String status;

    public Appointment(int id, Patient patient, Doctor doctor, String dateTime) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = "Created";
    }

    public void confirm() {
        status = "Confirmed";
    }

    public void cancel() {
        status = "Cancelled";
    }

    @Override
    public String toString() {
        return "Appointment #" + id +
                ", Patient: " + patient.getName() +
                ", Doctor: " + doctor.getName() +
                ", Date: " + dateTime +
                ", Status: " + status;
    }
}
