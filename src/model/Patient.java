package model;

import exceptions.InvalidDataException;

public class Patient extends Person {

    private String diagnosis;
    private String bloodType;

    public Patient(int id, String name, int age, String gender,
                   String diagnosis, String bloodType)
            throws InvalidDataException {

        super(id, name, age, gender);

        this.diagnosis = diagnosis;
        this.bloodType = bloodType;
    }

    @Override
    public void doWork() {
        System.out.println("Patient " + name + " is resting");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Diagnosis: " + diagnosis +
                ", Blood type: " + bloodType;
    }
}
