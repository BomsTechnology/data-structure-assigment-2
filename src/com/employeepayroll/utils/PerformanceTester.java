package com.employeepayroll.utils;

import com.employeepayroll.sorting.QuickSort;
import com.employeepayroll.sorting.SelectionSort;

public class PerformanceTester {
     public static <T extends Comparable<T>> void compareAndPrintPerformance(T[] employeeArrayForSalary, T[] employeeArrayForName) {
        System.out.println("The performance of our sorting algorithms");
        System.out.println("###########################################");

        long startSelectionTime = System.currentTimeMillis();
        SelectionSort.sort(employeeArrayForSalary);
        long endSelectionTime = System.currentTimeMillis();
        long totalSelectionTime = endSelectionTime - startSelectionTime;

        System.out.println("Selection Sort Time ► " + totalSelectionTime + " ms");

        long startQuickTime = System.currentTimeMillis();
        QuickSort.sort(employeeArrayForName);
        long endQuickTime = System.currentTimeMillis();
        long totalQuickTime = endQuickTime - startQuickTime;

        System.out.println("Quick Sort Time ► " + totalQuickTime + " ms");
        System.out.println("###########################################");
    }
}
