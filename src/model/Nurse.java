package model;

import exceptions.InvalidDataException;

public class Nurse extends Person {

    public Nurse(int id, String name, int age, String gender)
            throws InvalidDataException {
        super(id, name, age, gender);
    }

    @Override
    public void doWork() {
        System.out.println("Nurse " + name + " is helping patients");
    }
}
