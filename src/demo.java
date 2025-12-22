public class demo {
    public static void main(String[] args) {
        Patient patient1 = new Patient(1, "Alice ", 30, "corona");
        Patient patient2 = new Patient(2, "Bob ", 15, "cold");

        Doctor doctor1 = new Doctor("Wilson", 50, "Main doctor", 101);
        Doctor doctor2 = new Doctor("Lee", 40, "Surgeon", 102);

        Appointment appointment1 = new Appointment(1001, "Alice", "Wilson", "2024-12-25", "Confirmed");

        System.out.println("Patient 1: " + patient1);
        System.out.println("Patient 2: " + patient2);
        System.out.println("Doctor 1: " + doctor1);
        System.out.println("Doctor 2: " + doctor2);
        System.out.println("Appointment 1: " + appointment1);

        System.out.println("Patient 1 name: " + patient1.getName());
        System.out.println("Patient 1 age: " + patient1.getAge());
        System.out.println("Doctor 1 specialization: " + doctor1.getSpecialization());
        System.out.println("Appointment status: " + appointment1.getStatus());

        patient2.setDiagnosis("Severe Cold");
        System.out.println("Updated patient 2: " + patient2);

        appointment1.setStatus("Completed");
        System.out.println("Updated appointment: " + appointment1);

        System.out.println("Doctor is experienced: " + doctor1.isExperienced());
        System.out.println("Doctor is retired: " + doctor1.isRetired());

        appointment1.confirmAppointment();
        System.out.println("Appointment status: " + appointment1.getStatus());

        appointment1.cancelAppointment();
        System.out.println("Appointment status: " + appointment1.getStatus());

        System.out.println("Patient is adult: " + patient1.isAdult());
        System.out.println("Patient is old: " + patient1.isOld());


    }
}
