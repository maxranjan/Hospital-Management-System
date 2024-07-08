import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Emp {
    private Connection connection;

    private Scanner scanner;

    Emp(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addEmp() {
        System.out.print("Enter Employee Name:- ");
        String name = scanner.next();
        System.out.print("Enter Employee Age:- ");
        int age = scanner.nextInt();
        System.out.print("Enter Employee Gender:- ");
        String gender = scanner.next();
        System.out.print("Enter Employee Role:- ");
        String role = scanner.next();

        try {

            String query = " INSERT INTO employees(name, age, gender,role)VALUES(? , ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, role);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println(" Employee Added Successfully");
            } else {
                System.out.println(" Failed To Add Employee");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewEmployee() {
        String query = "select * from employees";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(); // It hold the table from the database then set the
                                                                    // point name of next and print
            System.out.println("Employees");
            System.out.println("+------------+-----------------------+----------+------------+---------------------+");
            System.out.println("|Employee ID |Name                   |Age       |Gender      |Role                 |");
            System.out.println("+------------+-----------------------+----------+------------+---------------------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String role = resultSet.getString("role");
                System.out.printf("|%-12s|%-23s|%-10s|%-12s|%-21s|\n", id, name, age, gender, role);
                System.out.println(
                        "+------------+-----------------------+----------+------------+---------------------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
