
public class Doctor {
    private int id;
    private String name;
    private int Age;
    private String Specialization;
    public Doctor(String name, int age, String specialization, int id) {
        this.name = name;
        this.Age = age;
        this.Specialization = specialization;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }
    public boolean isExperienced() {
        return Age >= 40;
    }
    public boolean isRetired() {
        return Age <= 65;
    }

    @Override
    public String toString(){
        return "id: "+ id + "Name: " + name + " Age: " + Age + " Specialization: " + Specialization;
    }


}
