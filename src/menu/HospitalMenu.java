package menu;

import model.*;
import exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalMenu implements Menu {

    private ArrayList<Person> people;
    private Scanner scanner;

    public HospitalMenu() {
        people = new ArrayList<>();
        scanner = new Scanner(System.in);

        try {
            people.add(new Doctor(1001, "Dr. Ali", 45, "M", "Therapist"));
            people.add(new Nurse(2001, "Aigerim", 32, "F"));
            people.add(new Patient(3001, "Arman", 25, "M", "Flu"));
        } catch (InvalidDataException e) {
            System.out.println("Error initializing test data: " + e.getMessage());
        }
    }


    @Override
    public void displayMenu() {
        System.out.println("""
                === HOSPITAL MENU ===
                1. Show all people
                2. Everyone works
                3. Add patient
                4. Add doctor
                5. Add nurse
                0. Exit
                """);
    }

    @Override
    public void run() {
        int choice;

        do {
            displayMenu();
            System.out.print("Choose option: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                scanner.nextLine();
                choice = -1;
            }

            switch (choice) {
                case 1 -> showAllPeople();
                case 2 -> everyoneWorks();
                case 3 -> addPatient();
                case 4 -> addDoctor();
                case 5 -> addNurse();
                case 0 -> System.out.println("Program finished");
                default -> System.out.println("Wrong option");
            }

        } while (choice != 0);
    }

    private void showAllPeople() {
        if (people.isEmpty()) {
            System.out.println("No people in system");
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
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Gender (M/F): ");
            String gender = scanner.nextLine();

            System.out.print("Diagnosis: ");
            String diagnosis = scanner.nextLine();

            people.add(new Patient(id, name, age, gender, diagnosis));
            System.out.println("Patient added successfully");

        } catch (InvalidDataException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void addDoctor() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Gender (M/F): ");
            String gender = scanner.nextLine();

            System.out.print("Specialization: ");
            String specialization = scanner.nextLine();

            people.add(new Doctor(id, name, age, gender, specialization));
            System.out.println("Doctor added successfully");

        } catch (InvalidDataException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void addNurse() {
        try {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Gender (M/F): ");
            String gender = scanner.nextLine();

            people.add(new Nurse(id, name, age, gender));
            System.out.println("Nurse added successfully");

        } catch (InvalidDataException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
}
