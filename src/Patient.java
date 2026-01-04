public class Patient extends Person {
    private String diagnosis;
    private String bloodType;

    public Patient(int id, String name, int age, String gender,
                   String diagnosis, String bloodType) {
        super(id, name, age, gender);
        this.diagnosis = diagnosis;
        this.bloodType = bloodType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis != null && !diagnosis.trim().isEmpty()) {
            this.diagnosis = diagnosis;
        }
    }

    public void setBloodType(String bloodType) {
        if (bloodType != null && !bloodType.trim().isEmpty()) {
            this.bloodType = bloodType;
        }
    }

    @Override
    public void work() {
        System.out.println("Patient " + getName() + " is recovering from " + diagnosis);
    }

    @Override
    public String toString() {
        return super.toString() + ", Diagnosis: " + diagnosis + ", Blood Type: " + bloodType;
    }

    public boolean needsEmergencyCare() {
        return diagnosis != null &&
                (diagnosis.toLowerCase().contains("emergency") ||
                        diagnosis.toLowerCase().contains("severe"));
    }

    public boolean needsSurgery() {
        return diagnosis != null &&
                diagnosis.toLowerCase().contains("surgery");
    }

    public String getTreatmentPlan() {
        if (isChild()) {
            return "Pediatric treatment for " + getName();
        } else if (isSenior()) {
            return "Geriatric treatment for " + getName();
        }
        return "Standard treatment for " + getName();
    }
}