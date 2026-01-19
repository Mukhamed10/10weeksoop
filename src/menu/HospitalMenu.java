package menu;

import model.*;
import exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.Scanner;

public class HospitalMenu {

    private final ArrayList<Person> people = new ArrayList<>();
    private final ArrayList<Appointment> appointments = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public HospitalMenu() {
        try {
            people.add(new Doctor(1, "Dr. Smith", 45, "M", "Surgeon", 1111));
            people.add(new Patient(2, "Alice", 30, "F", "Flu", "O+"));
            people.add(new Nurse(3, "Mary", 35, "F"));
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        int choice;

        do {
            System.out.println("""
                    \n=== HOSPITAL MENU ===
                    1. Show all people
                    2. Everyone works
                    3. Add patient
                    4. Add doctor
                    5. Add nurse
                    6. Create appointment
                    7. Show appointments
                    0. Exit
                    """);

            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> showAllPeople();
                    case 2 -> everyoneWorks();
                    case 3 -> addPatient();
                    case 4 -> addDoctor();
                    case 5 -> addNurse();
                    case 6 -> createAppointment();
                    case 7 -> showAppointments();
                    case 0 -> System.out.println("Exiting...");
                    default -> System.out.println("Wrong option!");
                }
            } catch (InvalidDataException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (choice != 0);
    }

    private void showAllPeople() {
        for (Person p : people) {
            p.showInfo();
        }
    }

    private void everyoneWorks() {
        for (Person p : people) {
            p.doWork();
        }
    }

    private void addPatient() throws InvalidDataException {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.print("Diagnosis: ");
        String diag = scanner.nextLine();
        System.out.print("Blood type: ");
        String blood = scanner.nextLine();

        people.add(new Patient(id, name, age, gender, diag, blood));
        System.out.println("Patient added");
    }

    private void addDoctor() throws InvalidDataException {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.print("Specialization: ");
        String spec = scanner.nextLine();
        System.out.print("License number: ");
        int lic = scanner.nextInt(); scanner.nextLine();

        people.add(new Doctor(id, name, age, gender, spec, lic));
        System.out.println("Doctor added");
    }

    private void addNurse() throws InvalidDataException {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Gender (M/F): ");
        String gender = scanner.nextLine();

        people.add(new Nurse(id, name, age, gender));
        System.out.println("Nurse added");
    }

    private void createAppointment() {
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Doctor> doctors = new ArrayList<>();

        for (Person p : people) {
            if (p instanceof Patient) patients.add((Patient) p);
            if (p instanceof Doctor) doctors.add((Doctor) p);
        }

        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Need at least one patient and one doctor");
            return;
        }

        System.out.println("Choose patient:");
        for (int i = 0; i < patients.size(); i++)
            System.out.println((i + 1) + ". " + patients.get(i).getName());
        int pIndex = scanner.nextInt() - 1;

        System.out.println("Choose doctor:");
        for (int i = 0; i < doctors.size(); i++)
            System.out.println((i + 1) + ". " + doctors.get(i).getName());
        int dIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Date & time: ");
        String date = scanner.nextLine();

        Appointment ap = new Appointment(
                appointments.size() + 1,
                patients.get(pIndex),
                doctors.get(dIndex),
                date
        );
        ap.confirm();
        appointments.add(ap);

        System.out.println("Appointment created");
    }

    private void showAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }
}
