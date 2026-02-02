package menu;

import database.PersonDAO;
import model.*;
import exceptions.InvalidDataException;
import java.util.List;
import java.util.Scanner;

public class HospitalMenu implements Menu {
    private Scanner scanner;
    private PersonDAO personDAO;  // NO MORE ArrayList!

    public HospitalMenu() {
        this.scanner = new Scanner(System.in);
        this.personDAO = new PersonDAO();
        // REMOVED: ArrayList initialization
    }

    @Override
    public void displayMenu() {
        System.out.println("""
                === HOSPITAL MANAGEMENT SYSTEM (Week 8) ===
                1. Show all people
                2. Add new person
                3. Update person
                4. Delete person
                5. Search by name
                6. Search by age range
                7. Search doctors by specialization
                8. Show all doctors
                9. Show all nurses
                10. Show all patients
                11. Everyone works (Polymorphism)
                12. Exit
                """);
    }

    @Override
    public void run() {
        int choice;

        do {
            displayMenu();
            System.out.print("Choose option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
                choice = -1;
            }

            switch (choice) {
                case 1 -> showAllPeople();
                case 2 -> addNewPerson();
                case 3 -> updatePerson();
                case 4 -> deletePerson();
                case 5 -> searchByName();
                case 6 -> searchByAgeRange();
                case 7 -> searchDoctorsBySpecialization();
                case 8 -> showAllDoctors();
                case 9 -> showAllNurses();
                case 10 -> showAllPatients();
                case 11 -> everyoneWorks();
                case 12 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option!");
            }

        } while (choice != 12);
    }

    private void showAllPeople() {
        personDAO.displayAllPeople();
    }

    private void addNewPerson() {
        System.out.println("Select type:");
        System.out.println("1. Doctor");
        System.out.println("2. Nurse");
        System.out.println("3. Patient");
        System.out.print("Choice: ");

        try {
            int typeChoice = Integer.parseInt(scanner.nextLine());

            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Gender (M/F): ");
            String gender = scanner.nextLine();

            Person person = null;

            switch (typeChoice) {
                case 1 -> {
                    System.out.print("Specialization: ");
                    String specialization = scanner.nextLine();
                    person = new Doctor(id, name, age, gender, specialization);
                }
                case 2 -> {
                    person = new Nurse(id, name, age, gender);
                }
                case 3 -> {
                    System.out.print("Diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    person = new Patient(id, name, age, gender, diagnosis);
                }
                default -> {
                    System.out.println("Invalid type!");
                    return;
                }
            }

            boolean success = personDAO.insertPerson(person);
            System.out.println(success ? "Person added successfully!" : "Failed to add person!");

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numbers");
        } catch (InvalidDataException e) {
            System.out.println("Invalid data: " + e.getMessage());
        }
    }

    private void updatePerson() {
        System.out.print("Enter person ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Person person = personDAO.getPersonById(id);

            if (person == null) {
                System.out.println("Person not found!");
                return;
            }

            System.out.println("Current: " + person);
            System.out.println("What to update?");

            if (person instanceof Doctor) {
                updateDoctor((Doctor) person);
            } else if (person instanceof Patient) {
                updatePatient((Patient) person);
            } else {
                System.out.println("Only doctors and patients can be updated in this version.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
        }
    }

    private void updateDoctor(Doctor doctor) {
        try {
            System.out.print("New name (press Enter to keep): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) doctor.setName(name);

            System.out.print("New age (press Enter to keep): ");
            String ageStr = scanner.nextLine();
            if (!ageStr.trim().isEmpty()) doctor.setAge(Integer.parseInt(ageStr));

            System.out.print("New specialization (press Enter to keep): ");
            String specialization = scanner.nextLine();
            if (!specialization.trim().isEmpty()) doctor.setSpecialization(specialization);

            boolean success = personDAO.updateDoctor(doctor);
            System.out.println(success ? "Update successful!" : "Update failed!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number!");
        }
    }

    private void updatePatient(Patient patient) {
        try {
            System.out.print("New name (press Enter to keep): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) patient.setName(name);

            System.out.print("New diagnosis (press Enter to keep): ");
            String diagnosis = scanner.nextLine();
            if (!diagnosis.trim().isEmpty()) patient.setDiagnosis(diagnosis);

            boolean success = personDAO.updatePatient(patient);
            System.out.println(success ? "Update successful!" : "Update failed!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deletePerson() {
        System.out.print("Enter person ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                boolean success = personDAO.deletePerson(id);
                System.out.println(success ? "Deleted successfully!" : "Delete failed!");
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
        }
    }

    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Person> results = personDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("No people found matching '" + name + "'");
        } else {
            System.out.println("\nSearch results:");
            for (Person p : results) {
                System.out.println(p);
            }
        }
    }

    private void searchByAgeRange() {
        try {
            System.out.print("Min age: ");
            int min = Integer.parseInt(scanner.nextLine());
            System.out.print("Max age: ");
            int max = Integer.parseInt(scanner.nextLine());

            List<Person> results = personDAO.searchByAgeRange(min, max);

            if (results.isEmpty()) {
                System.out.println("No people found in age range " + min + "-" + max);
            } else {
                System.out.println("\nPeople in age range:");
                for (Person p : results) {
                    System.out.println(p);
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid age!");
        }
    }

    private void searchDoctorsBySpecialization() {
        System.out.print("Enter specialization to search: ");
        String specialization = scanner.nextLine();

        List<Doctor> doctors = personDAO.searchDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found with specialization '" + specialization + "'");
        } else {
            System.out.println("\nDoctors found:");
            for (Doctor d : doctors) {
                System.out.println(d);
            }
        }
    }

    private void showAllDoctors() {
        List<Doctor> doctors = personDAO.getAllDoctors();
        System.out.println("\n=== ALL DOCTORS ===");
        for (Doctor d : doctors) {
            System.out.println(d);
        }
        System.out.println("Total: " + doctors.size() + " doctors\n");
    }

    private void showAllNurses() {
        List<Nurse> nurses = personDAO.getAllNurses();
        System.out.println("\n=== ALL NURSES ===");
        for (Nurse n : nurses) {
            System.out.println(n);
        }
        System.out.println("Total: " + nurses.size() + " nurses\n");
    }

    private void showAllPatients() {
        List<Patient> patients = personDAO.getAllPatients();
        System.out.println("\n=== ALL PATIENTS ===");
        for (Patient p : patients) {
            System.out.println(p);
        }
        System.out.println("Total: " + patients.size() + " patients\n");
    }

    private void everyoneWorks() {
        List<Person> people = personDAO.getAllPeople();
        System.out.println("\n=== EVERYONE WORKS (POLYMORPHISM) ===");
        for (Person p : people) {
            p.doWork();  // Each calls its own doWork() method
        }
        System.out.println("======================================\n");
    }

    // Getters and setters for Person (need to add these to your model classes!)
    // In Doctor.java:
    // public void setSpecialization(String specialization) { this.specialization = specialization; }

    // In Patient.java:
    // public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    // In Person.java:
    // public void setName(String name) { this.name = name; }
    // public void setAge(int age) { this.age = age; }
}