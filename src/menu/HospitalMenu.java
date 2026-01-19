package menu;

import model.*;
import exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
            System.out.println("Initialization error: " + e.getMessage());
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
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Please enter a valid number.");
                scanner.nextLine(); // очистка буфера
                choice = -1;
            }

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

        } while (choice != 0);
    }

    private void showAllPeople() {
        if (people.isEmpty()) {
            System.out.println("No people in the system.");
            return;
        }
        for (Person p : people) {
            p.showInfo();
        }
    }

    private void everyoneWorks() {
        for (Person p : people) {
            p.doWork();
        }
    }

    private void addPatient() {
        while (true) {
            try {
                int id = readInt("ID: ");
                String name = readString("Name: ");
                int age = readInt("Age: ");
                String gender = readGender("Gender (M/F): ");
                String diag = readString("Diagnosis: ");
                String blood = readString("Blood type: ");

                Patient patient = new Patient(id, name, age, gender, diag, blood);
                people.add(patient);
                System.out.println("Patient added successfully!");
                break;

            } catch (InvalidDataException | InputMismatchException e) {
                System.out.println("ERROR: " + e.getMessage() + "\nPlease try again.\n");
                scanner.nextLine();
            }
        }
    }

    private void addDoctor() {
        while (true) {
            try {
                int id = readInt("ID: ");
                String name = readString("Name: ");
                int age = readInt("Age: ");
                String gender = readGender("Gender (M/F): ");
                String spec = readString("Specialization: ");
                int lic = readInt("License number: ");

                Doctor doctor = new Doctor(id, name, age, gender, spec, lic);
                people.add(doctor);
                System.out.println("Doctor added successfully!");
                break;

            } catch (InvalidDataException | InputMismatchException e) {
                System.out.println("ERROR: " + e.getMessage() + "\nPlease try again.\n");
                scanner.nextLine();
            }
        }
    }

    private void addNurse() {
        while (true) {
            try {
                int id = readInt("ID: ");
                String name = readString("Name: ");
                int age = readInt("Age: ");
                String gender = readGender("Gender (M/F): ");

                Nurse nurse = new Nurse(id, name, age, gender);
                people.add(nurse);
                System.out.println("Nurse added successfully!");
                break;

            } catch (InvalidDataException | InputMismatchException e) {
                System.out.println("ERROR: " + e.getMessage() + "\nPlease try again.\n");
                scanner.nextLine();
            }
        }
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
        int pIndex = readChoice(patients.size()) - 1;

        System.out.println("Choose doctor:");
        for (int i = 0; i < doctors.size(); i++)
            System.out.println((i + 1) + ". " + doctors.get(i).getName());
        int dIndex = readChoice(doctors.size()) - 1;

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

        System.out.println("Appointment created successfully!");
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

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid number. Try again.");
                scanner.nextLine();
            }
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private String readGender(String prompt) {
        while (true) {
            System.out.print(prompt);
            String g = scanner.nextLine().trim().toUpperCase();
            if (g.equals("M") || g.equals("F")) return g;
            System.out.println("Invalid gender. Enter M or F.");
        }
    }

    private int readChoice(int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= max) return choice;
                System.out.println("Invalid choice. Enter number between 1 and " + max);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
            }
        }
    }
}
