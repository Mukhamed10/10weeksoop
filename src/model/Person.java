package model;

import exceptions.InvalidDataException;
import interfaces.Worker;
import interfaces.Printable;

public abstract class Person implements Worker, Printable {

    protected int id;
    protected String name;
    protected int age;
    protected String gender;


    public Person(int id, String name, int age, String gender)
            throws InvalidDataException {
        if (id <= 0)
            throw new InvalidDataException("ID must be positive");
        if (name == null || name.isEmpty())
            throw new InvalidDataException("Name cannot be empty");
        if (age < 0 || age > 150)
            throw new InvalidDataException("Invalid age");
        if (!gender.equals("M") && !gender.equals("F"))
            throw new InvalidDataException("Gender must be M or F");

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }


    @Override
    public abstract void doWork();

    @Override
    public void showInfo() {
        System.out.println(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Age: " + age +
                ", Gender: " + gender;
    }
}
