public class Appointment {
    private int id;
    private Person patient;
    private Person doctor;
    private String dateTime;
    private String status;
    public Appointment(int id, Person patient, Person doctor, String dateTime, String status) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Person getPatient() {
        return patient;
    }

    public Person getDoctor() {
        return doctor;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void confirmAppointment() {
        this.status = "Confirmed";}


    public void cancelAppointment() {
        this.status = "Cancelled";}

    @Override
    public String toString() {
        return "appointmnet #" + id +
                "  patient: " + patient.getName() + "  doctor: " + doctor.getName() + "  date: " + dateTime +
                " status: " + status;
    }
}




