import java.util.ArrayList;
import java.util.Scanner;

public class demo {
    public static void main(String[] args) {
        ArrayList<Person> allPeople = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        allPeople.add(new Person(1, "Admin", 40, "M"));
        allPeople.add(new Patient(2, "Alice", 25, "F", "Flu", "O+"));
        allPeople.add(new Doctor(101, "Dr. Smith", 45, "M", "Surgeon", 12345));
        allPeople.add(new Patient(3, "Bob", 70, "M", "Fracture", "A-"));

        boolean dd = true;

        while (dd) {
            System.out.println("=== HOSPITAL SYSTEM ===");
            System.out.println("1. Show all people");
            System.out.println("2. Everyone works (Polymorphism)");
            System.out.println("3. Patients only");
            System.out.println("4. Doctors only");
            System.out.println("5. Add new patient");
            System.out.println("6. Add new doctor");
            System.out.println("7. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showAllPeople(allPeople);
                case 2 -> showPolymorphism(allPeople);
                case 3 -> showOnlyPatients(allPeople);
                case 4 -> showOnlyDoctors(allPeople);
                case 5 -> addNewPatient(allPeople, scanner);
                case 6 -> addNewDoctor(allPeople, scanner);
                case 7 -> {
                    dd = false;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Wrong choice!");
            }
        }

        scanner.close();
    }

    static void showAllPeople(ArrayList<Person> people) {
        System.out.println("--- ALL PEOPLE ---");
        if (people.isEmpty()) {
            System.out.println("No people found");
            return;
        }

        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            System.out.println((i+1) + ". " + p);
        }
    }

    static void showPolymorphism(ArrayList<Person> people) {
        System.out.println("\n--- EVERYONE WORKS ---");
        for (Person p : people) {
            p.work();
        }
        System.out.println("↑ This is POLYMORPHISM!");
    }

    static void showOnlyPatients(ArrayList<Person> people) {
        System.out.println("\n--- PATIENTS ONLY ---");
        int count = 0;

        for (Person p : people) {
            if (p instanceof Patient) {
                count++;
                Patient patient = (Patient) p; // Convert Person to Patient
                System.out.println(count + ". " + patient.getName());
                System.out.println("   Diagnosis: " + patient.getDiagnosis());
                System.out.println("   Blood type: " + patient.getBloodType());
            }
        }

        if (count == 0) {
            System.out.println("No patients");
        }
    }

    static void showOnlyDoctors(ArrayList<Person> people) {
        System.out.println("\n--- DOCTORS ONLY ---");
        int count = 0;

        for (Person p : people) {
            if (p instanceof Doctor) {
                count++;
                Doctor doctor = (Doctor) p; // Convert Person to Doctor
                System.out.println(count + ". " + doctor.getName());
                System.out.println("   Specialization: " + doctor.getSpecialization());
                System.out.println("   License: " + doctor.getLicenseNumber());
            }
        }

        if (count == 0) {
            System.out.println("No doctors");
        }
    }

    static void addNewPatient(ArrayList<Person> people, Scanner scanner) {
        System.out.println("--- NEW PATIENT ---");

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

        System.out.print("Blood type: ");
        String blood = scanner.nextLine();



        Person newPatient = new Patient(id, name, age, gender, diagnosis, blood);
        people.add(newPatient);

        System.out.println("Patient added!");
    }

    static void addNewDoctor(ArrayList<Person> people, Scanner scanner) {
        System.out.println("--- NEW DOCTOR ---");

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
        String spec = scanner.nextLine();

        System.out.print("License number: ");
        int license = scanner.nextInt();
        scanner.nextLine();



        Person newDoctor = new Doctor(id, name, age, gender, spec, license);
        people.add(newDoctor);

        System.out.println("Doctor added!");
    }
}
