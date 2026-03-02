package com.employeepayroll.models;

public class Employee  {
    private int id;
    private String name;
    private double hoursWorked;
    private double hourlyRate;
    private double deductionProvince;
    private double deductionFederal;
    private double educationAllowance;

    public Employee(int id, String name, double hoursWorked, double hourlyRate,
                    double deductionProvince, double deductionFederal, double educationAllowance) {
        this.id = id;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.deductionProvince = deductionProvince;
        this.deductionFederal = deductionFederal;
        this.educationAllowance = educationAllowance;
    }

    public double calcHourlySalary() {
        if (hoursWorked == 0) return 0.0;

        double grossPay = hoursWorked * hourlyRate;
        double netPay   = grossPay - deductionProvince - deductionFederal + educationAllowance;
        return netPay / hoursWorked;
    }

    public int    getId()                  { return id; }
    public String getName()               { return name; }
    public double getHoursWorked()        { return hoursWorked; }
    public double getHourlyRate()         { return hourlyRate; }
    public double getDeductionProvince()  { return deductionProvince; }
    public double getDeductionFederal()   { return deductionFederal; }
    public double getEducationAllowance() { return educationAllowance; }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | %-20s | Hours: %5.1f | Rate: $%6.2f | " +
            "Prov: %.0f%% | Fed: %.0f%% | Edu: $%7.2f | Net Hourly: $%7.2f",
            id, name, hoursWorked, hourlyRate,
            deductionProvince,
            deductionFederal,
            educationAllowance,
            calcHourlySalary()
        );
    }
}
