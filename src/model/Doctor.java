package model;

import exceptions.InvalidDataException;

public class Doctor extends Person {

    private String specialization;
    private int licenseNumber;

    public Doctor(int id, String name, int age, String gender,
                  String specialization, int licenseNumber)
            throws InvalidDataException {

        super(id, name, age, gender);

        if (licenseNumber <= 0)
            throw new InvalidDataException("Invalid license number");

        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    @Override
    public void doWork() {
        System.out.println("Doctor " + name + " is treating patients");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Specialization: " + specialization +
                ", License: " + licenseNumber;
    }
}
