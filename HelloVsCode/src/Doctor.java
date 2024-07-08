import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;
    Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDocctors() {
        String query = "select * from doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(); // It hold the table from the database then set the
                                                                    // point name of next and print
            System.out.println("Doctors");
            System.out.println("+------------+-----------------------+----------+-----------+");
            System.out.println("|Doctor ID   |Name                   |Specialization        |");
            System.out.println("+------------+-----------------------+----------+-----------+");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("|%-12s|%-23s|%-22s|\n", id, name, specialization);
                System.out.println("+------------+-----------------------+----------+-----------+");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}