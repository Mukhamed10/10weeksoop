package database;

import model.Person;
import model.Doctor;
import model.Nurse;
import model.Patient;
import exceptions.InvalidDataException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PersonDAO {

    public boolean insertPerson(Person person) {
        String sql = "INSERT INTO people (name, age, gender, person_type, specialization, diagnosis) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getGender());

            if (person instanceof Doctor) {
                statement.setString(4, "DOCTOR");
                statement.setString(5, ((Doctor) person).getSpecialization());
                statement.setString(6, null);
            } else if (person instanceof Nurse) {
                statement.setString(4, "NURSE");
                statement.setString(5, null);
                statement.setString(6, null);
            } else if (person instanceof Patient) {
                statement.setString(4, "PATIENT");
                statement.setString(5, null);
                statement.setString(6, ((Patient) person).getDiagnosis());
            } else {
                return false;
            }


            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println(" Person added: " + person.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println(" Insert failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM people ORDER BY person_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    Person person = extractPerson(resultSet);
                    if (person != null) people.add(person);
                } catch (InvalidDataException e) {
                    System.out.println("  Invalid data in DB: " + e.getMessage());
                }
            }



            resultSet.close();
            statement.close();
            System.out.println(" Retrieved " + people.size() + " people");

        } catch (SQLException e) {
            System.out.println(" Select failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return people;
    }

    public Person getPersonById(int id) {
        String sql = "SELECT * FROM people WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Person person = extractPerson(resultSet);
                resultSet.close();
                statement.close();
                return person;
            }

            resultSet.close();
            statement.close();

        } catch (SQLException | InvalidDataException e) {
            System.out.println(" Get by ID failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE people SET name = ?, age = ?, gender = ?, specialization = ? " +
                "WHERE person_id = ? AND person_type = 'DOCTOR'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setInt(2, doctor.getAge());
            statement.setString(3, doctor.getGender());
            statement.setString(4, doctor.getSpecialization());
            statement.setInt(5, doctor.getId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println(" Doctor updated: " + doctor.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println(" Update doctor failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE people SET name = ?, age = ?, gender = ?, diagnosis = ? " +
                "WHERE person_id = ? AND person_type = 'PATIENT'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getDiagnosis());
            statement.setInt(5, patient.getId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println(" Patient updated: " + patient.getName());
                return true;
            }


        } catch (SQLException e) {
            System.out.println(" Update patient failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }
    public boolean updateNurse(Nurse nurse) {
        String sql = "UPDATE people SET name = ?, age = ?, gender = ? " +
                "WHERE person_id = ? AND person_type = 'NURSE'";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nurse.getName());
            statement.setInt(2, nurse.getAge());
            statement.setString(3, nurse.getGender());
            statement.setInt(4, nurse.getId());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println("Nurse updated: " + nurse.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Update nurse failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }


    public boolean deletePerson(int personId) {
        String sql = "DELETE FROM people WHERE person_id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println(" Person deleted (ID: " + personId + ")");
                return true;
            }


        } catch (SQLException e) {
            System.out.println(" Delete failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }


    public List<Person> searchByName(String name) {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    Person person = extractPerson(resultSet);
                    if (person != null) people.add(person);
                } catch (InvalidDataException e) {
                    System.out.println("  Invalid data: " + e.getMessage());
                }
            }

            resultSet.close();
            statement.close();
            System.out.println(" Found " + people.size() + " people matching '" + name + "'");

        } catch (SQLException e) {
            System.out.println(" Search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return people;
    }

    public List<Doctor> searchDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE person_type = 'DOCTOR' AND specialization ILIKE ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return doctors;


        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + specialization + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    Person person = extractPerson(resultSet);
                    if (person instanceof Doctor) {
                        doctors.add((Doctor) person);
                    }
                } catch (InvalidDataException e) {
                    System.out.println("️ Invalid data: " + e.getMessage());
                }
            }

            resultSet.close();
            statement.close();
            System.out.println(" Found " + doctors.size() + " doctors with specialization '" + specialization + "'");

        } catch (SQLException e) {
            System.out.println(" Specialization search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return doctors;
    }

    public List<Person> searchByAgeRange(int minAge, int maxAge) {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE age BETWEEN ? AND ? ORDER BY age";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return people;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                try {
                    Person person = extractPerson(resultSet);
                    if (person != null) people.add(person);
                } catch (InvalidDataException e) {
                    System.out.println("️ Invalid data: " + e.getMessage());
                }
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(" Age search failed: " + e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return people;
    }


    private Person extractPerson(ResultSet rs) throws SQLException, InvalidDataException {
        int id = rs.getInt("person_id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String type = rs.getString("person_type");

        if ("DOCTOR".equals(type)) {
            String specialization = rs.getString("specialization");
            return new Doctor(id, name, age, gender, specialization);
        } else if ("NURSE".equals(type)) {
            return new Nurse(id, name, age, gender);
        } else if ("PATIENT".equals(type)) {
            String diagnosis = rs.getString("diagnosis");
            return new Patient(id, name, age, gender, diagnosis);
        }
        return null;
    }

    public void displayAllPeople() {
        List<Person> people = getAllPeople();
        System.out.println("\n=== ALL PEOPLE ===");
        for (Person p : people) {
            System.out.println(p);
        }
        System.out.println("==================\n");
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        List<Person> people = getAllPeople();

        for (Person p : people) {
            if (p instanceof Doctor) {
                doctors.add((Doctor) p);
            }
        }
        return doctors;
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        List<Person> people = getAllPeople();

        for (Person p : people) {
            if (p instanceof Patient) {
                patients.add((Patient) p);
            }
        }
        return patients;
    }


    public List<Nurse> getAllNurses() {
        List<Nurse> nurses = new ArrayList<>();
        List<Person> people = getAllPeople();

        for (Person p : people) {
            if (p instanceof Nurse) {
                nurses.add((Nurse) p);
            }
        }
        return nurses;
    }
}