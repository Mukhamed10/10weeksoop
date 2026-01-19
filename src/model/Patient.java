package model;

import exceptions.InvalidDataException;

public class Patient extends Person {

    private String diagnosis;

    public Patient(int id, String name, int age, String gender, String diagnosis)
            throws InvalidDataException {
        super(id, name, age, gender);
        this.diagnosis = diagnosis;
    }

    @Override
    public void doWork() {
        System.out.println("Patient " + name + " is resting");
    }

    @Override
    public String toString() {
        return super.toString() + ", Diagnosis: " + diagnosis;
    }
}
