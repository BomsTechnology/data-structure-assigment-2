package com.employeepayroll.services;

import java.io.*;
import java.util.Scanner;

import com.employeepayroll.models.Employee;
import com.employeepayroll.sorting.BinarySearch;
import com.employeepayroll.sorting.EmployeeComparable;
import com.employeepayroll.sorting.QuickSort;
import com.employeepayroll.sorting.SelectionSort;
import com.employeepayroll.utils.PerformanceTester;
import com.employeepayroll.utils.WriteToFile;

public class EmployeeService {

    private static final int MAX_EMPLOYEES = 1000;
    private static final String DELIMITER   = ",";
    private int count;
    private Employee[] employees;

    public EmployeeService(String filename) {
        this.employees  = new Employee[MAX_EMPLOYEES];
        this.count      = 0;
        this.count      = loadEmployees(filename);
    }
    
    public int loadEmployees(String filename) {
        count = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip blank lines or comment lines
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split(DELIMITER);

                if (parts.length != 7) {
                    System.err.printf("Warning – line %d skipped (expected 7 fields, got %d): %s%n",
                            lineNumber, parts.length, line);
                    continue;
                }

                try {
                    int    id                 = Integer.parseInt(parts[0].trim());
                    String name               = parts[1].trim();
                    double hoursWorked        = Double.parseDouble(parts[2].trim());
                    double hourlyRate         = Double.parseDouble(parts[3].trim());
                    double deductionProvince  = Double.parseDouble(parts[4].trim());
                    double deductionFederal   = Double.parseDouble(parts[5].trim());
                    double educationAllowance = Double.parseDouble(parts[6].trim());

                    employees[count++] = new Employee(
                            id, name, hoursWorked, hourlyRate,
                            deductionProvince, deductionFederal, educationAllowance);

                } catch (NumberFormatException e) {
                    System.err.printf("Warning – line %d skipped (parse error): %s%n", lineNumber, line);
                }
            }

            System.out.printf("Successfully loaded %d employee(s) from \"%s\"%n%n", count, filename);

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found – " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return count;
    }

    public void sortBySalary() {
        EmployeeComparable[] bySalary = new EmployeeComparable[count];
        for (int i = 0; i < count; i++)
            bySalary[i] = new EmployeeComparable(employees[i], EmployeeComparable.SortMode.BY_SALARY);

        SelectionSort.sort(bySalary);

        // copy sorted result back into employees array
        for (int i = 0; i < count; i++)
            employees[i] = bySalary[i].getEmployee();

        System.out.println("Employees sorted by hourly salary (low → high).");
        WriteToFile.write(employees, count, "export/sortedemployeeBySalary.csv");
        System.out.println("Employees saved to \"export/sortedemployeeBySalary.csv\".");
    }

    public void sortByName() {
        EmployeeComparable[] byName = new EmployeeComparable[count];
        for (int i = 0; i < count; i++)
            byName[i] = new EmployeeComparable(employees[i], EmployeeComparable.SortMode.BY_NAME);

        QuickSort.sort(byName);

        // copy sorted result back into employees array
        for (int i = 0; i < count; i++)
            employees[i] = byName[i].getEmployee();

        System.out.println("Employees sorted by name (A → Z).");
        WriteToFile.write(employees, count, "export/sortedemployeeByName.csv");
        System.out.println("Employees saved to \"export/sortedemployeeByName.csv\".");
    }

    public void testSortingPerformance() {
        // SelectionSort — by salary
        EmployeeComparable[] bySalary = new EmployeeComparable[MAX_EMPLOYEES];
        for (int i = 0; i < MAX_EMPLOYEES; i++)
            bySalary[i] = new EmployeeComparable(employees[i], EmployeeComparable.SortMode.BY_SALARY);

        // QuickSort — by name
        EmployeeComparable[] byName = new EmployeeComparable[MAX_EMPLOYEES];
        for (int i = 0; i < MAX_EMPLOYEES; i++)
            byName[i] = new EmployeeComparable(employees[i], EmployeeComparable.SortMode.BY_NAME);

        PerformanceTester.compareAndPrintPerformance(bySalary, byName);
    }

    public void printAllEmployees() {
        if (count == 0) {
            System.out.println("No employees to display.");
            return;
        }

        System.out.println("=".repeat(100));
        System.out.printf("%-5s %-20s %10s %12s %18s %16s %20s %15s%n",
                "ID", "Name", "Hours", "Hourly Rate",
                "Prov. Deduction", "Fed. Deduction",
                "Edu. Allowance", "Net Hourly");
        System.out.println("=".repeat(100));

        for (int i = 0; i < count; i++) {
            Employee e = employees[i];
            System.out.printf("%-5d %-20s %10.2f %12.2f %18.2f %16.2f %20.2f %15.2f%n",
                    e.getId(),
                    e.getName(),
                    e.getHoursWorked(),
                    e.getHourlyRate(),
                    e.getDeductionProvince(),
                    e.getDeductionFederal(),
                    e.getEducationAllowance(),
                    e.calcHourlySalary());
        }

        System.out.println("=".repeat(100));
        System.out.printf("Total employees: %d%n%n", count);
    }

    public void searchMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Binary Search ===");
        System.out.print("Enter employee name to search for: ");
        String name = scanner.nextLine().trim();

        // Make sure array is sorted by name before searching
        EmployeeComparable[] byName = new EmployeeComparable[count];
        for (int i = 0; i < count; i++)
            byName[i] = new EmployeeComparable(employees[i], EmployeeComparable.SortMode.BY_NAME);

        // Create target
        Employee target      = new Employee(0, name, 0, 0, 0, 0, 0);
        EmployeeComparable t = new EmployeeComparable(target, EmployeeComparable.SortMode.BY_NAME);

        int index = BinarySearch.search(byName, t, 0, count - 1);

        // Print result
        System.out.println("\n--- Search Result ---");
        if (index == -1) {
            System.out.println("Employee \"" + name + "\" was NOT found.");
        } else {
            System.out.println("Employee \"" + name + "\" was found at index " + index + ":");
            Employee found = byName[index].getEmployee();
            System.out.printf("  ID: %d | Name: %s | Net Hourly Salary: $%.2f%n",
                    found.getId(),
                    found.getName(),
                    found.calcHourlySalary());
        }
        System.out.println("---------------------\n");
    }

}
