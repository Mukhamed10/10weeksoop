public class Doctor extends Person {
    private String specialization;
    private int licenseNumber;

    public Doctor(int id, String name, int age, String gender,
                  String specialization, int licenseNumber) {
        super(id, name, age, gender);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setSpecialization(String specialization) {
        if (specialization != null ) {
            this.specialization = specialization;
        }
    }

    public void setLicenseNumber(int licenseNumber) {
        if (licenseNumber > 0) {
            this.licenseNumber = licenseNumber;
        }
    }

    @Override
    public void work() {
        System.out.println("Doctor " + getName() + " (" + specialization + ") is consulting patients");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Specialization: " + specialization +
                ", License: " + licenseNumber;
    }

    public void performSurgery(String surgeryName) {
        System.out.println("Doctor " + getName() + " performs surgery: " + surgeryName);
    }

    public boolean isSurgeon() {
        return specialization != null &&
                specialization.toLowerCase().contains("surgeon");
    }

    public String getTitle() {
        if (getAge() < 35) return "Young Doctor " + getName();
        if (getAge() > 55) return "Senior Doctor " + getName();
        return "Doctor " + getName();
    }

    public boolean canPerformSurgery() {
        return isSurgeon() && getAge() >= 30;
    }
}