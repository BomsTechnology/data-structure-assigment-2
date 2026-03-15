import java.util.Scanner;
import javax.swing.JOptionPane;
import models.Employee;
import utils.FileReader;

public class Main {
  private static final int EMPLOYEE_COUNT = 1000;

  public static void main(String[] args) {
    // Display welcome dialog
    JOptionPane.showMessageDialog(null,
      "Employee Data Sorting and Searching Program!\n\nPress OK to Start",
      "Welcome to",
      JOptionPane.INFORMATION_MESSAGE);
    
    try (Scanner scanner = new Scanner(System.in)) {
      // Ask user to enter the full path of employee data file
      System.out.print("Enter the full path of employee data file: ");
      String filePath = scanner.nextLine().trim();
      
      System.out.println("Read employee data from file " + filePath);

      Employee[] employees = FileReader.readEmployeesFromFile(filePath, EMPLOYEE_COUNT);

      if (employees.length == 0) {
        System.err.println("No employees loaded. Check file path and format.");
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}