package model;

import exceptions.InvalidDataException;

public class Doctor extends Person {

    private String specialization;

    public Doctor(int id, String name, int age, String gender, String specialization)
            throws InvalidDataException {
        super(id, name, age, gender);
        this.specialization = specialization;
    }

    @Override
    public void doWork() {
        System.out.println("Doctor " + name + " is treating patients");
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}

