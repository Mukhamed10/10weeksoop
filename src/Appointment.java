public class Appointment {
    private int id;
    private String patientName;
    private String doctorName;
    private String dateTime;
    private String status;
    public Appointment(int  id, String patientName, String doctorName, String dateTime, String status) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void confirmAppointment() {
        this.status = "Confirmed";}

    public void cancelAppointment() {
        this.status = "Cancelled";}

    @Override
    public String toString(){
        return "id=" + id + ", patient=" + patientName +
                ", doctor=" + doctorName + ", date=" + dateTime +
                ", status=" + status;

    }


}
