package com.employeepayroll.sorting;

import com.employeepayroll.models.Employee;

public class EmployeeComparable implements Comparable<EmployeeComparable> {

    public enum SortMode { BY_SALARY, BY_NAME }

    private Employee employee;
    private SortMode sortMode;

    public EmployeeComparable(Employee employee, SortMode sortMode) {
        this.employee = employee;
        this.sortMode = sortMode;
    }

    public Employee getEmployee() { return employee; }

    @Override
    public int compareTo(EmployeeComparable other) {
        if (sortMode == SortMode.BY_SALARY) {
            return Double.compare(
                this.employee.calcHourlySalary(),
                other.employee.calcHourlySalary()
            );
        } else {
            return this.employee.getName().compareToIgnoreCase(other.employee.getName());
        }
    }
}