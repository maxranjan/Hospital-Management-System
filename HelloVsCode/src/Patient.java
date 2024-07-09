import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient() {
        System.out.print("Enter Patient Name:- ");
        String name = new java.util.Scanner(System.in).nextLine();
        System.out.print("Enter Patient Age:- ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender:- ");
        String gender = new java.util.Scanner(System.in).nextLine();

        try {

            String query = " INSERT INTO patients(name, age, gender)VALUES(? ,?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println();
                System.out.println("Patient Added Successfully");
            } else {
                System.out.println();
                System.out.println("Failed To Add Patient");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewPatients() {
        String query = "select * from patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(); // It hold the table from the database then set the
                                                                    // point name of next and print
            System.out.println("Patients");
            System.out.println("+------------+-----------------------+----------+-----------------+");
            System.out.println("|Patient ID  |Name                   |Age       |Gender           |");
            System.out.println("+------------+-----------------------+----------+-----------------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("|%-12s|%-23s|%-10s|%-17s|\n", id, name, age, gender);
                System.out.println("+------------+-----------------------+----------+-----------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    
    public void viewPatientById() {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        String query = "SELECT * FROM patients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patient Details");
            System.out.println("+------------+-----------------------+----------+-----------------+");
            System.out.println("|Patient ID  |Name                   |Age       |Gender           |");
            System.out.println("+------------+-----------------------+----------+-----------------+");

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("|%-12s|%-23s|%-10s|%-17s|\n", id, name, age, gender);
            } else {
                System.out.println("| No patient found with ID " + patientId + "                          |");
            }
            System.out.println("+------------+-----------------------+----------+-----------------+");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
