package models;

public class Employee {
  private final int id;
  private final String name;
  private final double hoursWorked;
  private final double hourlyRate;
  private final double deductionProvince;
  private final double deductionFederal;
  private final double educationAllowance;

  public Employee(
    int id, 
    String name, 
    double hoursWorked, 
    double hourlyRate, 
    double deductionProvince, 
    double deductionFederal, 
    double educationAllowance
  ) {
    this.id = id;
    this.name = name;
    this.hoursWorked = hoursWorked;
    this.hourlyRate = hourlyRate;
    this.deductionProvince = deductionProvince;
    this.deductionFederal = deductionFederal;
    this.educationAllowance = educationAllowance;
  }

  /**
   * Calculates based on gross pay minus deductions plus allowances, 
   * then divided by hours worked to get the effective hourly salary.
   */
  public double calcHourlySalary() {
    if (hoursWorked == 0) return 0; // Prevent division by zero
    
    double salaryBeforeTax = hoursWorked * hourlyRate;
    double provinceTax = salaryBeforeTax * deductionProvince;
    double federalTax = salaryBeforeTax * deductionFederal;
    
    double salaryAfterTax = salaryBeforeTax - provinceTax - federalTax + educationAllowance;
    
    return salaryAfterTax / hoursWorked;
  }

  /**
   * Formatted for the CSV output requirements
   */
  @Override
  public String toString() {
    return String.format("%d,%s,%.2f,%.2f,%.2f,%.2f,%.2f", 
      id, name, hoursWorked, hourlyRate, deductionProvince, deductionFederal, educationAllowance);
  }
}
