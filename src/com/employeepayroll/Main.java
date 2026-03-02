package com.employeepayroll;

import java.util.Scanner;

import com.employeepayroll.services.EmployeeService;

public class Main {

    private static final String FILENAME = "data/employeesWithoutRepeat.txt";
    private static Scanner scanner       = new Scanner(System.in);
    private static EmployeeService service;

    public static void main(String[] args) {
        service = new EmployeeService(FILENAME);

        int choice = -1;

        while (choice != 0) {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    service.printAllEmployees();
                    break;
                case 2:
                    service.sortBySalary();
                    System.out.println("Array sorted by salary (low → high) and written to sortedemployeeBySalary.csv");
                    break;
                case 3:
                    service.sortByName();
                    System.out.println("Array sorted by name (A → Z) and written to sortedemployeeByName.csv");
                    break;
                case 4:
                    service.testSortingPerformance();
                    break;
                case 5:
                    service.searchMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (choice != 0) {
                System.out.println("\nPress ENTER to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║       EMPLOYEE PAYROLL SYSTEM        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Print all employees              ║");
        System.out.println("║  2. Sort by salary (SelectionSort)   ║");
        System.out.println("║  3. Sort by name   (QuickSort)       ║");
        System.out.println("║  4. Compare sort performance         ║");
        System.out.println("║  5. Search employee by name          ║");
        System.out.println("║  0. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }


    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
