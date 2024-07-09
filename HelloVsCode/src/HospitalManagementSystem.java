import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://127.0.0.2:3306/hospital";
    private static final String username = "root";
    private static final String password = "Nic@12345";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            Emp emp = new Emp(connection, scanner);
            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Add Employee");
                System.out.println("5. View Employees");
                System.out.println("6. View Patients By ID");
                System.out.println("7. Exit");
                System.out.println();
                System.out.print("Enter Your Choice :- ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:

                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:

                        doctor.viewDocctors();
                        System.out.println();
                        break;
                    case 4:
                        emp.addEmp();
                        System.out.println();
                        break;
                    case 5:
                        emp.viewEmployee();
                        System.out.println();
                        break;

                    case 6:
                        patient.viewPatientById();
                        System.out.println();
                        break;
                    case 7:
                        System.out.print(" ! ! !  Developed By Nicky Ranjan  ! ! !");
                        return;

                    default:
                        System.out.println("Enter Valid Choice");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
