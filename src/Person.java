public class Person {
    protected int id;
    protected String name;
    protected int age;
    protected String gender;

    public Person(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        if (id > 0) this.id = id;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        }
    }

    public void setGender(String gender) {
        if (gender != null && (gender.equals("M") || gender.equals("F"))) {
            this.gender = gender;
        }
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public boolean isSenior() {
        return age >= 65;
    }

    public boolean isChild() {
        return age < 18;
    }

    public void work() {
        System.out.println(name + " is working.");
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }

}