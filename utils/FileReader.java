package utils;

import java.io.BufferedReader;
import java.io.IOException;
import models.Employee;

public class FileReader {
  /**
   * Reads employee data from the specified file and populates an Employee array
   * 
   * @param filePath Path to the input file
   * @param maxSize Maximum number of employees to read
   * @return Array of Employee objects
   */
  public static Employee[] readEmployeesFromFile(String filePath, int maxSize) {
    Employee[] tempEmployees = new Employee[maxSize];
    int count = 0;
    
    try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null && count < maxSize) {
        String[] parts = line.split(",");
        
        if (parts.length == 7) {
          int id = Integer.parseInt(parts[0].trim());
          String name = parts[1].trim();
          double hoursWorked = Double.parseDouble(parts[2].trim());
          double hourlyRate = Double.parseDouble(parts[3].trim());
          double deductionProvince = Double.parseDouble(parts[4].trim());
          double deductionFederal = Double.parseDouble(parts[5].trim());
          double educationAllowance = Double.parseDouble(parts[6].trim());
          
          tempEmployees[count] = new Employee(id, name, hoursWorked, hourlyRate, deductionProvince, deductionFederal, educationAllowance);
          count++;
        }
      }
            
      // Return only the filled portion of the array
      Employee[] employees = new Employee[count];
      System.arraycopy(tempEmployees, 0, employees, 0, count);
      return employees;
          
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      return new Employee[0]; // Return empty array if file not found
    }
  }
}