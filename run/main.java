import java.util.Scanner;
import util.BinarySearch;
import util.CSVWriter;
import util.PerformanceTester;

public class Main {


        CSVWriter.write("sortedemployeeBySalary.csv", employeeArrayForSalary);
        CSVWriter.write("sortedemployeeByName.csv", employeeArrayForName);



        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name to search ► ");
        String nameToSearch = scanner.nextLine();

        
        Employee searchKey = new Employee(0, nameToSearch, 0, 0, 0, 0, 0);

        int index = BinarySearch.search(employeeArrayForName, searchKey);

        if (index != -1) {
            System.out.println("Employee FOUND at index ► " + index);
            System.out.println(employeeArrayForName[index]);
        } else {
            System.out.println("Employee NOT FOUND.");
        }

        scanner.close();
    }
}
