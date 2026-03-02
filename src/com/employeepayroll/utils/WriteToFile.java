package com.employeepayroll.utils;

import com.employeepayroll.models.Employee;

public class WriteToFile {
    public static void write(Employee[] employees, int count, String filename) {
        java.io.BufferedWriter bw = null;
        try {
            bw = new java.io.BufferedWriter(
                    new java.io.OutputStreamWriter(
                            new java.io.FileOutputStream(filename), "UTF-8"));
    
            for (int i = 0; i < count; i++) {
                Employee e = employees[i];
                bw.write(
                    e.getId()                 + "," +
                    e.getName()               + "," +
                    e.getHoursWorked()        + "," +
                    e.getHourlyRate()         + "," +
                    e.getDeductionProvince()  + "," +
                    e.getDeductionFederal()   + "," +
                    e.getEducationAllowance()
                );
                bw.newLine();
            }
    
            System.out.printf("Successfully wrote %d employee(s) to \"%s\"%n%n", count, filename);
    
        } catch (java.io.IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        } finally {
            if (bw != null) {
                try { bw.close(); } catch (java.io.IOException e) { /* ignore */ }
            }
        }
    }
}
